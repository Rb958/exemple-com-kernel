package com.afrikpay.security.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
public class Terminal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    private String description;
    private boolean state;
    private Date createdAt;
    private Date updatedAt;
    private Date lastActivity;
    @Column(unique = true, nullable = false)
    private String identity;
    @ManyToMany(mappedBy = "terminals")
    private Collection<WhiteList> appConfigs;

    public Terminal() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
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

    public Date getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Date lastActivity) {
        this.lastActivity = lastActivity;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Collection<WhiteList> getAppConfigs() {
        return appConfigs;
    }

    public Terminal setAppConfigs(Collection<WhiteList> appConfigs) {
        this.appConfigs = appConfigs;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Terminal terminal = (Terminal) o;
        return id == terminal.id && state == terminal.state && type.equals(terminal.type) && identity.equals(terminal.identity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, state, identity);
    }

}
