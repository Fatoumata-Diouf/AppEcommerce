package sn.isi.commandeservice.exception;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long id) {
        super("Client introuvable avec l'id : " + id);
    }
}
