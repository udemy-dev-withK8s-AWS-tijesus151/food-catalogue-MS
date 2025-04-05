package com.codedecode.foodcatalogue.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codedecode.foodcatalogue.entity.FoodItem;

@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem, Integer>{
	
	List<FoodItem> findByRestaurantId(Integer restaurantId);
}
