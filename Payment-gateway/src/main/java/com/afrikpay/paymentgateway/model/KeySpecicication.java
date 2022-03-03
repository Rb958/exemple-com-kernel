package com.afrikpay.paymentgateway.model;

public class KeySpecicication{
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

    @Override
    public String toString() {
        return "KeySpecicication{" +
                "privateKey='" + privateKey + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", iv='" + iv + '\'' +
                "}\n";
    }
}