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

    public ClientService(Connection connection) throws SQLException {
        createSt = connection.prepareStatement(
                "INSERT INTO client (name) VALUES(?)",
                Statement.RETURN_GENERATED_KEYS
        );

        getByIdSt = connection.prepareStatement(
                "SELECT name FROM client WHERE id = ?"
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

        try (ResultSet generatedKeys = createSt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id = generatedKeys.getLong("id");
            } else {
                throw new SQLException("Invalid value 'id' entered.");
            }
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

        setNameSt.setString(1, name);
        setNameSt.setLong(2, id);

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
