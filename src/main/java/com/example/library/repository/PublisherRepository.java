package com.example.library.repository;

import com.example.library.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Optional<Publisher> findByNameAndLanguage(String name, String language);
    Optional<Publisher> getById(long id);
}
