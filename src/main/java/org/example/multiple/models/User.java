package org.example.multiple.models;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NaturalId
  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;


  @Column(name = "name")
  private String name;

  @ManyToMany(cascade = {CascadeType.MERGE})
  @JoinTable(name = "user_role",
          joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private Set<RoleUser> roleUsers = new LinkedHashSet<>();


  public User() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<RoleUser> getRoleUsers() {
    return roleUsers;
  }

  public void setRoleUsers(Set<RoleUser> roleUsers) {
    this.roleUsers = roleUsers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User user)) return false;

    if (!getId().equals(user.getId())) return false;
    if (!getEmail().equals(user.getEmail())) return false;
    if (!getPassword().equals(user.getPassword())) return false;
    if (!getName().equals(user.getName())) return false;
    return getRoleUsers().equals(user.getRoleUsers());
  }

  @Override
  public int hashCode() {
    int result = getId().hashCode();
    result = 31 * result + getEmail().hashCode();
    result = 31 * result + getPassword().hashCode();
    result = 31 * result + getName().hashCode();
    result = 31 * result + getRoleUsers().hashCode();
    return result;
  }
}
