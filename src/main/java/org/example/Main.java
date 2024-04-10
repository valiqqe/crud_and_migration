package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


public class Main {
    public static void main(String[] args) {
        ClientService service = new ClientService(PostgresConnection.getInstance().getPostgresConnection());
        System.out.println("Client created.ID: " + service.createClient("Hiii"));
        System.out.println("Name by id 11 = " + service.getById(11));
        service.setName(11, "Valik Didok");
        service.listAll().forEach((a)-> System.out.println(a));
    }
}