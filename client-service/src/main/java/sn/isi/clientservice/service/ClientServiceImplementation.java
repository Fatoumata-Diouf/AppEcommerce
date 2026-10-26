package sn.isi.clientservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sn.isi.clientservice.entity.Client;
import sn.isi.clientservice.exception.ClientNotFoundException;
import sn.isi.clientservice.repository.ClientRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImplementation implements IClient {

    private final ClientRepository clientRepository;

    @Override
    public Client save(Client client) {
        log.info("Début de l'enregistrement du client : {}", client);
        try {
            Client saved = clientRepository.save(client);
            log.info("Client enregistré avec succès, id={}", saved.getId());
            return saved;
        } catch (Exception e) {
            log.error("Erreur lors de l'enregistrement du client : {}", client, e);
            throw e;
        }
    }

    @Override
    public Client findById(Long id) {
        log.info("Recherche du client avec id={}", id);
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Aucun client trouvé avec id={}", id);
                    return new ClientNotFoundException(id);
                });
        log.debug("Client trouvé : {}", client);
        return client;
    }

    @Override
    public List<Client> findAll() {
        log.info("Récupération de tous les clients");
        List<Client> clients = clientRepository.findAll();
        log.info("{} client(s) récupéré(s)", clients.size());
        return clients;
    }
}