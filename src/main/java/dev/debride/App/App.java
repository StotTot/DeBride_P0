package dev.debride.App;

import dev.debride.controllers.AccountController;
import dev.debride.controllers.ClientController;
import io.javalin.Javalin;

public class App {

    public static void main(String[] args) {


        Javalin app = Javalin.create();
        AccountController accountController = new AccountController();
        ClientController clientController = new ClientController();

        // CLIENT OPERATIONS:
        // GET all clients
        app.get("/clients", clientController.getAllClientsHandler);

        // GET client by id
        app.get("/clients/:id", clientController.getClientByIdHandler);

        // POST create a new client
        app.post("/clients", clientController.createClientHandler);

        // PUT update account name
        app.put("/clients/:id", clientController.updateClientHandler);

        // DELETE
        app.delete("/clients/:id", clientController.deleteClientHandler);

        // ACCOUNT OPERATIONS:
        // GET all accounts for the client
        app.get("/clients/:id/accounts", accountController.getAllAccountsHandler);

        // GET client by id
        app.get("/clients/:id/accounts/:aid", accountController.getAccountByIdHandler);

        // POST create a new account
        app.post("/clients/:id/accounts", accountController.createAccountHandler);

        // PUT update account balance
        app.put("/clients/:id/accounts/:aid", accountController.updateAccount);

        // DELETE account
        app.delete("/clients/:id/accounts/:aid", accountController.deleteAccountHandler);


        app.start();
    }
}
