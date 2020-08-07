package com.wap.chun.post.submit.controller;

import com.wap.chun.post.submit.dtos.*;
import com.wap.chun.post.submit.service.SubmitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/submit")
public class SubmitController {
    private final SubmitService submitService;

    @GetMapping("/member/{id}")
    public SubmitMemberInfoDto getSubmitMemberById(@PathVariable Long id){
        return submitService.getSubmitMemberById(id);
    }

    @GetMapping("/match/{id}")
    public SubmitMatchInfoDto getSubmitMatchById(@PathVariable Long id){
        return submitService.getSubmitMatchById(id);
    }

    // 매치를 등록하기 위해 invitation을 공지하고 그의 따른 submit을 저장한다.
    @PostMapping("/save/match")
    public void saveMatchSubmit(@RequestBody SubmitMatchSaveDto dto){
        submitService.saveSubmitForMatch(dto);
    }

    // 용병 및 멤버를 채용하기 위해 invitation을 공지하고 그의 따른 submit을 저장.
    @PostMapping("/save/member")
    public void saveMemberSubmit(@RequestBody SubmitMemberSaveDto dto){
        submitService.saveSubmitForMemberOrMercenary(dto);
    }

    @PostMapping("/update/match")
    public void updateMatch(@RequestBody SubmitMatchUpdateDto dto){
        submitService.updateSubmitForMatch(dto);
    }

    @PostMapping("/update/member")
    public void updateMember(@RequestBody SubmitMemberUpdateDto dto){
        submitService.updateSubmitForMember(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void updateSubmit(@PathVariable Long id){
        submitService.deleteSubmit(id);
    }
}
