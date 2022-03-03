package dbgateway.config.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String label;
    private Date createdAt;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = CascadeType.ALL)
    private List<User> users;

    public long getId() {
        return id;
    }

    public Role setId(long id) {
        this.id = id;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public Role setLabel(String label) {
        this.label = label;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Role setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id && Objects.equals(label, role.label) && Objects.equals(createdAt, role.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, createdAt);
    }

    public List<User> getUsers() {
        return users;
    }

    public Role setUsers(List<User> users) {
        this.users = users;
        return this;
    }
}
