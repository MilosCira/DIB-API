package com.example.demo.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import com.example.demo.entity.Beer;
import com.example.demo.service.BeerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/")

public class BeerController {

	@Autowired
	BeerService beerService;

	// This function returns a list of all beers

	@GetMapping("beers")
	@ApiOperation("Returns all beers .")
	public List<Beer> getAll() {

		return beerService.findAll();
	}

	// This function returns a beer by id

	@GetMapping("beers/{id}")
	@ApiOperation("Returns beer by id .")
	public Beer getById(@PathVariable("id") Long id) {

		return beerService.findById(id);
	}

	// This function delete a beer by id

	@DeleteMapping("beers/{id}")
	@ApiOperation("Delete beer by id.")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		try {
			beerService.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Id is not in database your id= " + id + " try some else");
		}

		return ResponseEntity.status(HttpStatus.OK).body("Success delete beer");
	}

	// This function add in data beer

	@PostMapping("beers")
	@ApiOperation("Add beer in database.")
	public ResponseEntity<?> post(@RequestBody List<Beer> beers) {

		if (beerService.numberOfRows() + beers.size() > 10) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("MAX value in database is 10");
		}
		try {

			beerService.save(beers);

		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Same fields in database");
		}

		return ResponseEntity.status(HttpStatus.OK).body("Success add a beer");

	}

}
