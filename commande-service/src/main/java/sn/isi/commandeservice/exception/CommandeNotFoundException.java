package sn.isi.commandeservice.exception;

public class CommandeNotFoundException extends RuntimeException {
  public CommandeNotFoundException(String message) {
    super(message);
  }
}
