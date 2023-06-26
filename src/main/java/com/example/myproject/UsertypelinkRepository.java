package com.example.myproject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsertypelinkRepository extends JpaRepository<Usertypelink, String> {
//    @Query(value = "select * from usertypelinks where");
}