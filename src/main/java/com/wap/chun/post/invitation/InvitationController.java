package com.wap.chun.post.invitation;

import com.wap.chun.domain.entitys.Invitation;
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

    @GetMapping("/category/{category}/location/{location}")
    public List<InvitationInfoDto> getInvitationInfoByLocationAndCategory(@PathVariable String category, @PathVariable String location){
        return invitationService.getInvitationInfoByCategoryAndLocation(category, location);
    }

    @GetMapping("/category/{category}/location/{location}/from/{from}/to/{to}")
    public List<InvitationInfoDto> getInvitationInfoByLocationAndCategory(@PathVariable String category, @PathVariable String location,
                                                                          @PathVariable LocalDate from, @PathVariable LocalDate to){
        return invitationService.getInvitationInfoByCategoryAndLocationAndDateBetween(category, location, from, to);
    }

    @GetMapping("/all")
    public List<InvitationInfoDto> getAll(){
        return InvitationService.getAll();
    }

    @PostMapping("/save")
    public void saveInvitation(@RequestBody InvitationInfoDto dto){
        invitationService.saveInvitation(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteInvitation(@PathVariable Long id, @RequestHeader(value = "Authorize") String token){
        invitationService.deleteInvitation(id, token);
    }
}
