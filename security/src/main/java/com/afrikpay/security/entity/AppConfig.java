package com.afrikpay.security.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class AppConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false, updatable = false)
    private String appID;
    @Column(unique = true)
    private String name;
    private String privateKey;
    private String publicKey;
    private String secretKey;
    private String ivKey;
    private String encryptionAlgo;
    private String signAlgo;
    private boolean state;
    private String emails;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToOne(mappedBy = "appConfig")
    private WhiteList whiteList;

    public AppConfig() {
        createdAt = new Date();
        updatedAt = new Date();
        state = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getIvKey() {
        return ivKey;
    }

    public void setIvKey(String ivKey) {
        this.ivKey = ivKey;
    }

    public String getEncryptionAlgo() {
        return encryptionAlgo;
    }

    public void setEncryptionAlgo(String encryptionAlgo) {
        this.encryptionAlgo = encryptionAlgo;
    }

    public String getSignAlgo() {
        return signAlgo;
    }

    public void setSignAlgo(String signAlgo) {
        this.signAlgo = signAlgo;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public WhiteList getWhiteList() {
        return whiteList;
    }

    public AppConfig setWhiteList(WhiteList whiteList) {
        this.whiteList = whiteList;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppConfig appConfig = (AppConfig) o;
        return id == appConfig.id && state == appConfig.state && appID.equals(appConfig.appID) && name.equals(appConfig.name) && Objects.equals(privateKey, appConfig.privateKey) && Objects.equals(publicKey, appConfig.publicKey) && Objects.equals(secretKey, appConfig.secretKey) && Objects.equals(ivKey, appConfig.ivKey) && Objects.equals(encryptionAlgo, appConfig.encryptionAlgo) && Objects.equals(signAlgo, appConfig.signAlgo) && Objects.equals(emails, appConfig.emails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appID, name, privateKey, publicKey, secretKey, ivKey, encryptionAlgo, signAlgo, state, emails);
    }
}
