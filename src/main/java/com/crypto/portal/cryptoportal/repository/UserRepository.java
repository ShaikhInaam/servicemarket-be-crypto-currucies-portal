package com.crypto.portal.cryptoportal.repository;

import com.crypto.portal.cryptoportal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUsernameAndUserPass(String username, String password);

    UserEntity findByUsername(String username);
}
