package com.chun.apps.invitation.controller;

import com.chun.apps.invitation.dtos.request.SubmitMatchSaveReqDto;
import com.chun.apps.invitation.dtos.request.SubmitMatchUpdateReqDto;
import com.chun.apps.invitation.dtos.request.SubmitPeopleSaveReqDto;
import com.chun.apps.invitation.dtos.request.SubmitPeopleUpdateReqDto;
import com.chun.apps.invitation.service.SubmitMatchService;
import com.chun.apps.invitation.service.SubmitPeopleService;
import com.chun.commons.dtos.invitation.SubmitMatchResDto;
import com.chun.commons.dtos.invitation.SubmitPeopleResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/submit")
public class SubmitController {
    private final SubmitMatchService submitMatchService;
    private final SubmitPeopleService submitPeopleService;

    @GetMapping("/people/{id}")
    public SubmitPeopleResDto getSubmitMemberById(@PathVariable Long id){
        return submitPeopleService.find(id);
    }

    @GetMapping("/match/{id}")
    public SubmitMatchResDto getSubmitMatchById(@PathVariable Long id){
        return submitMatchService.find(id);
    }

    @PostMapping("/match")
    public void saveMatchSubmit(@RequestHeader(name = "Authorization") String token, @RequestBody SubmitMatchSaveReqDto dto){
        submitMatchService.save(token, dto);
    }

    @PostMapping("/people")
    public void saveMemberSubmit(@RequestBody SubmitPeopleSaveReqDto dto){
        submitPeopleService.save(dto);
    }

    @PutMapping("/match")
    public void updateMatch(@RequestHeader(name = "Authorization") String token, @RequestBody SubmitMatchUpdateReqDto dto){
        submitMatchService.update(token, dto);
    }

    @PutMapping("/people")
    public void updateMember(@RequestHeader(name = "Authorization") String token, @RequestBody SubmitPeopleUpdateReqDto dto){
        submitPeopleService.update(token, dto);
    }

    @DeleteMapping("/people/{id}")
    public void deletePeople(@PathVariable Long id){
        submitPeopleService.delete(id);
    }
    @DeleteMapping("/match/{id}")
    public void deleteMatch(@RequestHeader(name = "Authorization") String token, @PathVariable Long id){
        submitMatchService.delete(token, id);
    }
}
