package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entity.Beer;


public interface BeerRepository extends JpaRepository<Beer,Long> {
  
}
