package com.example.SercuitywithRest.Repository;

import com.example.SercuitywithRest.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel,Long > {
    Optional<UserModel> findUsername(String email);
}
