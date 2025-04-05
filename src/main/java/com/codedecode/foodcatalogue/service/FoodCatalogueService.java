package com.codedecode.foodcatalogue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.codedecode.foodcatalogue.dto.FoodCataloguePage;
import com.codedecode.foodcatalogue.dto.FoodItemDTO;
import com.codedecode.foodcatalogue.dto.Restaurant;
import com.codedecode.foodcatalogue.entity.FoodItem;
import com.codedecode.foodcatalogue.mapper.FoodItemMapper;
import com.codedecode.foodcatalogue.repo.FoodItemRepo;

@Service
public class FoodCatalogueService {

	@Autowired
	FoodItemRepo foodItemRepo;
	
	@Autowired
	RestTemplate restTemplate;

	public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
		FoodItem foodItem = FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO);
		FoodItem foodItemSaved = foodItemRepo.save(foodItem);
		FoodItemDTO foodItemDTOSaved = FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(foodItemSaved);
		return foodItemDTOSaved;
	}

	public FoodCataloguePage fetchFoodCataloguePageDetails(int restaurantId) {
		
		// food item list
		List<FoodItem> foodItemList = fetchFoodItemList(restaurantId);
		
		// restaurant details
		Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMS(restaurantId);
		
		return createFoodCataloguePage(foodItemList, restaurant);
	}

	private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
		FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
		foodCataloguePage.setFoodItemsList(foodItemList);
		foodCataloguePage.setRestaurant(restaurant);
		return foodCataloguePage;
	}

	private Restaurant fetchRestaurantDetailsFromRestaurantMS(int restaurantId) {
		return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/"+restaurantId, Restaurant.class);
		//return restTemplate.getForObject("http://RESTAURANTLISTING/fetchById/"+restaurantId, Restaurant.class);
	}

	private List<FoodItem> fetchFoodItemList(int restaurantId) {
		
		return foodItemRepo.findByRestaurantId(restaurantId);
	}
}
