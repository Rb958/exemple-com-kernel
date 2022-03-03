package dbgateway.config.def;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "databasedef")
@XmlAccessorType(XmlAccessType.FIELD)
public class DatabaseConf {
    @XmlElementWrapper(name = "connections")
    @XmlElement(name = "connection")
    private List<ConnectionProperties> connection;

    public DatabaseConf() {
        connection = new ArrayList<>();
    }

    public List<ConnectionProperties> getConnection() {
        return connection;
    }

    public DatabaseConf setConnection(List<ConnectionProperties> connection) {
        this.connection = connection;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatabaseConf that = (DatabaseConf) o;
        return Objects.equals(connection, that.connection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connection);
    }

    public void addConnnection(ConnectionProperties connectionProperties) {
        if (!connection.contains(connectionProperties)){
            connection.add(connectionProperties);
        }
    }

    public void removeConnection(ConnectionProperties connectionProperties) {
        connection.remove(connectionProperties);
    }
}
