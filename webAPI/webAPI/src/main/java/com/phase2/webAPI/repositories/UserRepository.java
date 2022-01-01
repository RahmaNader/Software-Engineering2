package com.phase2.webAPI.repositories;

import com.phase2.webAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUserName(String userName);
    boolean existsByUserNameAndPassword(String userName, String password);
    User findAllByUserName(String username);
    boolean existsByToken(int token);
    User findAllByToken(int token);
}
