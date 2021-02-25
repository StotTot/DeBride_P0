package dev.debride.services;

import dev.debride.daos.ClientDAO;
import dev.debride.entities.Client;

import java.util.Set;
import java.util.logging.Logger;

public class ClientServiceImpl implements ClientService{

    private static Logger logger = Logger.getLogger(ClientServiceImpl.class.getName());
    private ClientDAO cdao;

    public ClientServiceImpl(ClientDAO clientDAO) {this.cdao = clientDAO;}

    @Override
    public Client registerClient(Client client) {
        this.cdao.registerClient(client);
        return client;
    }

    @Override
    public Set<Client> getAllClients() {
        return this.cdao.getClients();
    }

    @Override
    public Client getClientById(int id) {
        return this.cdao.getClientById(id);
    }

    @Override
    public Client updateClient(Client client) {

        this.cdao.updateClient(client);

        return client;
    }

    @Override
    public boolean deleteClientById(int id) {
        return this.cdao.deleteClientById(id);
    }
}
