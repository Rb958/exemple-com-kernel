package dbgateway.config.def;

import jakarta.xml.bind.annotation.*;

import java.util.Objects;

@XmlRootElement(name = "connection")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConnectionProperties {
    @XmlElement(required = true)
    private String username;
    @XmlElement(required = true)
    private String password;
    @XmlElement(required = true)
    private String driverClass;
    @XmlElement(required = true)
    private String connectionUrl;
    @XmlElement(required = true)
    private String dialect;
    @XmlElement
    private boolean tls;
    @XmlElement
    private boolean verifyCert;
    @XmlElement
    private String certLocation;
    @XmlElement(defaultValue = "1")
    private int poolSize;
    @XmlElement
    private boolean autoCommit;
    @XmlElement
    private boolean autoReconnect;
    @XmlElement(defaultValue = "create")
    private String auto;
    @XmlAttribute(required = true)
    private String databaseName;

    public ConnectionProperties() {
    }

    public String getUsername() {
        return username;
    }

    public ConnectionProperties setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ConnectionProperties setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public ConnectionProperties setDriverClass(String driverClass) {
        this.driverClass = driverClass;
        return this;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public ConnectionProperties setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
        return this;
    }

    public String getDialect() {
        return dialect;
    }

    public ConnectionProperties setDialect(String dialect) {
        this.dialect = dialect;
        return this;
    }

    public boolean isTls() {
        return tls;
    }

    public ConnectionProperties setTls(boolean tls) {
        this.tls = tls;
        return this;
    }

    public boolean isVerifyCert() {
        return verifyCert;
    }

    public ConnectionProperties setVerifyCert(boolean verifyCert) {
        this.verifyCert = verifyCert;
        return this;
    }

    public String getCertLocation() {
        return certLocation;
    }

    public ConnectionProperties setCertLocation(String certLocation) {
        this.certLocation = certLocation;
        return this;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public ConnectionProperties setPoolSize(int poolSize) {
        this.poolSize = poolSize;
        return this;
    }

    public boolean isAutoCommit() {
        return autoCommit;
    }

    public ConnectionProperties setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
        return this;
    }

    public boolean isAutoReconnect() {
        return autoReconnect;
    }

    public ConnectionProperties setAutoReconnect(boolean autoReconnect) {
        this.autoReconnect = autoReconnect;
        return this;
    }

    public String getAuto() {
        return auto;
    }

    public ConnectionProperties setAuto(String auto) {
        this.auto = auto;
        return this;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public ConnectionProperties setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    public boolean checkConfiguration(){
        return databaseName != null &&
                username != null &&
                password != null &&
                driverClass != null &&
                connectionUrl != null &&
                dialect != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectionProperties that = (ConnectionProperties) o;
        return Objects.equals(connectionUrl, that.connectionUrl) && databaseName.equals(that.databaseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectionUrl, databaseName);
    }
}
