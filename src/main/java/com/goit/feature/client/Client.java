package com.goit.feature.client;

import lombok.Data;

@Data
public class Client {
    private long id;
    private String name;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
