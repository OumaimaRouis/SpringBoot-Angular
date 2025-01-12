package com.gestion3.repository;

import com.gestion3.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    public AppUser findAppUserByUsername(String username);


}