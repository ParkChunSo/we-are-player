package com.chun.apps.invitation.controller;

import com.chun.apps.invitation.dtos.request.InvitationMatchSaveReqDto;
import com.chun.apps.invitation.dtos.request.InvitationMatchUpdateReqDto;
import com.chun.apps.invitation.dtos.request.InvitationPeopleSaveReqDto;
import com.chun.apps.invitation.dtos.request.InvitationPeopleUpdateReqDto;
import com.chun.apps.invitation.service.InvitationMatchService;
import com.chun.apps.invitation.service.InvitationPeopleService;
import com.chun.commons.dtos.invitation.InvitationMatchResDto;
import com.chun.commons.dtos.invitation.InvitationPeopleResDto;
import com.chun.commons.enums.InvitationType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/invitation")
public class InvitationController {
    private final InvitationMatchService invitationMatchService;
    private final InvitationPeopleService invitationPeopleService;

    @GetMapping("/people/{id}")
    public InvitationPeopleResDto findPeople(@PathVariable Long id){
        return invitationPeopleService.find(id);
    }

    @GetMapping("/match/{id}")
    public InvitationMatchResDto findMatch(@PathVariable Long id){
        return invitationMatchService.find(id);
    }

    @GetMapping("/member")
    public List<InvitationPeopleResDto> findMember(){
        return invitationPeopleService.findAll(InvitationType.MEMBER);
    }

    @GetMapping("/mercenary")
    public List<InvitationPeopleResDto> findMercenary(){
        return invitationPeopleService.findAll(InvitationType.MERCENARY);
    }

    @GetMapping("/match")
    public List<InvitationMatchResDto> findMatch(){
        return invitationMatchService.findAll();
    }

    //장소 기반 검색
    @GetMapping("/people/city/{city}/district/{district}")
    public List<InvitationPeopleResDto> findPeopleByLocation(@PathVariable String city, @PathVariable String district){
        return invitationPeopleService.findPeopleByLocation(city, district);
    }

    //장소 기반 검색
    @GetMapping("/match/city/{city}/district/{district}")
    public List<InvitationMatchResDto> findMatchByLocation(@PathVariable String city, @PathVariable String district){
        return invitationMatchService.findByLocation(city, district);
    }

    //장소와 날짜 기반 검색
//    @GetMapping("/category/{category}/city/{city}/district/{district}/from/{from}/to/{to}")
//    public List<InvitationInfoDto> getInvitationInfoByLocationAndCategory(@PathVariable String category, @PathVariable String city, @PathVariable String district,
//                                                                          @PathVariable LocalDate from, @PathVariable LocalDate to){
//        return invitationMatchService.getInvitationInfoByCategoryAndLocationAndDateBetween(category, city, district, from, to);
//    }

    @PutMapping("/people")
    public void updatePeople(@RequestHeader(name = "Authorization") String token, @RequestBody InvitationPeopleUpdateReqDto dto){
        invitationPeopleService.update(token, dto);
    }

    @PutMapping("/match")
    public void updateMatch(@RequestHeader(name = "Authorization") String token, @RequestBody InvitationMatchUpdateReqDto dto){
        invitationMatchService.update(token, dto);
    }

    @PostMapping("/people")
    public void savePeople(@RequestBody InvitationPeopleSaveReqDto dto){
        invitationPeopleService.save(dto);
    }

    @PostMapping("/match")
    public void saveMatch(@RequestBody InvitationMatchSaveReqDto dto){
        invitationMatchService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void deletePeople(@RequestHeader(name = "Authorization") String token, @PathVariable Long id){
        invitationPeopleService.delete(token, id);
    }

    @DeleteMapping("/{id}")
    public void deleteMatch(@RequestHeader(name = "Authorization") String token, @PathVariable Long id) {
        invitationMatchService.delete(token, id);
    }
}
