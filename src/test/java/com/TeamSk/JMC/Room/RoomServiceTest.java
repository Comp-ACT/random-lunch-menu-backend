package com.TeamSk.JMC.Room;

import com.TeamSk.JMC.Domain.Member.Member;
import com.TeamSk.JMC.Domain.Room.Room;
import com.TeamSk.JMC.Domain.Room.RoomRepository;
import com.TeamSk.JMC.Service.Rooms.RoomService;
import com.TeamSk.JMC.Web.Dto.roomDto.RoomMakingDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;
    @InjectMocks
    private RoomService roomService;

    @Test
    public void Room생성(){

        //given
        Member member = new Member("박세준","psj6570@naver.com","asdfasfd");
        RoomMakingDto roomMakingDto = new RoomMakingDto("점메추방","0000",1L);
        Room save = roomMakingDto.toEntity();
        BDDMockito.given(roomRepository.save(BDDMockito.any())).willReturn(save);
        //when
        //Room saveRoom = roomService.save(roomMakingDto);

        //then
        //Assertions.assertThat(roomMakingDto.getName()).isEqualTo(saveRoom.getName());
    }
}
