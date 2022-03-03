package com.afrikpay.security.service.encryption;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import java.security.*;

public class KeyService {
    private final EncryptionParameter parameter;

    public KeyService(EncryptionParameter parameter) {
        this.parameter = parameter;
    }

    public KeySpecicication generateAsymKey(String algo, int keysize) throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(algo);
        kpg.initialize(keysize);

        KeyPair keyPair = kpg.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        String privKey = Hex.encodeHexString(privateKey.getEncoded());
        String pubKey = Hex.encodeHexString(publicKey.getEncoded());
        return new KeySpecicication(privKey, pubKey);
    }

    public KeySpecicication generateSymKey(String algo, int keySize) throws NoSuchAlgorithmException, NoSuchPaddingException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algo);
        keyGenerator.init(keySize);
        String key = Hex.encodeHexString(keyGenerator.generateKey().getEncoded());

        Cipher cipher = Cipher.getInstance(parameter.buildCipher());
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] iv = new byte[cipher.getBlockSize()];
        sr.nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        KeySpecicication keySpec = new KeySpecicication();
        keySpec.setIv(Hex.encodeHexString(ivParameterSpec.getIV()));
        keySpec.setSecretKey(key);
        return keySpec;
    }


    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class KeySpecicication{
        private String privateKey;
        private String publicKey;
        private String secretKey;
        private String iv;

        public KeySpecicication() {}

        public KeySpecicication(String privateKey, String publicKey) {
            this.privateKey = privateKey;
            this.publicKey = publicKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public void setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }

        public void setPublicKey(String publicKey) {
            this.publicKey = publicKey;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public String getIv() {
            return iv;
        }

        public void setIv(String iv) {
            this.iv = iv;
        }
    }
}
