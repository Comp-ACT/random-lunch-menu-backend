package com.TeamSk.JMC.Service.Rooms;

import com.TeamSk.JMC.Domain.Member.Member;
import com.TeamSk.JMC.Domain.Member.MemberRepository;
import com.TeamSk.JMC.Domain.Restaurant.Restaurant;
import com.TeamSk.JMC.Domain.Room.Room;
import com.TeamSk.JMC.Domain.Room.RoomRepository;
import com.TeamSk.JMC.Domain.RoomMember.RoomMember;
import com.TeamSk.JMC.Domain.RoomMember.RoomMemberRepository;
import com.TeamSk.JMC.Web.Dto.MemberDto.MemberHashMapDto;
import com.TeamSk.JMC.Web.Dto.MemberDto.MemberResponseDto;
import com.TeamSk.JMC.Web.Dto.restaurantDto.RestaurantResponseDto;
import com.TeamSk.JMC.Web.Dto.roomDto.RoomJoinDto;
import com.TeamSk.JMC.Web.Dto.roomDto.RoomMakingDto;
import com.TeamSk.JMC.Web.Dto.roomDto.RoomResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;

    private final RoomMemberRepository roomMemberRepository;

    public Long save(RoomMakingDto roomMakingDto) {
        return roomRepository.save(roomMakingDto.toEntity()).getId();
    }

    public boolean addMember(RoomJoinDto dto) {
        Long roomId = dto.getRoomId();
        Long memberId = dto.getMemberId();
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isPresent() && memberOptional.isPresent()) {
            RoomMember build = RoomMember.builder()
                    .member(memberOptional.get())
                    .room(roomOptional.get())
                    .build();
            System.out.println("roomMember id : " + build.getId());
            roomMemberRepository.save(build);
            return true;
        }

        return false;
    }

    public RoomResponseDto getRoomResponseDto(Long roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            String name = room.getName();
            Long leaderId = room.getLeaderId();
            String password = room.getPassword();
            List<RestaurantResponseDto> restaurantList = getRestaurantList(roomId);
            List<MemberResponseDto> memberList = getMemberList(roomId);
            return RoomResponseDto.builder()
                    .name(name)
                    .leaderId(leaderId)
                    .password(password)
                    .restaurantList(restaurantList)
                    .memberList(memberList)
                    .build();
        }
        //에러 터지는거 아직 미구현
        return null;
    }

    public List<RestaurantResponseDto> getRestaurantList(Long roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);

        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            List<Restaurant> restaurantsList = room.getRestaurants();
            List<RestaurantResponseDto> restaurantList = new ArrayList<>();
            for (int i = 0; i < restaurantsList.size(); i++) {
                Long id = restaurantsList.get(i).getId();
                String name = restaurantsList.get(i).getName();
                RestaurantResponseDto build = RestaurantResponseDto.builder()
                        .id(id)
                        .name(name)
                        .build();
                restaurantList.add(build);
            }
            return restaurantList;
        }
        //에러 터지는거 아직 미구현
        return null;
    }

    public boolean deleteRoom(Long roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            roomRepository.delete(room);
            return true;
        } else
            return false;
    }

    public boolean deleteUserInRoom(Long roomId, Long memberId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isPresent()) {
            HashMap<Long, MemberHashMapDto> memberHashMap = getMemberHashMap(roomId);
            if (memberHashMap.containsKey(memberId)) {
                Long roomMemberId = memberHashMap.get(memberId).getRoomMemberId();
                Optional<RoomMember> roomMemberOptional = roomMemberRepository.findById(roomMemberId);
                RoomMember roomMember = roomMemberOptional.get();
                roomMemberRepository.delete(roomMember);
                return true;
            }
        }
        return false;
    }


    public List<MemberResponseDto> getMemberList(Long roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);

        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            List<RoomMember> roomMemberList = room.getRoomMembers();
            List<MemberResponseDto> memberList = new ArrayList<>();
            for (int i = 0; i < roomMemberList.size(); i++) {
                Long id = roomMemberList.get(i).getMember().getId();
                String email = roomMemberList.get(i).getMember().getEmail();
                String name = roomMemberList.get(i).getMember().getName();
                String profileImageURL = roomMemberList.get(i).getMember().getProfileImageURL();
                MemberResponseDto responseDto = MemberResponseDto.builder()
                        .id(id)
                        .email(email)
                        .name(name)
                        .profileImageURL(profileImageURL)
                        .build();
                memberList.add(responseDto);
            }
            return memberList;
        }
        //에러 터지는거 아직 미구현
        return null;
    }

    public HashMap<Long, MemberHashMapDto> getMemberHashMap(Long roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);

        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            List<RoomMember> roomMemberList = room.getRoomMembers();
            HashMap<Long, MemberHashMapDto> memberList = new HashMap<>();
            for (int i = 0; i < roomMemberList.size(); i++) {
                Long roomMemberId = roomMemberList.get(i).getId();
                Long memberId = roomMemberList.get(i).getMember().getId();
                String email = roomMemberList.get(i).getMember().getEmail();
                String name = roomMemberList.get(i).getMember().getName();
                String profileImageURL = roomMemberList.get(i).getMember().getProfileImageURL();
                MemberHashMapDto responseDto = MemberHashMapDto.builder()
                        .roomMemberId(roomMemberId)
                        .memberId(memberId)
                        .email(email)
                        .name(name)
                        .profileImageURL(profileImageURL)
                        .build();
                memberList.put(memberId, responseDto);
            }
            return memberList;
        }
        //에러 터지는거 아직 미구현
        return null;
    }


}
