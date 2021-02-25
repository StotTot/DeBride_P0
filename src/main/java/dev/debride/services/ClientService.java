package dev.debride.services;

import dev.debride.entities.Client;

import java.util.Set;

public interface ClientService {

    // POST

    Client registerClient(Client client);

    // GET

    Set<Client> getAllClients();

    Client getClientById(int id);

    // PUT

    Client updateClient(Client client);

    // DELETE

    boolean deleteClientById(int id);

}
