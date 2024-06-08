package org.javaacademy.afisha.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.afisha.model.dto.PlaceDto;
import org.javaacademy.afisha.model.entity.Place;
import org.javaacademy.afisha.mapper.PlaceMapper;
import org.javaacademy.afisha.repository.PlaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final TransactionTemplate transactionTemplate;

    public String addNewPlace(PlaceDto placeDto) {
        Place place = PlaceMapper.covertPlaceDtoToPlaceEntity(placeDto);
        transactionTemplate.executeWithoutResult(transactionStatus -> {
            placeRepository.addNewEntity(place);
        });
        return place.getName();
    }

    public List<PlaceDto> findAllPlaces() {
        return placeRepository.findAllEntity()
                .stream()
                .map(PlaceMapper::covertPlaceEntityToPlaceDto)
                .toList();
    }
}
