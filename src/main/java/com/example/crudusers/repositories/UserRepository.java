package com.example.crudusers.repositories;


import com.example.crudusers.models.User2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User2, Integer> {
}
