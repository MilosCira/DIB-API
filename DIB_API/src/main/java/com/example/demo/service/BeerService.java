package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Beer;

public interface BeerService {
    
	
	List<Beer> findAll();
	Beer findById(Long id);
	void deleteById(Long id);
	void save(List<Beer> beers);
	Long numberOfRows();
}
