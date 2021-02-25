package dev.debride.entities;

public class Client {
    private int clientId;
    private String name;

    public Client(){}

    public Client(int clientId, String name) {
        this.clientId = clientId;
        this.name = name;
    }

    public int getClientId() {
        return this.clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", Name='" + name + '\'' +
                '}';
    }
}
