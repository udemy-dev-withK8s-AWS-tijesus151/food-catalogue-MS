package com.codedecode.foodcatalogue.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.codedecode.foodcatalogue.dto.FoodItemDTO;
import com.codedecode.foodcatalogue.entity.FoodItem;

@Mapper
public interface FoodItemMapper {

	FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);
	
	FoodItem mapFoodItemDTOToFoodItem(FoodItemDTO foodItemDTO);
	FoodItemDTO mapFoodItemToFoodItemDTO(FoodItem foodItem);
	
}
