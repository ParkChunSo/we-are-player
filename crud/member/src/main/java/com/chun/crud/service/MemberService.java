package com.chun.crud.service;

import com.chun.commons.errors.exception.MemberAlreadyExistException;
import com.chun.commons.errors.exception.MemberNotFoundException;
import com.chun.crud.dtos.MemberSaveDto;
import com.chun.crud.dtos.MemberUpdateDto;
import com.chun.crud.entitys.Member;
import com.chun.crud.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member save(MemberSaveDto dto){
        if(memberRepository.existsById(dto.getId()))
            throw new MemberAlreadyExistException();

        Member member = Member.builder()
                .id(dto.getId())
                .password(dto.getPassword())
                .name(dto.getName())
                .roleSet(dto.getType().getRoles())
                .city(dto.getCity())
                .district(dto.getDistrict())
                .pictureUri(dto.getPictureUri())
                .position(dto.getPosition())
                .build();

        return memberRepository.save(member);
    }

    public Member find(String userId) {
        return memberRepository.findById(userId)
                .orElseThrow(MemberNotFoundException::new);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member update(MemberUpdateDto dto) {
        Member member = memberRepository.findById(dto.getId())
                .orElseThrow(MemberNotFoundException::new);

        return memberRepository.save(member.updateInfo(dto));
    }

    public Member updatePassword(String userId, String newPassword) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(MemberNotFoundException::new);
        return memberRepository.save(member.updatePassword(newPassword));
    }

    public void delete(String userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(MemberNotFoundException::new);

        memberRepository.delete(member);
    }
}
