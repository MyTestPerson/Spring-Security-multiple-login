package org.example.multiple.repository;

import org.example.multiple.enam.RoleEnum;
import org.example.multiple.models.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface RoleRepository extends JpaRepository<RoleUser, Long> {

  RoleUser findByRoleEnum(RoleEnum roleEnum);

}
