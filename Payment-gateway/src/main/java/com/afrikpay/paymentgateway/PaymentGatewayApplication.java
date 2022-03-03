package com.afrikpay.paymentgateway;

import com.afrikpay.paymentgateway.kernel.PgKernel;
import com.afrikpay.paymentgateway.kernel.PgKernelFactory;
import com.afrikpay.paymentgateway.signal.payloaddef.EncryptRequestParameter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rkernel.KernelFactory;
import rkernel.signal.BasicSignal;

@SpringBootApplication
public class PaymentGatewayApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PaymentGatewayApplication.class, args);
    }

    @Override
    public synchronized void run(String... args) {
        try {
            loadKernel();
            // send signal to generate key
            sendRequestTest();
        }catch(Exception e){
            e.printStackTrace();
            KernelFactory.getInstance(PgKernel.class).dispatchLogException(e);
        }
    }

    public synchronized void loadKernel(){
        KernelFactory.getInstance(PgKernel.class).load();
    }

    public synchronized void sendRequestTest() throws JsonProcessingException {
        EncryptRequestParameter genKeyRq = new EncryptRequestParameter();
        genKeyRq.setAlgo("aes");
        genKeyRq.setMode("CBC");
        genKeyRq.setPadding("PKCS5Padding");
        genKeyRq.setKeySize(256);
        ObjectMapper mapper = new ObjectMapper();
        String genKeyRqStr = mapper.writeValueAsString(genKeyRq);
        BasicSignal<String> genKeySignal = new BasicSignal<>("security.encryption.genkey.sym", genKeyRqStr);
        String keySpecicication = (String) KernelFactory.getInstance(PgKernel.class).dispatchSignal(genKeySignal);
        System.out.println("Generated key");
        System.out.println(keySpecicication);
    }
}
