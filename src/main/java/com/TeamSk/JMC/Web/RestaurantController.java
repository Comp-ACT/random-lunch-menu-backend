package com.TeamSk.JMC.Web;

import com.TeamSk.JMC.Service.Restaurant.RestaurantService;
import com.TeamSk.JMC.Web.Dto.restaurantDto.RestaurantMakingDto;
import com.TeamSk.JMC.Web.Dto.restaurantDto.RestaurantResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("/restaurants")
    @ResponseBody
    public Long save(@RequestBody RestaurantMakingDto restaurantDto) {
        return restaurantService.save(restaurantDto);
    }

    @GetMapping("/restaurants/{restaurantId}")
    public RestaurantResponseDto getRestaurantInfo(@PathVariable Long restaurantId) {
        return restaurantService.getRestaurantResponseDto(restaurantId);
    }

    @DeleteMapping("/restaurants/{restaurantId}")
    @ResponseBody
    public boolean deleteRestaurant(@PathVariable Long restaurantId) {
        return restaurantService.deleteRestaurant(restaurantId);
    }

}