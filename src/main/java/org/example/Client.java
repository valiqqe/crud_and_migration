package org.example;

import lombok.Data;

@Data
public class Client {
    private Long id;
    private String name;
    public Client(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}