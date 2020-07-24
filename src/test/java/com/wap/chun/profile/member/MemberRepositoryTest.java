package com.wap.chun.profile.member;

import com.wap.chun.common.RepositoryTest;
import com.wap.chun.domain.entitys.Member;
import com.wap.chun.domain.request.MemberInfoSetUp;
import com.wap.chun.profile.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@RepositoryTest
public class MemberRepositoryTest{

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("아이디 중복 확인")
    void test_existById() {
        //given
        Member member = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park);
        memberRepository.save(member);

        //when
        final boolean val = memberRepository.existsById(member.getId());

        //then
        assertTrue(val);
    }

    @Test
    @DisplayName("사용자 조회")
    void test_findById() {
        //given
        Member member = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park);
        memberRepository.save(member);

        //when
        final Member entity = memberRepository.findById(member.getId()).orElse(null);

        //then
        assertNotNull(entity);
        assertEquals(entity.getId(), member.getId());
    }
}
