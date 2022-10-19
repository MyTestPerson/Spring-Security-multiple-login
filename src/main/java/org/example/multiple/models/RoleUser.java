package org.example.multiple.models;

import org.example.multiple.enam.RoleEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class RoleUser implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @ManyToMany(mappedBy = "roleUsers", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
  private Set<User> users = new LinkedHashSet<>();

  @Enumerated(EnumType.STRING)
  @Column(name = "name", nullable = false, unique = true, length = 45, insertable = false, updatable = false)
  private RoleEnum roleEnum;


  public RoleUser() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }

  public RoleEnum getRoleEnum() {
    return roleEnum;
  }

  public void setRoleEnum(RoleEnum roleEnum) {
    this.roleEnum = roleEnum;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof RoleUser roleUser)) return false;

    if (!getId().equals(roleUser.getId())) return false;
    return getRoleEnum() == roleUser.getRoleEnum();
  }

  @Override
  public int hashCode() {
    int result = getId().hashCode();
    result = 31 * result + getRoleEnum().hashCode();
    return result;
  }
}