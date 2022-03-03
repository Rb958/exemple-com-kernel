package dbgateway.manager;

import dbgateway.config.SessionManager;
import dbgateway.dao.DBRepository;
import dbgateway.exception.DBConnectionConfigException;
import dbgateway.exception.RepositoryException;
import org.hibernate.Session;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class AbstractManager {
    private final Session session;

    public AbstractManager(String databaseName, List<Class<?>> entityClassList) throws DBConnectionConfigException {
        SessionManager sessionManager = new SessionManager();
        session = sessionManager.getConnection(databaseName, entityClassList);
    }

    protected DBRepository getRepository(String fullQualifyClassName) throws RepositoryException {
        try {
            Class<?> rClass = Class.forName(fullQualifyClassName);
            Constructor<?> constructor = rClass.getConstructor(Session.class);
            return (DBRepository) constructor.newInstance(session);
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    protected Session getSession(){
        return session;
    }
}