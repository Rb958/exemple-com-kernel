package com.afrikpay.security.service.encryption;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class EncryptionParameter {


    public enum EncryptionMode {
        SYMMETRIC,
        ASYMMETRIC;
    }

    private EncryptionMode encryptionMode = EncryptionMode.SYMMETRIC;
    private String algo = "AES";
    private String mode = "CBC";
    private String padding = "PKCS5Padding";
    private IvParameterSpec iv;
    private boolean pubKey = false;
    private boolean privKey = false;
    private Key key;

    public EncryptionMode getAesMode() {
        return encryptionMode;
    }

    public EncryptionParameter setAesMode(String encryptionMode) {
        if ("asym".equalsIgnoreCase(encryptionMode)) {
            this.encryptionMode = EncryptionMode.ASYMMETRIC;
        } else {
            this.encryptionMode = EncryptionMode.SYMMETRIC;
        }
        return this;
    }

    public String getAlgo() {
        return algo;
    }

    public EncryptionParameter setAlgo(String algo) {
        if (algo != null){
            this.algo = algo;
        }
        return this;
    }

    public String getMode() {
        return mode;
    }

    public EncryptionParameter setMode(String mode) {
        if (mode != null){
            this.mode = mode;
        }
        return this;
    }

    public String getPadding() {
        return padding;
    }

    public EncryptionParameter setPadding(String padding) {
        if (padding != null){
            this.padding = padding;
        }
        return this;
    }

    public IvParameterSpec getIv() {
        return iv;
    }

    public EncryptionParameter setIv(String iv) throws DecoderException {
        if (iv != null) {
            this.iv = new IvParameterSpec(Hex.decodeHex(iv));
        }
        return this;
    }

    public boolean getPubKey() {
        return pubKey;
    }

    public EncryptionParameter setPubKey(boolean pubKey) {
        if (pubKey) {
            this.pubKey = true;
        }
        return this;
    }

    public boolean getPrivKey() {
        return privKey;
    }

    public EncryptionParameter setPrivKey(boolean privKey) {
        if(privKey) {
            this.privKey = true;
        }
        return this;
    }

    public Key getKey() {
        return key;
    }

    public EncryptionParameter setKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException, EncryptionParameterExeption, DecoderException {
        if (getAesMode() == EncryptionMode.ASYMMETRIC){
            if (!privKey && !pubKey){
                throw new EncryptionParameterExeption("You must specify which type of key has been supplied in the \"key\" parameter.");
            }
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Hex.decodeHex(key));
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(Hex.decodeHex(key));
            KeyFactory privkf = KeyFactory.getInstance(algo);
            KeyFactory pubKf = KeyFactory.getInstance(algo);
            if (pubKey){
                this.key = pubKf.generatePublic(pubKeySpec);
            }else{
                this.key = privkf.generatePrivate(keySpec);
            }
        }else {
            this.key = (key != null) ? new SecretKeySpec(Hex.decodeHex(key), algo) : null;
        }
        return this;
    }

    public String buildCipher(){
        StringBuilder sb = new StringBuilder();
        sb.append(algo);
        if (!mode.isEmpty()){
            sb.append("/");
            sb.append(mode);
        }
        if (!padding.isEmpty()){
            sb.append("/");
            sb.append(padding);
        }
        return sb.toString();
    }
}
