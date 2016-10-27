package fr.eni.expeditor.service;


import fr.eni.expeditor.entity.*;
import org.jboss.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by d1503betournej on 26/10/2016.
 */
@Stateless
public class LectureFichierCSVBean extends AbstractService {


    @EJB
    private GestionCommandeBean gestionCommandeBean;

    @EJB
    private GestionClientBean gestionClientBean;

    private static Logger LOGGER = Logger.getLogger(LectureFichierCSVBean.class.getName());


    public void lectureFichierCommandes(String chemin) throws FileNotFoundException, IOException {

        LOGGER.info("Lecture du fichier csv " + chemin);

        try (BufferedReader br = new BufferedReader(new FileReader(chemin))) {
            LOGGER.info("Fichier trouvé");
            LOGGER.info(count(chemin) - 1 + " entrées trouvées dans le fichier.");
            String ligne = null;
            String[] lignesCommande;
            List<Commande> commandesCSV = new ArrayList<>();

            Commande commandeCourante;
            Etat etatCourant;
            Client clientCourant;
            LOGGER.info("Début lecture des lignes du fichier csv");
            br.readLine();
            while ((ligne = br.readLine()) != null) {
                try {
                    String[] data = ligne.split(",");

                    LOGGER.info("Split sur , " + Arrays.toString(data));

                    commandeCourante = new Commande();
                    commandeCourante = lectureCommande(data, commandeCourante);

                    clientCourant = new Client();
                    clientCourant = lectureClient(data, clientCourant);

                    commandeCourante.setClient(clientCourant);

                    lignesCommande = data[4].split(";");
                    LOGGER.info("split lignes commande ; " + Arrays.toString(lignesCommande));
                    commandeCourante = lectureLignesCommande(lignesCommande, commandeCourante);

                    commandesCSV.add(commandeCourante);

//                    gestionCommandeBean.ajouter(commandeCourante);

                } catch (Exception ex) {
                    LOGGER.info("--------------- Un élément a été ignoré car il ne correspondait pas aux exigences CSV ----------- ");
                }
            }
            LOGGER.info("Récap des commandes CSV");
            for (Commande commande : commandesCSV) {
                LOGGER.info(commande);
            }
        }
    }

    private Client lectureClient(String[] data, Client clientCourant) throws Exception {

        String[] adresseComplete;
        String idExterneClient, adresse1Client,
                codePostalClient, villeClient;

        idExterneClient = data[2];

        LOGGER.info("idExterne client " + idExterneClient);

        adresseComplete = data[3].split("-");

        LOGGER.info("adresseComplete " + Arrays.toString(adresseComplete));
        adresse1Client = adresseComplete[0].trim();
        LOGGER.info("adresse1 client " + adresse1Client);

        codePostalClient = adresseComplete[1].substring(0, 6).trim();
        LOGGER.info("code postal client " + codePostalClient);
        if (codePostalClient.length() != 5) {
            throw new Exception("Code postal invalide.");
        } else {
            try {
                int cpInt = Integer.parseInt(codePostalClient);
            } catch (NumberFormatException ex) {
                throw new Exception("Code postal invalide.");
            }
        }

        villeClient = adresseComplete[1].substring(7);
        LOGGER.info("ville client " + villeClient);

        clientCourant.setIdExterne(idExterneClient);
        clientCourant.setRaisonSociale(idExterneClient);
        clientCourant.setAdresse1(adresse1Client);
        clientCourant.setCp(codePostalClient);
        clientCourant.setVille(villeClient);


        LOGGER.info("recherche/persistence du client " + clientCourant.toString());
        clientCourant = gestionClientBean.rechercherParIdentifiantExterne(clientCourant);

        if (clientCourant == null) {
            gestionClientBean.enregistrerClient(clientCourant);
        }

        return clientCourant;
    }

    private Commande lectureCommande(String[] data, Commande commandeCourante) {
        Date dateCommande;
        int numeroCommande;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Etat etatCourant;

        try {
            dateCommande = formatter.parse(data[0]);
        } catch (ParseException e) {
            dateCommande = new Date();
            e.printStackTrace();
        }

        LOGGER.info("Date commande  " + dateCommande);
        numeroCommande = Integer.parseInt(data[1].split("Cmd NÂ° ")[1]);


        LOGGER.info("Numero commande  " + numeroCommande);

        commandeCourante.setDateCommande(dateCommande);
        commandeCourante.setNumero(numeroCommande);

        etatCourant = new Etat();
        etatCourant.setLibelle("En attente");
        etatCourant.setCode("ATTE");

        commandeCourante.setEtat(etatCourant);
        commandeCourante.setLignesCommande(new ArrayList<>());

        return commandeCourante;
    }

    private Commande lectureLignesCommande(String[] lignesCommande, Commande commandeCourante) {

        String[] articleQuantite;
        String idExterneArticle;
        int quantiteArticle;
        LigneCommande ligneCommandeCourante;
        Article articleCourant;

        for (String ligneCommande : lignesCommande) {
            articleQuantite = ligneCommande.split("\\(");


            LOGGER.info("split ligne commande " + Arrays.toString(articleQuantite));
            idExterneArticle = articleQuantite[0];

            LOGGER.info("idExterne article " + idExterneArticle);
            quantiteArticle = Integer.parseInt(articleQuantite[1].split("\\)")[0]);

            LOGGER.info("quantite article " + quantiteArticle);

            articleCourant = new Article();
            articleCourant.setIdExterne(idExterneArticle);

            ligneCommandeCourante = new LigneCommande();

            ligneCommandeCourante.setArticle(articleCourant);
            ligneCommandeCourante.setQuantite(quantiteArticle);

            ligneCommandeCourante.setCommande(commandeCourante);
            commandeCourante.getLignesCommande().add(ligneCommandeCourante);
        }

        return commandeCourante;
    }

    public int count(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }
}
