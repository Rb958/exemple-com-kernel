package com.afrikpay.security.service.encryption;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EncryptRequestParameter that = (EncryptRequestParameter) o;
        return privateKey == that.privateKey && publicKey == that.publicKey && keySize == that.keySize && Objects.equals(algo, that.algo) && Objects.equals(data, that.data) && Objects.equals(iv, that.iv) && Objects.equals(key, that.key) && Objects.equals(mode, that.mode) && Objects.equals(encryptMode, that.encryptMode) && Objects.equals(padding, that.padding);
    }

    @Override
    public int hashCode() {
        return Objects.hash(algo, privateKey, publicKey, data, iv, key, keySize, mode, encryptMode, padding);
    }

    @Override
    public String toString() {
        return "EncryptRequestParameter{" +
                "algo='" + algo + '\'' +
                ", privateKey=" + privateKey +
                ", publicKey=" + publicKey +
                ", data='" + data + '\'' +
                ", iv='" + iv + '\'' +
                ", key='" + key + '\'' +
                ", keySize=" + keySize +
                ", mode='" + mode + '\'' +
                ", encryptMode='" + encryptMode + '\'' +
                ", padding='" + padding + '\'' +
                '}';
    }
}
