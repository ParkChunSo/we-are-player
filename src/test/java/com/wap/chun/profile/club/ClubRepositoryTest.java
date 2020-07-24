package com.wap.chun.profile.club;

import com.wap.chun.common.RepositoryTest;
import com.wap.chun.domain.builder.ClubBuilder;
import com.wap.chun.domain.builder.ClubInfoUpdateDtoSetUp;
import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.Member;
import com.wap.chun.domain.request.MemberInfoSetUp;
import com.wap.chun.profile.club.dtos.ClubInfoUpdateDto;
import com.wap.chun.profile.club.repository.ClubRepository;
import com.wap.chun.profile.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RepositoryTest
public class ClubRepositoryTest {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("클럽 저장(성공)")
    void testSaveClubSuccess(){
        //given
        Member park = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park);
        park = memberRepository.save(park);
        Club club = ClubBuilder.build(park);

        //when
        Club save = clubRepository.save(club);

        //then
        assertNotNull(save.getClubId());
        assertEquals(club.getLeader().getId(), park.getId());
    }
    
    @Test
    @DisplayName("클럽 조회(성공)")
    void testGetClubSuccess(){
        //given
        Member park = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park);
        park = memberRepository.save(park);
        Club club = ClubBuilder.build(park);
        clubRepository.save(club);

        //when
        Optional<Club> save = clubRepository.findByClubNameAndLocation(club.getClubName(), club.getLocation());

        //then
        assertTrue(save.isPresent());
        assertNotNull(save.get().getClubId());
        assertEquals(club.getClubName(), save.get().getClubName());
        assertEquals(club.getLocation(), save.get().getLocation());
        assertEquals(club.getLeader(), park);
    }

    @Test
    @DisplayName("클럽 중복 확인(성공)")
    void testExistsByClubNameAndLocationSuccess(){
        //given
        Member park = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park);
        park = memberRepository.save(park);
        Club YClub = ClubBuilder.build(park);
        clubRepository.save(YClub);

        //when
        boolean val = clubRepository.existsByClubNameAndLocation(YClub.getClubName(), YClub.getLocation());

        //then
        assertTrue(val);
    }

    @Test
    @DisplayName("클럽 지역명으로 조회(성공)")
    void testFindByLocationSuccess(){
        //given
        Member park = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park);
        Member kim = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.kim);
        park = memberRepository.save(park);
        kim = memberRepository.save(kim);

        Club YClub = ClubBuilder.build(park);
        Club YPClub = ClubBuilder.build(kim, "양평프로FC");
        clubRepository.save(YClub);
        clubRepository.save(YPClub);

        //when
        Optional<List<Club>> save = clubRepository.findByLocation(YClub.getLocation());

        //then
        assertTrue(save.isPresent());
        assertEquals(save.get().size(), 2);
    }

    @Test
    @DisplayName("클럽명으로 조회(성공)")
    void testFindByClubNameSuccess(){
        //given
        Member park = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park);
        Member kim = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.kim);
        park = memberRepository.save(park);
        kim = memberRepository.save(kim);

        Club YClub = ClubBuilder.build(park, "잔디밭");
        Club YPClub = ClubBuilder.build(kim, "잔디밭");
        clubRepository.save(YClub);
        clubRepository.save(YPClub);

        //when
        Optional<List<Club>> save = clubRepository.findByLocation(YClub.getLocation());

        //then
        assertTrue(save.isPresent());
        assertEquals(save.get().size(), 2);
    }

    @Test
    @DisplayName("정보 업데이트(성공)")
    void testUpdateClub(){
        //given
        ClubInfoUpdateDto dto = ClubInfoUpdateDtoSetUp.dto;

        Member park = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park);
        Member kim = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.kim);
        park = memberRepository.save(park);
        kim = memberRepository.save(kim);

        Club club = ClubBuilder.build(park);
        club = clubRepository.save(club);

        //when
        club.setLikeCnt(dto.getLikeCnt());
        club.setRudeCnt(dto.getRudeCnt());
        club.setLeader(kim);
        Club save = clubRepository.save(club);

        //then
        assertNotNull(save.getClubId());
        assertEquals(club.getLeader(), kim);
        assertEquals(club.getLikeCnt(), dto.getLikeCnt());
        assertEquals(club.getRudeCnt(), dto.getRudeCnt());
    }

    @Test
    @DisplayName("클럽 삭제")
    void testDeleteClub(){
        //given
        Member park = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park);
        park = memberRepository.save(park);

        Club club = clubRepository.save(ClubBuilder.build(park));

        //when
        clubRepository.deleteByClubNameAndLocation(club.getClubName(), club.getLocation());

        //then
        assertFalse(clubRepository.existsByClubNameAndLocation(club.getClubName(), club.getLocation()));
    }
}
