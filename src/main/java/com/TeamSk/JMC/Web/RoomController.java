package com.TeamSk.JMC.Web;

import com.TeamSk.JMC.Exception.ErrorResponse;
import com.TeamSk.JMC.Exception.RoomRequestParamRequiredException;
import com.TeamSk.JMC.Service.Rooms.RoomService;
import com.TeamSk.JMC.Web.Dto.roomDto.RoomJoinDto;
import com.TeamSk.JMC.Web.Dto.roomDto.RoomRequestDto;
import com.TeamSk.JMC.Web.Dto.roomDto.RoomResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/rooms")
    public Long makingRoom(@RequestBody RoomRequestDto roomRequestDto) {
        return roomService.save(roomRequestDto);
    }

    @DeleteMapping("/rooms/{roomId}")
    public boolean deleteRoom(@PathVariable Long roomId) {
        return roomService.deleteRoom(roomId);
    }

    @PostMapping("/rooms/join")
    public boolean joinRoom(@RequestBody RoomJoinDto roomJoinDto) {
        return roomService.addMember(roomJoinDto);
    }

    @GetMapping("/rooms/{roomId}")
    public RoomResponseDto getRoomInfo(@PathVariable Long roomId) {
        return roomService.getRoomResponseDto(roomId);
    }

    @PutMapping("/rooms/{roomId}")
    public boolean updateRoomInfo(@PathVariable Long roomId, @RequestBody RoomRequestDto roomRequestDto) {
        return roomService.updateRoomInfo(roomId, roomRequestDto);
    }

    @DeleteMapping("/rooms/users/{roomId}/{memberId}")
    public boolean deleteUserInRoom(@PathVariable Long roomId, @PathVariable Long memberId) {
        return roomService.deleteUserInRoom(roomId, memberId);

    }

    @ExceptionHandler(RoomRequestParamRequiredException.class)
    public ResponseEntity<ErrorResponse> roomRequestParamRequiredExceptionHandler(RoomRequestParamRequiredException e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(
                        new ErrorResponse(
                                e.getHttpStatus().value(),
                                e.getHttpStatus().getReasonPhrase(),
                                e.getMessage()
                        )
                );
    }
}
