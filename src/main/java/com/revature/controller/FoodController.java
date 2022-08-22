package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Food;
import com.revature.service.FoodService;

/**
 * The purpose of this class is to expose
 * service functionality at different endpoints for the client
 * to interact with and perform CRUD methods on our DB
 */

// @RestController automatically infers that the return type of all method will be an HTTP response
// aka ResponseBody
@RestController // @Controller is a TYPE of @Component... (a.k.a Stereotype Annotation)
@RequestMapping("/foods") // all functionality will be exposed at the /food endpoint 
public class FoodController {
	
	private FoodService foodService; // Spring IoC container knows to inject an instance of the service class
	
	@Autowired
	public FoodController(FoodService foodService) {
		super();
		this.foodService = foodService;
	}

	@GetMapping("/all") // GET request to list all food records ... http://localhost:5000/food/all
	public List<Food> findAllFoods() { // how does spring know to return it as an HTTP Response?
									   // ...Because this class is annotated with @RestController! 
		// call the service layer's findAllFoods() method...
		return foodService.findAllFoods();
	}
	
	@GetMapping("/{id}") // allows the client to send the request http://localhost:5000/api/foods/2
	public ResponseEntity<Food> findFoodById(@PathVariable("id") int id) {
		return ResponseEntity.ok(foodService.findById(id));
	}
	
	// POST request to add ... http://localhost:5000/food/add
	@PostMapping("/add") // this method takes in a Food object in JSON
	public Food addFood(@RequestBody Food food) { // https://stackoverflow.com/questions/39343340/spring-why-should-i-still-use-requestbody-when-my-class-is-already-annotated
		
		// when we take in the Food obj in the form of JSON at that endpoint in the Request Body
		// Jackson Databind (object mapper) will transform the JSON to a Java Object
		return foodService.addFood(food);
	}
	
	@DeleteMapping("/{id}")
	public void removeFood(@PathVariable("id") int id) {
		foodService.deleteById(id);
	}
}
