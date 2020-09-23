package com.subwaytrip.app.model.repository;

import com.subwaytrip.app.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);

    User findByAccountAndPassword(String account, String password);

    User findByAccount(String account);

    User findByEmail(String email);

}
