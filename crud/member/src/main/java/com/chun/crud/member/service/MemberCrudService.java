package com.chun.crud.member.service;

import com.chun.commons.errors.exception.MemberAlreadyExistException;
import com.chun.commons.errors.exception.MemberNotFoundException;
import com.chun.crud.member.repository.MemberCrudRepository;
import com.chun.crud.member.dtos.MemberSaveDto;
import com.chun.crud.member.dtos.MemberUpdateDto;
import com.chun.crud.member.entitys.Member;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MemberCrudService {
    private final MemberCrudRepository memberCrudRepository;

    public Member save(MemberSaveDto dto){
        if(memberCrudRepository.existsById(dto.getId()))
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

        return memberCrudRepository.save(member);
    }

    public Member find(String userId) {
        return memberCrudRepository.findById(userId)
                .orElseThrow(MemberNotFoundException::new);
    }

    public List<Member> findAll() {
        return memberCrudRepository.findAll();
    }

    public Member update(MemberUpdateDto dto) {
        Member member = memberCrudRepository.findById(dto.getId())
                .orElseThrow(MemberNotFoundException::new);

        return memberCrudRepository.save(member.updateInfo(dto));
    }

    public Member updatePassword(String userId, String newPassword) {
        Member member = memberCrudRepository.findById(userId)
                .orElseThrow(MemberNotFoundException::new);
        return memberCrudRepository.save(member.updatePassword(newPassword));
    }

    public void delete(String userId) {
        Member member = memberCrudRepository.findById(userId)
                .orElseThrow(MemberNotFoundException::new);

        memberCrudRepository.delete(member);
    }
}
