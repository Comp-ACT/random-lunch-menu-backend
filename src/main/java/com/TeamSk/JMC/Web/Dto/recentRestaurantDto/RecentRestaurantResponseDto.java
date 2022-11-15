package com.TeamSk.JMC.Web.Dto.recentRestaurantDto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
public class RecentRestaurantResponseDto {
    private final String restaurantName;

    private final String votingDate;

    @Builder
    public RecentRestaurantResponseDto(String restaurantName, String votingDate) {
        this.restaurantName = restaurantName;
        this.votingDate = votingDate;
    }
}
