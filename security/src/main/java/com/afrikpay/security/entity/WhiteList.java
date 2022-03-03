package com.afrikpay.security.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
public class WhiteList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne()
    private AppConfig appConfig;
    @OneToMany()
    @JoinTable(
        joinColumns = {@JoinColumn(name = "white_list_id", nullable = false)},
        inverseJoinColumns = {@JoinColumn(name = "terminal_id", nullable = false)}
    )
    private Collection<Terminal> terminals;

    public WhiteList() {
        terminals = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public WhiteList setId(long id) {
        this.id = id;
        return this;
    }

    public Collection<Terminal> getTerminals() {
        return terminals;
    }

    public WhiteList setTerminals(Collection<Terminal> terminals) {
        this.terminals = terminals;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WhiteList whiteList = (WhiteList) o;
        return id == whiteList.id && Objects.equals(terminals, whiteList.terminals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, terminals);
    }
}
