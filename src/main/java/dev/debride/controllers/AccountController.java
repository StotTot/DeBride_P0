package dev.debride.controllers;

import com.google.gson.Gson;
import dev.debride.daos.AccountDaoPostgre;
import dev.debride.daos.ClientDaoPostgre;
import dev.debride.entities.Account;
import dev.debride.entities.Client;
import dev.debride.services.AccountService;
import dev.debride.services.AccountServiceImpl;
import dev.debride.services.ClientService;
import dev.debride.services.ClientServiceImpl;
import io.javalin.http.Handler;

import java.util.Set;

public class AccountController {

    private AccountService accountService = new AccountServiceImpl(new AccountDaoPostgre());
    private ClientService clientService = new ClientServiceImpl(new ClientDaoPostgre());


    public Handler getAllAccountsHandler = (ctx) ->{
        String amountGreaterThan = ctx.queryParam("amountGreaterThan", "NONE");
        String amountLessThan = ctx.queryParam("amountLessThan", "NONE");
        int client_id = Integer.parseInt(ctx.pathParam("id"));
        Client client = this.clientService.getClientById(client_id);
        if(client != null){
            if(amountGreaterThan.equals("NONE") && amountLessThan.equals("NONE")){


                System.out.println(client_id);
                Set<Account> allAccounts = this.accountService.getAccounts(client_id);
                Gson gson = new Gson();
                String accountsJSON = gson.toJson(allAccounts);
                if(allAccounts.size() != 0){
                    ctx.result(accountsJSON);
                    ctx.status(200);
                }else{
                    ctx.result("No account for that client exists");
                    ctx.status(404);

                }

            }else if (amountGreaterThan != "NONE" && amountLessThan.equals("NONE")){


                System.out.println(client_id);
                System.out.println("Greater");
                Set<Account> accounts = this.accountService.accountsGreater(client_id, Integer.parseInt(amountGreaterThan));
                Gson gson = new Gson();
                String accountsJSON = gson.toJson(accounts);
                if(accounts.size() != 0){
                    ctx.result(accountsJSON);
                    ctx.status(200);
                }else{
                    ctx.result("No account for that client exists");
                    ctx.status(404);

                }


            }else if (amountLessThan != "NONE" && amountGreaterThan.equals("NONE")){


                System.out.println(client_id);
                System.out.println("Less");
                Set<Account> accounts = this.accountService.accountsLess(client_id, Integer.parseInt(amountLessThan));
                Gson gson = new Gson();
                String accountsJSON = gson.toJson(accounts);

                if(accounts.size() != 0){
                    ctx.result(accountsJSON);
                    ctx.status(200);
                }else{
                    ctx.result("No account for that client exists");
                    ctx.status(404);

                }

            }else{


                System.out.println(client_id);
                System.out.println("Both");
                Set<Account> accounts = this.accountService.accountsGreatAndLess(client_id, Integer.parseInt(amountGreaterThan), Integer.parseInt(amountLessThan));
                Gson gson = new Gson();
                String accountsJSON = gson.toJson(accounts);
                if(accounts.size() != 0){
                    ctx.result(accountsJSON);
                    ctx.status(200);
                }else{
                    ctx.result("No account for that client exists");
                    ctx.status(404);

                }


            }
        }else{
            ctx.result("Client does not exist");
            ctx.status(404);
        }


    };

    public Handler getAccountByIdHandler = (ctx) ->{

        int id = Integer.parseInt(ctx.pathParam("aid"));
        int cid = Integer.parseInt(ctx.pathParam("id"));
        Account account = this.accountService.getAccountById(cid, id);
        if(account == null){
            ctx.result("Account does not exist");
            ctx.status(404);
        }else{
            Gson gson = new Gson();
            String accountJSON = gson.toJson(account);
            ctx.result(accountJSON);
            ctx.status(200);
        }
    };

    public Handler createAccountHandler = (ctx) ->{
      int client_id = Integer.parseInt(ctx.pathParam("id"));
      Client client = this.clientService.getClientById(client_id);
      System.out.println(client_id);
      if(client == null){
          ctx.result("Cannot create a new account due to the client not existing");
          ctx.status(404);
      }else{
          String cname = client.getName();
          String body = ctx.body();
          Gson gson = new Gson();
          Account account = gson.fromJson(body, Account.class);
          account.setOwnerId(client.getClientId());
          this.accountService.registerAccount(account);
          String json = gson.toJson(account);
          ctx.result("A new account was created for " + cname);
          ctx.result(json);
          ctx.status(201);
      }

    };

    public Handler updateAccount = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("aid"));
        int cid = Integer.parseInt(ctx.pathParam("id"));
        Account lfAccount = this.accountService.getAccountById(cid, id);
        if(lfAccount == null){
            ctx.result("Account not found");
            ctx.status(404);
        }else{
            String body = ctx.body();
            Gson gson = new Gson();
            Account account = gson.fromJson(body, Account.class);
            account.setAccountId(id);
            this.accountService.updateAccount(account);
            Account updatedAccount = this.accountService.getAccountById(cid, id);
            Gson upGson = new Gson();
            String upDatedJSON = upGson.toJson(updatedAccount);
            ctx.result(upDatedJSON);
            ctx.status(200);
        }


    };

    public Handler deleteAccountHandler = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("aid"));
        int cid = Integer.parseInt(ctx.pathParam("id"));
        Account lfAccount = this.accountService.getAccountById(cid, id);
        boolean deleted = this.accountService.deleteAccount(id);
        if(deleted && lfAccount != null){
            ctx.result("Account was deleted");
            ctx.status(200);
        }else{
            ctx.result("Could not delete");
            ctx.status(404);
        }
    };


}
