package dev.debride.daos;

import dev.debride.entities.Account;
import dev.debride.entities.Client;
import dev.debride.utils.ConnectionUtil;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ClientDaoPostgre implements ClientDAO{

    @Override
    public Client registerClient(Client client) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into Client (client_name) values (?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, client.getName());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int key = rs.getInt("client_id");
            client.setClientId(key);
            return client;

        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Client> getClients() {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from client";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Set<Client> clients = new HashSet<Client>();

            while(rs.next()){
                Client client = new Client();
                client.setClientId(rs.getInt("client_id"));
                client.setName(rs.getString("client_name"));

                clients.add(client);
            }
            return clients;

        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public Client getClientById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from client where client_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Client client = new Client();
            client.setClientId(rs.getInt("client_id"));
            client.setName(rs.getString("client_name"));

            return client;


        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public Client updateClient(Client client) {
        try(Connection conn = ConnectionUtil.createConnection()){

            String sql = "update client set client_name = ? where client_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, client.getName());
            ps.setInt(2, client.getClientId());
            ps.executeUpdate();
            return client;

        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteClientById(int id) {

        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "delete from client where client_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;

        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }
}
