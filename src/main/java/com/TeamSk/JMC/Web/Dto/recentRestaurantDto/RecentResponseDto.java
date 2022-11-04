package com.TeamSk.JMC.Web.Dto.recentRestaurantDto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
public class RecentResponseDto {

    private final List<RecentRestaurantResponseDto> recentList;

    @Builder
    public RecentResponseDto(@JsonProperty("recentList") List<RecentRestaurantResponseDto> recentList) {
        this.recentList = recentList;
    }
}
