package com.afrikpay.security.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Parameters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String paramKey;
    private String description;
    @Column(nullable = false)
    private String paramValue;

    public Parameters() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameters that = (Parameters) o;
        return id == that.id && paramKey.equals(that.paramKey) && paramValue.equals(that.paramValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paramKey, paramValue);
    }
}
