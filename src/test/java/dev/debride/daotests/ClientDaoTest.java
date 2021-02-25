package dev.debride.daotests;

import dev.debride.daos.ClientDAO;
import dev.debride.daos.ClientDaoPostgre;
import dev.debride.entities.Client;
import org.junit.jupiter.api.*;

import java.util.Set;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientDaoTest {
    private static ClientDAO cdao = new ClientDaoPostgre();
    private static Client testClient = null;

    @Test
    @Order(1)
    void create_client(){
        Client johnSmith  = new Client(0, "John Smith");
        cdao.registerClient(johnSmith);
        System.out.println(johnSmith);
        testClient = johnSmith;
        Assertions.assertNotEquals(0, johnSmith.getClientId());
        Assertions.assertEquals("John Smith", johnSmith.getName());

    }

    @Test
    @Order(2)
    void get_client_by_id(){
        System.out.println(testClient.getClientId());
        int id = testClient.getClientId();
        Client client = cdao.getClientById(id);
        System.out.println(cdao.getClientById(id));
        Assertions.assertEquals(testClient.getClientId(), client.getClientId());
        //Assertions.assertEquals(testAccount.getBalance(), account.getBalance());
        System.out.println(client);


    }

    @Test
    @Order(3)
    void get_clients(){
        Set<Client> clients = cdao.getClients();
        Assertions.assertTrue(clients.size() > 2);
        System.out.println(clients);

    }

    @Test
    @Order(4)
    void update_client(){
        Client client = cdao.getClientById(testClient.getClientId());
        client.setName("Dragon Lady");
        cdao.updateClient(client);

        Client updatedClient = cdao.getClientById(testClient.getClientId());
        Assertions.assertEquals("Dragon Lady", updatedClient.getName());
        System.out.println(updatedClient);
    }

    @Test
    @Order(5)
    void delete_client_by_id(){
        int id = testClient.getClientId();
        boolean result = cdao.deleteClientById(id);
        Assertions.assertTrue(result);
    }

}
