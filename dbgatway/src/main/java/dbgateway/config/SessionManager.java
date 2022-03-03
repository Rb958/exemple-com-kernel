package dbgateway.config;

import dbgateway.config.def.ConnectionProperties;
import dbgateway.exception.DBConnectionConfigException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionManager {

    private static final HashMap<String, Session> sessionHashMap = new HashMap<>();
    private final DBConnectionManager dbConnectionManager;
    private final ConfigManager configManager;

    public SessionManager() {
        this.configManager = new ConfigManager();
        this.dbConnectionManager = new DBConnectionManager();
    }

    public Session getConnection(String databaseName, List<Class<?>> entityClasses) throws DBConnectionConfigException {
        if (!sessionHashMap.containsKey(databaseName)) {
            addConnection(databaseName, entityClasses);
        }
        return sessionHashMap.get(databaseName);
    }

    public void removeConnection(String databaseName){
        Session tmpSession = sessionHashMap.get(databaseName);
        tmpSession.close();
        sessionHashMap.remove(databaseName);
    }

    private Session addConnection(String databaseName, List<Class<?>> entityClasses) throws DBConnectionConfigException {

        if (dbConnectionManager.hasDataBaseConfig(databaseName)){
            ConnectionProperties connectionProperties = this.dbConnectionManager.getDBConnection(databaseName);
            if (!connectionProperties.checkConfiguration()){
                throw new DBConnectionConfigException();
            }
            Map<String, String> settings = this.configManager.getDBSettings(connectionProperties);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(settings)
                    .build();
            Configuration configuration = new Configuration();
            entityClasses.forEach(configuration::addAnnotatedClass);
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            Session session = sessionFactory.openSession();
            sessionHashMap.put(databaseName, session);
            return session;
        }else {
            return null;
        }
    }

    public Session addNewConnection(String databaseName, List<Class<?>> entityClasses) throws DBConnectionConfigException {
        return addConnection(databaseName, entityClasses);
    }

    public Collection<Session> getSessions(){
        return sessionHashMap.values();
    }

    public void closeAll(){
        sessionHashMap.forEach((key, session ) -> {
            if (session.isOpen()){
                session.close();
                sessionHashMap.remove(key);
            }
        });
    }
}
