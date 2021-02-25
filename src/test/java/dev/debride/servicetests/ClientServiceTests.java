package dev.debride.servicetests;


import dev.debride.daos.ClientDaoPostgre;
import dev.debride.entities.Client;
import dev.debride.services.ClientService;
import dev.debride.services.ClientServiceImpl;
import org.junit.jupiter.api.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientServiceTests {


    private static ClientService cserv = new ClientServiceImpl(new ClientDaoPostgre());
    private static Client testClient = null;

    @Test
    @Order(1)
    void registerClient(){

        Client client = new Client(0, "Aristotle DeBride");
        cserv.registerClient(client);
        System.out.println(client);

        Assertions.assertNotEquals(0, client.getClientId());
        Assertions.assertEquals("Aristotle DeBride", client.getName());

        testClient = client;
    }

    @Test
    @Order(2)
    void update_client_name(){
        Client client = new Client(testClient.getClientId(), "AD");
        cserv.updateClient(client);

        System.out.println(client);
        Assertions.assertEquals("AD", client.getName());


    }


}
