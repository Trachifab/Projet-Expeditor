package fr.eni.expeditor.exception;

public class ConnexionException extends Exception {
    public ConnexionException() {
        super("Il y à eu un problème lors de la tentative de connexion");
    }
}
