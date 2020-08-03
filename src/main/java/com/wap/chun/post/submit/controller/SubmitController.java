package com.wap.chun.post.submit.controller;

import com.wap.chun.post.submit.dtos.SubmitMatchInfoDto;
import com.wap.chun.post.submit.dtos.SubmitMatchSaveDto;
import com.wap.chun.post.submit.dtos.SubmitMemberSaveDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/submit")
public class SubmitController {

    // 매치를 등록하기 위해 invitation을 공지하고 그의 따른 submit을 저장한다.
    @PostMapping("/save/match")
    public void saveMatchSubmit(@RequestBody SubmitMatchSaveDto dto){

    }

    // 용병 및 멤버를 채용하기 위해 invitation을 공지하고 그의 따른 submit을 저장.
    @PostMapping("/save/member")
    public void saveMemberSubmit(@RequestBody SubmitMemberSaveDto dto){

    }

    @PutMapping("/update/submit")
    public void updateSubmit(@RequestBody SubmitMatchInfoDto dto){

    }
}
