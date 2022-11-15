package com.TeamSk.JMC.Exception.handler;

import com.TeamSk.JMC.Domain.Member.Member;
import com.TeamSk.JMC.Domain.Room.Room;
import com.TeamSk.JMC.Exception.MemberNotFoundException;
import com.TeamSk.JMC.Exception.RoomNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class Handler {
    public void memberNotFoundExceptionHandler(Long memberId, Optional<Member> memberOptional) {
        if (!memberOptional.isPresent()) {
            log.error("MemberNotFoundException : memberID(" + memberId + ")에 대한 member가 없습니다.");
            throw new MemberNotFoundException("MemberNotFoundException : memberID(" + memberId + ")에 대한 member가 없습니다.");
        }
    }

    public void roomNotFoundExceptionHandler(Long roomId, Optional<Room> roomOptional) {
        if (!roomOptional.isPresent()) {
            log.error("RoomNotFoundException : roomID(" + roomId + ")에 대한 room이 없습니다.");
            throw new RoomNotFoundException("RoomNotFoundException : roomID(" + roomId + ")에 대한 room이 없습니다.");
        }
    }
}
