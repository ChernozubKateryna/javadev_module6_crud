package com.goit.feature.database;

import com.goit.feature.prefs.Prefs;
import org.flywaydb.core.Flyway;

public class DatabaseInitService {
    public void initDb(Database database) {
        String connectionUrl = new Prefs().getString(Prefs.DB_JDBC_CONNECTION_URL);

        Flyway flyway = Flyway
                .configure()
                .dataSource(connectionUrl, null, null)
                .load();

        flyway.migrate();
    }
}
