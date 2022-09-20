package com.TeamSk.JMC.Member;

import com.TeamSk.JMC.Domain.Member.Member;
import com.TeamSk.JMC.Domain.Member.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("mockito 레파지토리 테스트")
    void mockMemberRepositoryTest() {

        //given
        Member member1 = Member.builder()
                .name("박세준")
                .email("psj6570@navmer.com")
                .profileImageURL("asdfadsf")
                .build();

        List<Member> members = new ArrayList<>();
        members.add(member1);
        memberRepository.save(member1);
        //BDDMockito.given(memberRepository.findAll()).willReturn(members);

        //when
        List<Member> findMembers = memberRepository.findAll();
        System.out.println("findMembers = " + findMembers);

        //then
        Assertions.assertEquals(1,findMembers.size());
        Assertions.assertEquals(member1.getName(),findMembers.get(0).getName());
    }
}
