package com.TeamSk.JMC.Web;

import com.TeamSk.JMC.Service.RecentRestaurant.RecentRestaurantService;
import com.TeamSk.JMC.Web.Dto.recentRestaurantDto.RecentResponseDto;
import com.TeamSk.JMC.Web.Dto.recentRestaurantDto.RecentRestaurantMakingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class RecentRestaurantController {

    private final RecentRestaurantService recentRestaurantService;

    @PostMapping("/recentRestaurant")
    public Long makingRoom(@RequestBody RecentRestaurantMakingDto makingDto) {
        return recentRestaurantService.save(makingDto);
    }

    @GetMapping("/recentRestaurant/{roomId}")
    public RecentResponseDto getRecentRestaurantList(@PathVariable Long roomId) {
        return recentRestaurantService.getRecentList(roomId);
    }
}
