package sn.isi.commandeservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sn.isi.commandeservice.dto.ClientDTO;

@FeignClient(name = "CLIENT-SERVICE")
public interface ClientFeignClient {

    @GetMapping("/clients/{id}")
    ClientDTO getClientById(@PathVariable("id") Long id);
}
