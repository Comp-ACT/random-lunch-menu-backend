package com.TeamSk.JMC.Web;

import com.TeamSk.JMC.Service.Rooms.RoomService;
import com.TeamSk.JMC.Web.Dto.roomDto.RoomJoinDto;
import com.TeamSk.JMC.Web.Dto.roomDto.RoomMakingDto;
import com.TeamSk.JMC.Web.Dto.roomDto.RoomResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/rooms")
    public Long makingRoom(@RequestBody RoomMakingDto roomMakingDto) {
        return roomService.save(roomMakingDto);
    }

    @DeleteMapping("/rooms/{roomId}")
    public void deleteRoom(@RequestParam Long roomId)
    {

    }
    @PostMapping("/rooms/join")
    public boolean joinRoom(@RequestBody RoomJoinDto roomJoinDto)
    {
        return roomService.addMember(roomJoinDto);
    }

    @GetMapping("/rooms/{roomId}")
    public RoomResponseDto getRoomInfo(@PathVariable Long roomId)
    {

        return roomService.getRoomResponseDto(roomId);
    }
}
