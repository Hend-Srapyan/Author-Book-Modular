package com.example.authorbookweb.repository;

import com.example.authorbookcommon.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer>, JpaSpecificationExecutor<Author> {

    List<Author> findAllByNameContainingOrSurnameContaining(String name, String surname);
}
