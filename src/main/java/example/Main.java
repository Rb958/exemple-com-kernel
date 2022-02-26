package example;

import dbgateway.exception.DBConnectionConfigException;
import example.entity.Role;
import example.entity.User;
import example.kernel.ExampleKernelFactory;
import example.model.KeySpecicication;
import example.service.UserService;
import example.signal.EncryptionSignal;
import example.signal.payloaddef.EncryptRequestParameter;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExampleKernelFactory.getInstance().load();
        Thread.sleep(3000);

        Role role = new Role();
        role.setLabel("ROLE_ADMIN");
        Role role1 = new Role();
        role1.setLabel("ROLE_USER");

        User user = new User();
        user.setUsername("Admin");
        user.setPassword("admin");
        user.setRoles(Arrays.asList(role, role1));

        UserService userService = null;
        try {
            userService = new UserService();
        } catch (DBConnectionConfigException e) {
            e.printStackTrace();
        }

        User savedUser = userService.saveUser(user);
        System.out.println(savedUser);

        // send signal to generate key
        EncryptRequestParameter genKeyRq = new EncryptRequestParameter();
        genKeyRq.setAlgo("aes");
        genKeyRq.setMode("CBC");
        genKeyRq.setPadding("PKCS5Padding");
        genKeyRq.setKeySize(256);

        EncryptionSignal genKeySignal = new EncryptionSignal("gen-key", genKeyRq);
        KeySpecicication keySpecicication = (KeySpecicication) ExampleKernelFactory.getInstance().processSignal(genKeySignal);
        System.out.println(keySpecicication);

        // send Encryption signal
        EncryptRequestParameter encryptRq = new EncryptRequestParameter();
        encryptRq.setAlgo("aes");
        encryptRq.setData("Hello world");
        encryptRq.setIv("5524db914f09badb16f3c941426c8c1b");
        encryptRq.setKey("6e87590f1fccb32fa74435c5a3a0e39279415044a7a9ee7798b4ea11e1bd0e8e");
        EncryptionSignal encryptSignal = new EncryptionSignal("security-encrypt", encryptRq);
        String data = (String) ExampleKernelFactory.getInstance().processSignal(encryptSignal);

        System.out.println("Encrypted Data: "+ data);

        // send Encryption signal
        EncryptRequestParameter decryptRq = new EncryptRequestParameter();
        encryptRq.setAlgo("aes");
        encryptRq.setData("Hello world");
        encryptRq.setIv("5524db914f09badb16f3c941426c8c1b");
        encryptRq.setKey("6e87590f1fccb32fa74435c5a3a0e39279415044a7a9ee7798b4ea11e1bd0e8e");
        EncryptionSignal decryptSignal = new EncryptionSignal("security-encrypt", decryptRq);
        String data2 = (String) ExampleKernelFactory.getInstance().processSignal(decryptSignal);
        System.out.println("Decrypted data : " + data2);

        Exception e = new Exception("Test Logging");
        ExampleKernelFactory.getInstance().dispatchLogException(e);
    }
}
