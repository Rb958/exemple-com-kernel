package dbgateway.config;

import dbgateway.config.def.ConnectionProperties;
import dbgateway.config.def.DatabaseConf;
import dbgateway.exception.FileManagerException;
import dbgateway.utils.files.XmlFileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class DBConnectionManager {

    protected static DatabaseConf databaseConf;

    protected final File documentroot = Paths.get(".").toFile();
    protected  String dbConfPath = "/databases/";

    public DBConnectionManager() {
        try {
            if (!XmlFileManager.getInstance(documentroot).pathExist(getDBConfPath())) {
                databaseConf = new DatabaseConf();
                flush(databaseConf);
            }
            databaseConf = getDBConfiguration();
        } catch (IOException | FileManagerException e) {
            e.printStackTrace();
        }
    }

    private DatabaseConf getDBConfiguration() throws IOException, FileManagerException {
        return (DatabaseConf) XmlFileManager.getInstance(documentroot)
                .getFileContent(getDBConfPath());
    }

    private void flush(DatabaseConf databaseConf) {
        try {
            XmlFileManager.getInstance(documentroot).writeFileContent(databaseConf, getDBConfPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Collection<ConnectionProperties> retrieveDatabases(){
        final Collection<ConnectionProperties> connections = new ArrayList<>();
        databaseConf.getConnection().forEach(connectionProperties -> {
            if (!connections.contains(connectionProperties)) {
                connections.add(connectionProperties);
            }
        });
        return connections;
    }

    public Path getDBConfPath(){
        if (!Files.exists(Paths.get(documentroot.getPath().concat(dbConfPath)))){
            try {
                Files.createDirectories(Paths.get(documentroot.getPath().concat(dbConfPath)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Paths.get(documentroot.getPath().concat("/databases/dbconf.xml"));
    }

    public void addDBConnection(ConnectionProperties connectionProperties) {
        databaseConf.addConnnection(connectionProperties);
        flush(databaseConf);
    }

    public void removeDBConnection(ConnectionProperties connectionProperties) {
        databaseConf.removeConnection(connectionProperties);
        flush(databaseConf);
    }

    public boolean hasDataBaseConfig(String databaseName) {
        return databaseConf.getConnection().stream()
                .anyMatch(connectionProperties -> connectionProperties.getDatabaseName().equals(databaseName));
    }

    public ConnectionProperties getDBConnection(String databaseName){
        Optional<ConnectionProperties> optionalDatabaseConf = databaseConf.getConnection().stream()
                .filter(connectionProperties -> connectionProperties.getDatabaseName().equals(databaseName))
                .findAny();
        return optionalDatabaseConf.orElse(null);
    }
}
