package com.TeamSk.JMC.Web.Dto.restaurantDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RestaurantResponseDto {

    private Long id;
    private String name;

    @Builder
    public RestaurantResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
