package sn.isi.commandeservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sn.isi.commandeservice.dto.ProduitDTO;

@FeignClient(name = "PRODUIT-SERVICE")
public interface ProduitFeignClient {

    @GetMapping("/produits/{id}")
    ProduitDTO getProduitById(@PathVariable("id") Long id);
}
