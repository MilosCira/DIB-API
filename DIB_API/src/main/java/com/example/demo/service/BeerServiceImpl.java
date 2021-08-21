package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Beer;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BeerRepository;

@Service
public class BeerServiceImpl implements BeerService {

	@Autowired
	BeerRepository beerRepository;

	@Override
	public List<Beer> findAll() {
		// TODO Auto-generated method stub
		List<Beer> beerList = beerRepository.findAll();

		if (beerList.size() > 0) {
			return beerList;
		} else {
			return new ArrayList<Beer>();
		}

	}

	@Override
	public Beer findById(Long id) {
		// TODO Auto-generated method stub

		return this.beerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Beer not found with id :" + id));
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		beerRepository.deleteById(id);
	}

	@Override
	public Long numberOfRows() {
		return beerRepository.count();
	}

	@Override
	public void updateBeer(Beer beer) {

		beerRepository.saveAndFlush(beer);
	}

	@Override
	public void save(List<Beer> beers) {
		// TODO Auto-generated method stub

		beerRepository.saveAll(beers);

	}

}
