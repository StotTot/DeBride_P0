package dev.debride.controllers;

import com.google.gson.Gson;
import dev.debride.daos.ClientDaoPostgre;
import dev.debride.entities.Client;
import dev.debride.services.ClientService;
import dev.debride.services.ClientServiceImpl;
import io.javalin.http.Handler;

import java.util.Set;

public class ClientController {

    private ClientService clientService = new ClientServiceImpl(new ClientDaoPostgre());

    public Handler getAllClientsHandler = (ctx) ->{


        Set<Client> allClients = this.clientService.getAllClients();
        Gson gson = new Gson();
        String clientsJSON = gson.toJson(allClients);

        if(allClients.size() != 0){
            ctx.result(clientsJSON);
            ctx.status(200);
            System.out.println("Got clients");
        }else{
            ctx.status(404);
            System.out.println("No client exists");
        }

    };

    public Handler getClientByIdHandler = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        Client client = this.clientService.getClientById(id);
        if(client == null){
            ctx.result("Client does not exist");
            ctx.status(404);
        }else{
            Gson gson = new Gson();
            String clientJSON = gson.toJson(client);
            ctx.result(clientJSON);
            ctx.status(200);
        }

    };

    public Handler createClientHandler = (ctx) ->{
        String body = ctx.body();
        Gson gson = new Gson();
        Client client = gson.fromJson(body, Client.class);
        this.clientService.registerClient(client);
        String json = gson.toJson(client);
        ctx.result("Welcome " + client.getName() + "!");
        ctx.result(json);
        ctx.status(201);
    };

    public Handler updateClientHandler = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        Client lfClient = this.clientService.getClientById(id);
        if(lfClient == null){
            ctx.result("Client not found");
            ctx.status(404);
        }else{
            String body = ctx.body();
            Gson gson = new Gson();
            Client client = gson.fromJson(body, Client.class);
            client.setClientId(id);
            this.clientService.updateClient(client);
            Client updatedClient = this.clientService.getClientById(id);
            Gson upGson = new Gson();
            String upDatedJSON = upGson.toJson(updatedClient);
            ctx.result(upDatedJSON);
            ctx.status(200);
        }

    };

    public Handler deleteClientHandler = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        Client lfClient = this.clientService.getClientById(id);
        boolean deleted = this.clientService.deleteClientById(id);
        if(deleted && lfClient != null){
            ctx.result("Client was deleted.");
            ctx.status(200);
        }else{
            ctx.result("Could not delete.");
            ctx.status(404);
        }
    };






}
