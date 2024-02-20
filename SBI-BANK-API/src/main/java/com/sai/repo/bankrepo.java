package com.sai.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sai.model.bankapplications;

@Repository
public interface bankrepo extends JpaRepository<bankapplications, Integer> {

}
