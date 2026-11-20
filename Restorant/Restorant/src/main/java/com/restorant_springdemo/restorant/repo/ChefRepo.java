package com.restorant_springdemo.restorant.repo;

import com.restorant_springdemo.restorant.model.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefRepo extends JpaRepository<Chef,Long> {




}
