package sn.isi.commandeservice.exception;

public class CommandeNotFoundException extends RuntimeException {
    public CommandeNotFoundException(Long id) {
        super("Commande introuvable avec l'id : " + id);
    }
}
