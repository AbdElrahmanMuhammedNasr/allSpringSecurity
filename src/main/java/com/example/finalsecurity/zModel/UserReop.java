package com.example.finalsecurity.zModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReop extends JpaRepository<Users,Long> {
    Users findByUserName(String userName);
}
