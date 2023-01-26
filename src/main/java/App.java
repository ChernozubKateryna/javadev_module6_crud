import com.goit.feature.client.Client;
import com.goit.feature.client.ClientService;
import com.goit.feature.database.Database;
import com.goit.feature.database.DatabaseInitService;

import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException {
        Database database = Database.getInstance();

        new DatabaseInitService().initDb(database);

        ClientService clientService = new ClientService(database.getConnection());

        List<Client> clientList = clientService.listAll();
        System.out.println("clientList = " + clientList);

        System.out.println("Client by id = 4 = " + clientService.getById(4));

        clientService.create("Avtory");

        List<Client> clientListWithNewClient = clientService.listAll();
        System.out.println("clientList = " + clientListWithNewClient);

        clientService.setName(6, "Update name");

        List<Client> clientListWithUpdateLastClient = clientService.listAll();
        System.out.println("clientList = " + clientListWithUpdateLastClient);

        clientService.deleteById(6);

        List<Client> lastClientList = clientService.listAll();
        System.out.println("clientList = " + lastClientList);
    }
}
