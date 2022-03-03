package dbgateway.service;

import dbgateway.config.ConfigManager;
import dbgateway.config.DBConnectionManager;
import dbgateway.config.SessionManager;

public class DatabaseService {

    private final SessionManager sessionManager;
    private final ConfigManager configManager;
    private final DBConnectionManager dbConnectionManager;

    public DatabaseService(){
        this.sessionManager = new SessionManager();
        this.configManager = new ConfigManager();
        this.dbConnectionManager = new DBConnectionManager();
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public DBConnectionManager getDbConfigManager() {
        return dbConnectionManager;
    }
}
