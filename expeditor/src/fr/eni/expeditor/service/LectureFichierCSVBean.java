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
import java.util.Random;

/**
 * Created by d1503betournej on 26/10/2016.
 */
@Stateless
public class LectureFichierCSVBean extends AbstractService {


    @EJB
    private GestionCommandeBean gestionCommandeBean;

    @EJB
    private GestionLigneCommandeBean gestionLigneCommandeBean;

    @EJB
    private GestionArticleBean gestionArticleBean;

    @EJB
    private GestionClientBean gestionClientBean;


    private static Logger LOGGER = Logger.getLogger(LectureFichierCSVBean.class.getName());

    /**
     * Permet de lire le fichier csv de commandes.
     *
     * @param chemin Chemin complet du fichier csv (avec l'extension .csv)
     * @throws FileNotFoundException Impossible de trouver le fichier.
     * @throws IOException
     */
    public String lectureFichierCommandes(String chemin) throws FileNotFoundException, IOException {

        LOGGER.info("Lecture du fichier csv " + chemin);

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(chemin), "UTF8"))) {
            LOGGER.debug("Fichier trouvé");
            int nombreEntrees = count(chemin) - 1;
            int nombreInserees = 0;
            LOGGER.info(nombreEntrees + " entrées trouvées dans le fichier.");
            String ligne = null;
            String[] lignesCommande;

            Commande commandeCourante;
            Client clientCourant;
            LOGGER.debug("Début lecture des lignes du fichier csv");
            br.readLine();
            while ((ligne = br.readLine()) != null) {
                try {
                    String[] data = ligne.split(",");

                    LOGGER.debug("Split sur , " + Arrays.toString(data));

                    commandeCourante = new Commande();
                    commandeCourante = lectureCommande(data, commandeCourante);

                    clientCourant = new Client();
                    clientCourant = lectureClient(data, clientCourant);

                    commandeCourante.setClient(clientCourant);

                    lignesCommande = data[4].split(";");
                    LOGGER.debug("split lignes commande ; " + Arrays.toString(lignesCommande));
                    commandeCourante = lectureLignesCommande(lignesCommande, commandeCourante);
                    LOGGER.info(commandeCourante);

                    gestionCommandeBean.ajouter(commandeCourante);
                    nombreInserees++;

                } catch (Exception ex) {
                    LOGGER.debug("--------------- Un élément a été ignoré car il ne correspondait pas aux exigences CSV ----------- ");
                }

            }

            LOGGER.info(nombreEntrees + " entrées détectées dans le fichier, " + nombreInserees + " commandes insérées.");

            return nombreEntrees + " entrées détectées dans le fichier, " + nombreInserees + " commandes insérées.";
        }
    }

    /**
     * Création/mise en correspondance du client du fichier CSV.
     *
     * @param data          La ligne CSV correspondant aux infos du client.
     * @param clientCourant
     * @return
     * @throws Exception
     */
    private Client lectureClient(String[] data, Client clientCourant) throws Exception {

        String[] adresseComplete;
        String idExterneClient, adresse1Client,
                codePostalClient, villeClient;

        idExterneClient = data[2].trim();

        LOGGER.debug("idExterne client " + idExterneClient);

        adresseComplete = data[3].split("-");

        LOGGER.debug("adresseComplete " + Arrays.toString(adresseComplete));
        adresse1Client = adresseComplete[0].trim();
        LOGGER.debug("adresse1 client " + adresse1Client);

        codePostalClient = adresseComplete[1].substring(0, 6).trim();
        LOGGER.debug("code postal client " + codePostalClient);
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
        LOGGER.debug("ville client " + villeClient);

        clientCourant.setIdExterne(idExterneClient);
        clientCourant.setRaisonSociale(idExterneClient);
        clientCourant.setAdresse1(adresse1Client);
        clientCourant.setCp(codePostalClient);
        clientCourant.setVille(villeClient);


        LOGGER.debug("recherche/persistence du client " + clientCourant.toString());


        Client correspondanceClient = gestionClientBean.rechercherParIdentifiantExterne(clientCourant);

        if (correspondanceClient == null) {
            gestionClientBean.enregistrerClient(clientCourant);
        } else {
            clientCourant = correspondanceClient;
        }

        return clientCourant;
    }

    /**
     * Création de la commande du fichier CSV.
     *
     * @param data             La ligne CSV correspondant aux infos de la commande.
     * @param commandeCourante
     * @return
     */
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

        LOGGER.debug("Date commande  " + dateCommande);
        numeroCommande = Integer.parseInt(data[1].split("Cmd N° ")[1]);


        LOGGER.debug("Numero commande  " + numeroCommande);

        commandeCourante.setDateCommande(dateCommande);
        commandeCourante.setNumero(numeroCommande);

        etatCourant = new Etat();
        etatCourant.setLibelle("En attente");
        etatCourant.setCode("ATTE");

        commandeCourante.setEtat(etatCourant);
        commandeCourante.setLignesCommande(new ArrayList<>());


        return commandeCourante;
    }

    /**
     * Création des lignes commande du fichier CSV.
     *
     * @param lignesCommande
     * @param commandeCourante
     * @return
     */
    private Commande lectureLignesCommande(String[] lignesCommande, Commande commandeCourante) {

        String[] articleQuantite;
        String idExterneArticle;
        int quantiteArticle;
        LigneCommande ligneCommandeCourante;
        Article articleCourant;

        for (String ligneCommande : lignesCommande) {
            articleQuantite = ligneCommande.split("\\(");


            LOGGER.debug("split ligne commande " + Arrays.toString(articleQuantite));
            idExterneArticle = articleQuantite[0].trim();

            LOGGER.debug("idExterne article " + idExterneArticle);
            quantiteArticle = Integer.parseInt(articleQuantite[1].split("\\)")[0]);

            LOGGER.debug("quantite article " + quantiteArticle);

            articleCourant = new Article();
            articleCourant.setIdExterne(idExterneArticle);
            articleCourant.setLibelle(idExterneArticle);

            Random random = new Random();
            int randomNumber = random.nextInt(9999 - 1000) + 1000;

            articleCourant.setPoids(randomNumber);
            articleCourant.setDescription("Pas de description disponible pour cet article.");

            Article correspondanceArticle = gestionArticleBean.rechercherParIdentifiantExterne(articleCourant);

            if (correspondanceArticle == null) {
                gestionArticleBean.enregistrerArticle(articleCourant);
            } else {
                articleCourant = correspondanceArticle;
            }

            ligneCommandeCourante = new LigneCommande();

            ligneCommandeCourante.setArticle(articleCourant);
            ligneCommandeCourante.setQuantite(quantiteArticle);

            ligneCommandeCourante.setCommande(commandeCourante);
            commandeCourante.getLignesCommande().add(ligneCommandeCourante);

            IdLigneCommande idLigne = new IdLigneCommande();
            idLigne.setArticle_id(articleCourant.getId());
            idLigne.setCommande_numero(commandeCourante.getNumero());

            ligneCommandeCourante.setId(idLigne);

        }

        return commandeCourante;
    }

    /**
     * Permet de compter le nombre d'occurences - 1 (première ligne = nom des colonnes) dans un fichier CSV.
     *
     * @param filename
     * @return
     * @throws IOException
     */
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
