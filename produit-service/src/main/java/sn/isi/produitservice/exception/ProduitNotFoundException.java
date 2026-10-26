package sn.isi.clientservice.exception;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long id) {
        super("Client introuvable avec l'id : " + id);
    }
}
