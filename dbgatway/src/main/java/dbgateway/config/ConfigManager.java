package dbgateway.config;

import dbgateway.config.def.ConnectionProperties;
import org.hibernate.HibernateException;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.hibernate.tool.schema.TargetType;
import org.hibernate.tool.schema.spi.DelayedDropRegistryNotAvailableImpl;
import org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigManager {

    public Map<String, String> getDBSettings(ConnectionProperties connectionProperties){
        HashMap<String, String> settings = new HashMap<>();
        settings.put("hibernate.connection.username", connectionProperties.getUsername());
        settings.put("hibernate.connection.password", connectionProperties.getPassword());
        settings.put("hibernate.connection.pool_size", String.valueOf(connectionProperties.getPoolSize()));
        settings.put("hibernate.connection.autocommit", String.valueOf(connectionProperties.isAutoCommit()));
        settings.put("hibernate.connection.url", connectionProperties.getConnectionUrl());
        settings.put("hibernate.connection.driver_class", connectionProperties.getDriverClass());
        settings.put("hibernate.dialect", connectionProperties.getDialect());
        settings.put("hibernate.autoReconnect", String.valueOf(connectionProperties.isAutoReconnect()));
        settings.put("hibernate.requireSSL", String.valueOf(connectionProperties.isTls()));
        settings.put("hibernate.verifyServerCertificate", String.valueOf(connectionProperties.isVerifyCert()));
        settings.put("hbm2ddl.auto", String.valueOf(connectionProperties.getAuto()));
        return settings;
    }

    /**
     * Create database table using Entity classes
     * @param classes Collection of Entities classes
     * @param connectionProperties Instance of ConnectionProperties which represent the connection properties of database
     */
    public void initDb(List<Class<?>> classes, ConnectionProperties connectionProperties){
        try {
            Map<String, String> settings = getDBSettings(connectionProperties);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(settings)
                    .build();
            MetadataSources metadataSources = new MetadataSources(serviceRegistry);
            classes.forEach(metadataSources::addAnnotatedClass);
            EnumSet<TargetType> set = EnumSet.of(TargetType.DATABASE);
            DelayedDropRegistryNotAvailableImpl ddrNai = new DelayedDropRegistryNotAvailableImpl();
            SchemaManagementToolCoordinator.process(metadataSources.buildMetadata(), serviceRegistry, settings, ddrNai);
            SchemaUpdate schemaExport = new SchemaUpdate();
            schemaExport.setOverrideOutputFileContent();
            schemaExport.setOutputFile("test_db.sql");
            schemaExport.execute(set,metadataSources.buildMetadata());
        } catch (HibernateException e){
            e.printStackTrace();
//            KernelFactory.getInstance().getKernel().dispatchLogException(e);
        }
    }
}
