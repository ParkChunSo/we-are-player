package com.wap.chun.profile.member;

import com.wap.chun.common.WebConfig;
import com.wap.chun.domain.MemberInfoSetUp;
import com.wap.chun.domain.entitys.Member;
import com.wap.chun.profile.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


//@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureDataJpa
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    void test_ExistById() {
        //given
        Member member = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park);
        memberRepository.save(member);

        //when
        final boolean val = memberRepository.existsById(member.getId());

        //then
        assertTrue(val);
    }

    @Test
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
