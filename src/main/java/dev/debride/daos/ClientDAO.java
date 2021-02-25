package dev.debride.daos;

import dev.debride.entities.Client;

import java.util.Set;


public interface ClientDAO {

    // CREATE

    Client registerClient(Client client);

    // READ

    Set<Client> getClients();
    Client getClientById(int id);

    // UPDATE

    Client updateClient(Client client);

    // DELETE
    boolean deleteClientById(int id);
}
