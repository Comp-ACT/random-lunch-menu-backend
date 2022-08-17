package com.TeamSk.JMC.Service.Rooms;

import com.TeamSk.JMC.Domain.Room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;
}
