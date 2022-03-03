package dbgateway.config;

import dbgateway.config.def.ConnectionProperties;
import dbgateway.config.entity.Role;
import dbgateway.config.entity.User;
import dbgateway.config.repository.RoleRepository;
import dbgateway.config.repository.UserRepository;
import dbgateway.exception.DBConnectionConfigException;
import dbgateway.service.DatabaseService;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


class DBGatewayTest {

    private static DatabaseService databaseService;

    @BeforeAll
    private static void init(){
        databaseService = new DatabaseService();
    }

    @Test
    void createDataBase(){

        ConnectionProperties connectionProperties = new ConnectionProperties();
        connectionProperties.setUsername("root")
                .setPassword("cm-uds-15sci0973")
                .setPoolSize(1)
                .setAutoCommit(true)
                .setDatabaseName("test_dbgateway")
                .setConnectionUrl("jdbc:mysql://localhost:3306/test_dbgateway")
                .setDriverClass("com.mysql.cj.jdbc.Driver")
                .setDialect("org.hibernate.dialect.MySQL8Dialect")
                .setAutoReconnect(true)
                .setTls(true)
                .setVerifyCert(false)
                .setAuto("update");
        List<Class<?>> classList = Arrays.asList(User.class, Role.class);
        try {
            // If the database schema does not exist, we create it using the initDb method of ConfigManager class.
            databaseService.getConfigManager().initDb(classList, connectionProperties);
            // If the connection with database doesn't exist in dbconf.xml file, we create it using addDBConnection method of DBConfigManager class
            databaseService.getDbConfigManager().addDBConnection(connectionProperties);

            // We get session
            Session session = databaseService.getSessionManager().getConnection(connectionProperties.getDatabaseName(), classList);

            Role role = new Role();
            role.setId(1);
            role.setLabel("ADMIN");

            User user = new User();
            user.setUsername("user 3")
                    .setPassword("passwrd 3")
                    .setRole(role);

            System.out.println("===========================================");

            UserRepository userRepo = new UserRepository(session);
            RoleRepository roleRepository = new RoleRepository(session);
//            roleRepository.save(role);
//            userRepo.save(user);

            List<User> users = userRepo.findAll();
            System.out.println(users);

            System.out.println("We have : " + (userRepo.countAll())+ " user(s) stored");
        } catch (DBConnectionConfigException e) {
            e.printStackTrace();
        }

    }
}
