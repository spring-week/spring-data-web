package com.revature.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Food;
import com.revature.repository.FoodRepository;

// This class DEPENDS on the repository layer

@Service // Component -> Repository, Service, Controller (sterotype annotations)
public class FoodService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private FoodRepository foodRepo;

	@Autowired
	public FoodService(FoodRepository foodRepo) {
		this.foodRepo = foodRepo;
	}

	// findAllFoods() returns a list of food items in the DB
	public List<Food> findAllFoods() {

		log.info("retrieveing all foods from the DB");
		// we call the findAll() method from the dao (FoodRepository)
		return foodRepo.findAll();
	}

	// add a food, return the food object with generated PK
	public Food addFood(Food food) {

		Food savedFood = foodRepo.save(food);
		log.info("adding {} to the database", food.getDishName());
		return savedFood;
	}
	
	public Food findFoodByDishName(String dishName) {

		Optional<Food> possibleFood = foodRepo.findByDishNameIgnoreCase(dishName);

		if (possibleFood.isPresent()) {
			log.info("retrevied {}", dishName);
			return possibleFood.get();
		} else {
			log.warn("Could not retrieve {}", dishName);
			return null;
		}
	}

	public void deleteById(int id) {
		log.info("deleting food with id {}", id);
		// the CrudRepository's deleteById() method has void return type
		foodRepo.deleteById(id);
	}
}
