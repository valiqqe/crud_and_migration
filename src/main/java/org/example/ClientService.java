package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private Connection connection;
    private PreparedStatement createByName;
    private PreparedStatement findLastClient;
    private PreparedStatement findNameById;
    private PreparedStatement updateName;
    private PreparedStatement deleteById;
    private PreparedStatement selectAll;

    public ClientService(Connection connection){
        try {
            this.connection = connection;
            this.createByName = connection.prepareStatement("INSERT INTO client (name) VALUES (?)");
            this.findLastClient = connection.prepareStatement("SELECT MAX(id) as maxId FROM client");
            this.findNameById = connection.prepareStatement("SELECT name FROM client WHERE id = ?");
            this.updateName = connection.prepareStatement("UPDATE client SET name = ? WHERE id = ?");
            this.deleteById = connection.prepareStatement("DELETE FROM client WHERE id = ?");
            this.selectAll = connection.prepareStatement("SELECT * FROM client");
        }catch(SQLException e) {
            System.out.println("Client service construction exception. Reason: " + e.getMessage());
        }
    }
    public long createClient(String name){
        if (name.length() < 3 || name.length() > 999) {
            throw new IllegalArgumentException("The name must be between 3 and 999 characters long");
        }
        try {
            this.createByName.setString(1, name);
            this.createByName.executeUpdate();
            return findMaxId();
        }catch(SQLException e) {
            System.out.println("Insert order exception. " + e.getMessage());
            return -1;
        }
    }
    public long findMaxId(){
        try(ResultSet rs = this.findLastClient.executeQuery()){
            rs.next();
            return rs.getLong("maxId");
        } catch (SQLException e) {
            System.out.println("Select maxId exception. " + e.getMessage());
        }
        return -1;
    }
    public String getById(long id){
        try{
            this.findNameById.setLong(1, id);
            try(ResultSet rs = this.findNameById.executeQuery()){
                if(rs.next()){
                    return rs.getString("name");
                }
            }catch(SQLException e) {
                System.out.println("Find name by id exception. " + e.getMessage());
            }
        }catch(SQLException e) {
            System.out.println("Find name by id exception. " + e.getMessage());
        }
        return null;
    }
    public void setName(long id, String name){
        try{
            if(name.length()<= 2 && name.length()>=1000){
                throw new IllegalArgumentException("Довжина імені повинна бути в діапазоні від 3 до 999 символів");
            }
            this.updateName.setString(1, name);
            this.updateName.setLong(2, id);
            this.updateName.executeUpdate();
            if(!getById(id).equals(name)){
                throw new RuntimeException("name update error by id: " + id);
            }
        }catch(SQLException e) {
            System.out.println("Update name by id exception. " + e.getMessage());
        }
    }
    public void deleteById(long id){
        try {
            this.deleteById.setLong(1, id);
            deleteById.executeUpdate();
            if(getById(id) == null){
                System.out.println("Client deleted correctly");
            }else throw new RuntimeException("Error deleting client by id: " + id);
        }catch(SQLException e) {
            System.out.println("Delete by id exception. Reason: " + e.getMessage());
        }
    }
    public List<Client> listAll(){
        List<Client> resultList = new ArrayList<>();
        try(ResultSet rs = this.selectAll.executeQuery()){
            while (rs.next()){
                Client client = new Client(
                        rs.getLong("id"),
                        rs.getString("name"));
                resultList.add(client);
            }
        }catch(SQLException e) {
            System.out.println("Select all exception. Reason: " + e.getMessage());
        }
        return  resultList;
    }
}
