package sn.isi.produitservice.exception;

public class ProduitNotFoundException extends RuntimeException {
    public ProduitNotFoundException(Long id) {
        super("Produit introuvable avec l'id : " + id);
    }
}
