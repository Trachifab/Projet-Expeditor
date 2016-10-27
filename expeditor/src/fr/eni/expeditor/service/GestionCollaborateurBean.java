package fr.eni.expeditor.service;

import javax.ejb.Stateless;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import fr.eni.expeditor.entity.Collaborateur;
import fr.eni.expeditor.entity.Commande;
import fr.eni.expeditor.exception.ConnexionException;
import org.jboss.logging.Logger;

import java.util.*;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Service sous forme d'EJB permattant de gérer les entités Collaborateurs
 */
@Stateless
public class GestionCollaborateurBean extends AbstractService {
    private static Logger LOGGER = Logger.getLogger(GestionCollaborateurBean.class.getName());

    /**
     * Ajoute ou modifie un utilisateur
     * @param user
     */
    public void enregistrerCollaborateur(Collaborateur user) {
        if (user.getId() == null) {
            getEntityManager().persist(user);
        } else {
            user = getEntityManager().merge(user);
        }
    }

    /**
     * Renvoie tous les collaborateurs
     * @return la liste de tous les collaborateurs
     */
    public List<Collaborateur> rechercherTous(){

        Query query = getEntityManager().createQuery("SELECT c FROM Collaborateur c WHERE c.dateArchive is null");
        return query.getResultList();
    }

    /**
     * Renvoie un collaborateur
     * @param identite
     * @return
     */
    public Collaborateur rechercherParIdentifiant(Integer identite){

        return getEntityManager().find(Collaborateur.class, identite);
    }

    /**
     * Rnvoie un collaborateur par son login
     * @param email
     * @return
     */
    public Collaborateur rechercherParLogin(String email){
        return null;
    }

    /**
     * Supprime un collaborateur par son identifiant
     * @param identifiant
     */
    public void supprimerParIdentifiant(Integer identifiant){

        getEntityManager().remove(identifiant);
    }

    /**
     * Supprime un collaborateur par son login
     * @param login
     */
    public void supprimerParLogin(String login) {

        getEntityManager().remove(login);
    }

    /**
     *
     * @param email
     * @return
     */
    public Boolean collaborateurExiste(String email) {
        LOGGER.info("Recherche du collaborateur dont l'identifiant est " + email);
        Query q = getEntityManager().createNamedQuery("CollaborateurLoginExists");
        q.setParameter("email", email);
        // On fait != 0 pour "caster" le résultat de type "Long" en "Boolean"
        return ((Long)q.getSingleResult()) != 0;
    }

    /**
     *
     * @param email
     * @param motDePasse
     * @return
     * @throws ConnexionException
     */
    public Collaborateur rechercherParLoginMotDePasse(String email, String motDePasse) throws ConnexionException {
        LOGGER.info(String.format("Tentative de connexion de l'utilisateur '%s'", email));
        Query q = getEntityManager().createNamedQuery("SelectCollaborateurByLoginMotDePasse");
        q.setParameter("email", email);
        q.setParameter("motDePasse", motDePasse);
        try{
            return (Collaborateur)q.getSingleResult();
        }
        catch (NoResultException nre){
            throw new ConnexionException();
        }
    }

    /**
     * Récupére les statistiques de tous les collaborateurs de la journée
     */
    public JsonElement recupererStatistiques() {

        LOGGER.info("Récupération des statistiques collaborateurs");
        Query q = getEntityManager().createNamedQuery("COMMANDE.RECUPERER.STATISTIQUES.DU.JOUR");
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());

        q.setParameter("dateDuJour", date);
        List<Commande> commandesDuJour = q.getResultList();

        List<Collaborateur> tousLesCollaborateurs = rechercherTous();
        List<Statistiques> statistiques = new ArrayList<>();

        Statistiques currentStat = new Statistiques();
        for(Collaborateur collaborateur : tousLesCollaborateurs){
            if(collaborateur.getRole().getCode().equals("EMPL")){
                currentStat.setCollaborateur(collaborateur);
                for (Commande commande : commandesDuJour) {
                    if(commande.getEtat().getCode().equals("TRAI")
                            && commande.getCollaborateur().getId().equals(collaborateur.getId())){

                        currentStat.incrementerStat();
                    }
                }
                statistiques.add(currentStat);
                currentStat = new Statistiques();
            }
        }

        String colonnes ="";
        boolean isFirst = true;
        String valeur ="";

        for(Statistiques stat : statistiques) {
            colonnes += (isFirst? "":",")+stat.getCollaborateur().getPrenom();
            valeur += (isFirst? "":",")+stat.getNbCommandesTraites();

            isFirst = false;

        }

      StringBuilder str = new StringBuilder();
      str.append("{'colonnes':[");
        str.append(colonnes);
        str.append("],");
        str.append("'valeurs':[");

        str.append(valeur);
        str.append("]}");


        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(str.toString());

      return element;
    }

    /**
     * Classe interne statistiques
     */
    private class Statistiques{
        private Collaborateur collaborateur;
        private int nbCommandesTraites;

        public Statistiques(){

        }

        public Statistiques(Collaborateur collaborateur, int nbCommandesTraites){
            this.collaborateur = collaborateur;
            this.nbCommandesTraites = nbCommandesTraites;
        }

        public Collaborateur getCollaborateur() {
            return collaborateur;
        }

        public Statistiques setCollaborateur(Collaborateur collaborateur) {
            this.collaborateur = collaborateur;
            return this;
        }

        public int getNbCommandesTraites() {
            return nbCommandesTraites;
        }

        public Statistiques setNbCommandesTraites(int nbCommandesTraites) {
            this.nbCommandesTraites = nbCommandesTraites;
            return this;
        }

        public void incrementerStat(){
            this.nbCommandesTraites++;
        }
    }
}
