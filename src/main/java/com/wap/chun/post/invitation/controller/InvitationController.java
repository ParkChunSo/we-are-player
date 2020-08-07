package com.wap.chun.post.invitation.controller;

import com.wap.chun.domain.entitys.Invitation;
import com.wap.chun.post.invitation.dto.InvitationMatchInfoDto;
import com.wap.chun.post.invitation.dto.InvitationMemberInfoDto;
import com.wap.chun.post.invitation.dto.InvitationSaveDto;
import com.wap.chun.post.invitation.service.InvitationService;
import com.wap.chun.post.invitation.dto.InvitationInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/invitation")
public class InvitationController {
    private final InvitationService invitationService;

    @GetMapping("/id/{id}")
    public InvitationInfoDto getInvitationInfo(@PathVariable Long id){
        return invitationService.getInvitationInfoById(id);
    }

    //장소 기반 검색
    @GetMapping("/category/{category}/city/{city}/district/{district}")
    public List<InvitationInfoDto> getInvitationInfoByLocationAndCategory(@PathVariable String category, @PathVariable String city, @PathVariable String district){
        return invitationService.getInvitationInfoByCategoryAndLocation(category, city, district);
    }

    //장소와 날짜 기반 검색
    @GetMapping("/category/{category}/city/{city}/district/{district}/from/{from}/to/{to}")
    public List<InvitationInfoDto> getInvitationInfoByLocationAndCategory(@PathVariable String category, @PathVariable String city, @PathVariable String district,
                                                                          @PathVariable LocalDate from, @PathVariable LocalDate to){
        return invitationService.getInvitationInfoByCategoryAndLocationAndDateBetween(category, city, district, from, to);
    }

    @GetMapping("/all")
    public List<InvitationInfoDto> getAll(){
        return invitationService.getAll();
    }

    @PutMapping("/update")
    public void updateInvitation(@RequestHeader(name = "Authorization") String token, @RequestBody InvitationInfoDto dto){
        invitationService.updateInvitation(token, dto);
    }

    @PostMapping("/save")
    public void saveInvitation(@RequestBody InvitationSaveDto dto){
        invitationService.saveInvitation(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteInvitation(@RequestHeader(name = "Authorization") String token, @PathVariable Long id){
        invitationService.deleteInvitation(token, id);
    }
}
