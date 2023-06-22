package com.example.myproject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credential, String> {
}