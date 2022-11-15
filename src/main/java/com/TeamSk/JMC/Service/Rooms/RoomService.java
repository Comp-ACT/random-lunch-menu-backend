package com.TeamSk.JMC.Service.Rooms;

import com.TeamSk.JMC.Domain.Member.Member;
import com.TeamSk.JMC.Domain.Member.MemberRepository;
import com.TeamSk.JMC.Domain.Restaurant.Restaurant;
import com.TeamSk.JMC.Domain.Room.Room;
import com.TeamSk.JMC.Domain.Room.RoomRepository;
import com.TeamSk.JMC.Domain.RoomMember.RoomMember;
import com.TeamSk.JMC.Domain.RoomMember.RoomMemberRepository;
import com.TeamSk.JMC.Exception.*;
import com.TeamSk.JMC.Web.Dto.MemberDto.MemberHashMapDto;
import com.TeamSk.JMC.Web.Dto.MemberDto.MemberResponseDto;
import com.TeamSk.JMC.Web.Dto.restaurantDto.RestaurantResponseDto;
import com.TeamSk.JMC.Web.Dto.roomDto.RoomJoinDto;
import com.TeamSk.JMC.Web.Dto.roomDto.RoomRequestDto;
import com.TeamSk.JMC.Web.Dto.roomDto.RoomResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@RequiredArgsConstructor
@Service
@Slf4j
public class RoomService {
    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;

    private final RoomMemberRepository roomMemberRepository;
    private Optional<Member> memberOptional;

<<<<<<< Updated upstream
    public Long save(RoomRequestDto roomRequestDto) {
        StringJoiner result = new StringJoiner(", ");
        boolean flag = false;
        if (roomRequestDto.getName().equals("")) {
            result.add("name");
            flag = true;
        }
        if (roomRequestDto.getPassword().equals("")) {
            result.add("password");
            flag = true;
        }
        if (roomRequestDto.getLeaderId() == null) {
            result.add("leaderId");
            flag = true;
        }
        if (flag) {
            log.error("[" + result + "]는(은) 필수값 입니다.");
            throw new RoomRequestParamRequiredException("Parameter 필수 값 누락 ::[" + result + "]는(은) 필수값 입니다.");
        }
        return roomRepository.save(roomRequestDto.toEntity()).getId();
=======
    public Long save(RoomMakingDto roomMakingDto) {
        return roomRepository.save(roomMakingDto.toEntity()).getId();
>>>>>>> Stashed changes
    }

    public boolean addMember(RoomJoinDto dto) {
        Long roomId = dto.getRoomId();
        Long memberId = dto.getMemberId();
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Optional<Room> roomOptional = roomRepository.findById(roomId);
<<<<<<< Updated upstream

        if (!roomOptional.isPresent() && !memberOptional.isPresent()) {
            log.error("RoomMemberBothNotFoundException : roomID(" + roomId + "), memberID(" + memberId + ")에 대한 room과 member가 없습니다.");
            throw new RoomMemberBothNotFoundException("RoomMemberBothNotFoundException : roomID(" + roomId + "), memberID(" + memberId + ")에 대한 room과 member가 없습니다.");

        }

        if (!roomOptional.isPresent()) {
            log.error("RoomNotFoundException : roomID(" + roomId + ")에 대한 room이 없습니다.");
            throw new RoomNotFoundException("RoomNotFoundException : roomID(" + roomId + ")에 대한 room이 없습니다.");
        }
        if (!memberOptional.isPresent()) {
            log.error("MemberNotFoundException : memberID(" + memberId + ")에 대한 member가 없습니다.");
            throw new MemberNotFoundException("MemberNotFoundException : memberID(" + memberId + ")에 대한 member가 없습니다.");
=======
        if (roomOptional.isPresent() && memberOptional.isPresent()) {
            RoomMember build = RoomMember.builder()
                    .member(memberOptional.get())
                    .room(roomOptional.get())
                    .build();
            System.out.println("roomMember id : " + build.getId());
            roomMemberRepository.save(build);
            return true;
>>>>>>> Stashed changes
        }
        List<RoomMember> roomMembers = roomOptional.get().getRoomMembers();
        for (int i = 0; i < roomMembers.size(); i++) {
            if (roomMembers.get(i).getMember().getId() == memberId) {
                log.error("AlreadyExistedMemberException : 이미 memberID(" + memberId + ")에 대한 member가 방에 존재합니다.");
                throw new AlreadyExistedMemberException();
            }
        }

        RoomMember build = RoomMember.builder()
                .member(memberOptional.get())
                .room(roomOptional.get())
                .build();
        roomMemberRepository.save(build);
        return true;

    }

    public RoomResponseDto getRoomResponseDto(Long roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
<<<<<<< Updated upstream

        if (!roomOptional.isPresent()) {
            log.error("RoomNotFoundException : roomID(" + roomId + ")에 대한 room이 없습니다.");
            throw new RoomNotFoundException("RoomNotFoundException : roomID(" + roomId + ")에 대한 room이 없습니다.");
=======
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
>>>>>>> Stashed changes
        }
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

    public List<RestaurantResponseDto> getRestaurantList(Long roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);

<<<<<<< Updated upstream
        if (!roomOptional.isPresent()) {
            log.error("RoomNotFoundException : roomID(" + roomId + ")에 대한 room이 없습니다.");
            throw new RoomNotFoundException("RoomNotFoundException : roomID(" + roomId + ")에 대한 room이 없습니다.");
=======
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
>>>>>>> Stashed changes
        }

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

    public boolean deleteRoom(Long roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
<<<<<<< Updated upstream
        if (!roomOptional.isPresent()) {
            log.error("RoomNotFoundException : roomID(" + roomId + ")에 대한 room이 없습니다.");
            throw new RoomNotFoundException("RoomNotFoundException : roomID(" + roomId + ")에 대한 room이 없습니다.");
        }

        Room room = roomOptional.get();
        List<RoomMember> roomMembers = room.getRoomMembers();
        for (int i = 0; i < roomMembers.size(); i++) {
            deleteUserInRoom(roomId, roomMembers.get(i).getMember().getId());
        }
        roomRepository.delete(room);
        return true;

    }

    @Transactional
    public boolean updateRoomInfo(Long roomId, RoomRequestDto roomRequestDto) {
        StringJoiner result = new StringJoiner(", ");
        boolean flag = false;
        if (roomRequestDto.getName().equals("")) {
            result.add("name");
            flag = true;
        }
        if (roomRequestDto.getPassword().equals("")) {
            result.add("password");
            flag = true;
        }
        if (roomRequestDto.getLeaderId() == null) {
            result.add("leaderId");
            flag = true;
        }
        if (flag) {
            log.error("[" + result + "]는(은) 필수값 입니다.");
            throw new RoomRequestParamRequiredException("Parameter 필수 값 누락 ::[" + result + "]는(은) 필수값 입니다.");
        }
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (!roomOptional.isPresent()) {
            log.error("RoomNotFoundException : roomID(" + roomId + ")에 대한 room이 없습니다.");
            throw new RoomNotFoundException("RoomNotFoundException : roomID(" + roomId + ")에 대한 room이 없습니다.");
        }
        Room room = roomOptional.get();

        room.setName(roomRequestDto.getName());
        room.setPassword(roomRequestDto.getPassword());
        Long newLeaderId = roomRequestDto.getLeaderId();
        Optional<Member> memberOptional = memberRepository.findById(newLeaderId);
        if (!memberOptional.isPresent()) {
            log.error("MemberNotFoundException : memberID(" + newLeaderId + ")에 대한 member가 없습니다.");
            throw new MemberNotFoundException("MemberNotFoundException : memberID(" + newLeaderId + ")에 대한 member가 없어 leader를 변경할 수 없습니다.");
        }
        room.setLeaderId(newLeaderId);
        return true;
=======
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            roomRepository.delete(room);
            return true;
        } else
            return false;
>>>>>>> Stashed changes
    }

    public boolean deleteUserInRoom(Long roomId, Long memberId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
<<<<<<< Updated upstream
        if (!roomOptional.isPresent()) {
            log.error("RoomNotFoundException : roomID(" + roomId + ")에 대한 room이 없습니다.");
            throw new RoomNotFoundException();
=======
        if (roomOptional.isPresent()) {
            HashMap<Long, MemberHashMapDto> memberHashMap = getMemberHashMap(roomId);
            if (memberHashMap.containsKey(memberId)) {
                Long roomMemberId = memberHashMap.get(memberId).getRoomMemberId();
                Optional<RoomMember> roomMemberOptional = roomMemberRepository.findById(roomMemberId);
                RoomMember roomMember = roomMemberOptional.get();
                roomMemberRepository.delete(roomMember);
                return true;
            }
>>>>>>> Stashed changes
        }

        HashMap<Long, MemberHashMapDto> memberHashMap = getMemberHashMap(roomId);
        if (!memberHashMap.containsKey(memberId)) {
            log.error("MemberNotFoundException : memberID(" + memberId + ")에 대한 member가 없습니다.");
            throw new MemberNotFoundException();
        }
        Long roomMemberId = memberHashMap.get(memberId).getRoomMemberId();
        Optional<RoomMember> roomMemberOptional = roomMemberRepository.findById(roomMemberId);
        RoomMember roomMember = roomMemberOptional.get();
        roomMemberRepository.delete(roomMember);
        return true;
    }


    public List<MemberResponseDto> getMemberList(Long roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
<<<<<<< Updated upstream
        if (!roomOptional.isPresent()) {
            log.error("RoomNotFoundException : roomID(" + roomId + ")에 대한 room이 없습니다.");
            throw new RoomNotFoundException();
        }
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
=======

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
>>>>>>> Stashed changes
        }
        return memberList;


    }

    public HashMap<Long, MemberHashMapDto> getMemberHashMap(Long roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
<<<<<<< Updated upstream
        if (!roomOptional.isPresent()) {
            log.error("RoomNotFoundException : roomID(" + roomId + ")에 대한 room이 없습니다.");
            throw new RoomNotFoundException();
=======

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
>>>>>>> Stashed changes
        }

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
}
