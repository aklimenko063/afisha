package org.javaacademy.afisha.mapper;

import org.javaacademy.afisha.model.dto.PlaceDto;
import org.javaacademy.afisha.model.entity.Place;

public class PlaceMapper {

    public static Place covertPlaceDtoToPlaceEntity(PlaceDto placeDto) {
        Place place = new Place();
        place.setName(placeDto.getName());
        place.setAddress(placeDto.getAddress());
        place.setCity(placeDto.getCity());
        return place;
    }

    public static PlaceDto covertPlaceEntityToPlaceDto(Place place) {
        PlaceDto placeDto = new PlaceDto();
        placeDto.setName(place.getName());
        placeDto.setAddress(place.getAddress());
        placeDto.setCity(place.getCity());
        return placeDto;
    }
}
