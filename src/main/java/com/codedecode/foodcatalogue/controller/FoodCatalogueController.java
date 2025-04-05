package com.codedecode.foodcatalogue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.foodcatalogue.dto.FoodCataloguePage;
import com.codedecode.foodcatalogue.dto.FoodItemDTO;
import com.codedecode.foodcatalogue.service.FoodCatalogueService;

@RestController
@RequestMapping("/foodCatalogue")
@CrossOrigin
public class FoodCatalogueController {

	@Autowired
	FoodCatalogueService footCatalogueService;
	
	@PostMapping("/addFoodItem")
	public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO){
		FoodItemDTO foodItemDTOSaved = footCatalogueService.addFoodItem(foodItemDTO);
		return new ResponseEntity<>(foodItemDTOSaved, HttpStatus.CREATED);
	}
	
	@GetMapping("/fetchRestaurantAndFoodItemById/{restaurantId}")
	public ResponseEntity<FoodCataloguePage> fetchRestaurantDetailsWithFoodMenu(@PathVariable int restaurantId){
		FoodCataloguePage foodCataloguePage = footCatalogueService.fetchFoodCataloguePageDetails(restaurantId);
		return new ResponseEntity<>(foodCataloguePage, HttpStatus.OK);
	}
}
