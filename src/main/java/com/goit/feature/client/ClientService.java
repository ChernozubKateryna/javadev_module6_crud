package com.goit.feature.client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private PreparedStatement createSt;
    private PreparedStatement getByIdSt;
    private PreparedStatement setNameSt;
    private PreparedStatement deleteByIdSt;
    private PreparedStatement getAllSt;
    private PreparedStatement selectMaxIdSt;

    public ClientService(Connection connection) throws SQLException {
        createSt = connection.prepareStatement(
                "INSERT INTO client (name) VALUES(?)",
                Statement.RETURN_GENERATED_KEYS
        );

        getByIdSt = connection.prepareStatement(
                "SELECT name FROM client WHERE id = ?"
        );

        selectMaxIdSt = connection.prepareStatement(
                "SELECT max(id) AS maxId FROM client"
        );

        setNameSt = connection.prepareStatement(
                "UPDATE client SET name = ? WHERE id = ?"
        );

        deleteByIdSt = connection.prepareStatement(
                "DELETE FROM client WHERE id = ?"
        );

        getAllSt = connection.prepareStatement(
                "SELECT id, name FROM client"
        );
    }

    public long create(String name) throws SQLException {
        if (name.length() <= 2) {
            throw new IllegalArgumentException("Name too short.");
        }
        if (name.length() >= 1000) {
            throw new IllegalArgumentException("Name too long.");
        }

        createSt.setString(1, name);
        createSt.executeUpdate();

        long id;

        try(ResultSet rs = selectMaxIdSt.executeQuery()) {
            rs.next();
            id = rs.getLong("maxId");
        }

        return id;
    }

    public String getById(long id) throws SQLException {
        getByIdSt.setLong(1, id);

        try(ResultSet rs = getByIdSt.executeQuery()) {
            if (!rs.next()) {
                return null;
            }

            Client newClient = new Client();
            newClient.setId(id);
            newClient.setName(rs.getString("name"));

            return newClient.getName();
        }
    }

    public void setName(long id, String name) throws SQLException {
        if (name.length() <= 2) {
            throw new IllegalArgumentException("Name too short.");
        }
        if (name.length() >= 1000) {
            throw new IllegalArgumentException("Name too long.");
        }

        setNameSt.setLong(1, id);
        setNameSt.setString(2, name);

        setNameSt.executeUpdate();
    }

    public void deleteById(long id) throws SQLException {
        deleteByIdSt.setLong(1, id);

        deleteByIdSt.executeUpdate();
    }

    public List<Client> listAll() {
        List<Client> clientList = new ArrayList<>();

        try(ResultSet rs = getAllSt.executeQuery()) {
            while (rs.next()) {
                Client client = new Client();

                client.setId(rs.getLong("id"));
                client.setName(rs.getString("name"));

                clientList.add(client);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return clientList;
    }
}
