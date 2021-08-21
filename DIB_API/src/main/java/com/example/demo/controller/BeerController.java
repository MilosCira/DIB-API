package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import com.example.demo.entity.Beer;
import com.example.demo.service.BeerService;

@CrossOrigin(origins = "*" , allowedHeaders = "*")
@RestController
@RequestMapping("/api/")

public class BeerController {

	@Autowired
	BeerService beerService;

	
	
	
	
    /**
     * This function returns a list of all beers
     * @param id
     * @return {@link HttpStatus.OK}
     */
	@GetMapping("beers")
	@ApiOperation("Returns all beers .")
	public List<Beer> getAll() {

		return beerService.findAll();
	}
	
	
	

	
     /**
      * This function returns a beer by id
      * @param id
      * @return {@link HttpStatus.OK}
      */
	@GetMapping("beers/{id}")
	@ApiOperation("Returns beer by id .")
	public Beer getById(@PathVariable("id") Long id) {

		return beerService.findById(id);
	}

	
	
	
    /**
     * This function delete a beer by id
     * @param id
     * @return {@link HttpStatus.OK}
     */
	@DeleteMapping("beers/{id}")
	@ApiOperation("Delete beer by id.")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		try {
			beerService.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Id is not in database your id= " + id + " try some else");
		}

		return ResponseEntity.status(HttpStatus.OK).body("You have successfully wiped the beer");
	}
	
	
	
	
	/**
     * Function for update a beer
     * @param id
     * @return {@link HttpStatus.OK}
     */
	@PutMapping("beer")
	public ResponseEntity<?> updateBeer(@RequestBody Beer beer){
		beerService.updateBeer(beer);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("You have successfully update a beer");
	}

	
	
	
	/**
	 *This function add in data beer
     * @param id
     * @return {@link HttpStatus.OK}
     */
	@PostMapping("beers")
	@ApiOperation("Add beer in database.")
	public ResponseEntity<?> post(@RequestBody Beer beers) {

        
		List<Beer> beer = new ArrayList<>();
		
		beer.add(beers);
		
		if (beerService.numberOfRows() + beer.size() > 10) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("MAX value in database is 10");
		}
		try {

			beerService.save(beer);

		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Same fields in database");
		}

		return ResponseEntity.status(HttpStatus.CREATED).body("You have successfully added a beer");

	}

}
