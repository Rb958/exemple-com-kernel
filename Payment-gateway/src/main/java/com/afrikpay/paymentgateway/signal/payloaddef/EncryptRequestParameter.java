package com.afrikpay.paymentgateway.signal.payloaddef;

public class EncryptRequestParameter {
    private String algo;
    private boolean privateKey;
    private boolean publicKey;
    private String data;
    private String iv;
    private String key;
    private int keySize;
    private String mode;
    private String encryptMode;
    private String padding;


    public EncryptRequestParameter() {
        privateKey = false;
        publicKey = false;
    }

    public String getAlgo() {
        return algo;
    }

    public void setAlgo(String algo) {
        this.algo = algo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(boolean privateKey) {
        this.privateKey = privateKey;
    }

    public boolean isPublicKey() {
        return publicKey;
    }

    public void setPublicKey(boolean publicKey) {
        this.publicKey = publicKey;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getEncryptMode() {
        return encryptMode;
    }

    public void setEncryptMode(String encryptMode) {
        this.encryptMode = encryptMode;
    }

    public String getPadding() {
        return padding;
    }

    public void setPadding(String padding) {
        this.padding = padding;
    }

    public int getKeySize() {
        return keySize;
    }

    public void setKeySize(int keySize) {
        this.keySize = keySize;
    }
}
