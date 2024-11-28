package com.example.SercuitywithRest.Repository;

import com.example.SercuitywithRest.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel,Long > {
    Optional<UserModel>findByEmail(String email);
}
