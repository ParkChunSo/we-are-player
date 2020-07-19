package com.wap.chun.post.invitation;

import com.wap.chun.security.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvitationService {
    private final InvitationRepository invitationRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public static List<InvitationInfoDto> getAll() {

        return null;
    }

    public InvitationInfoDto getInvitationInfoById(Long id) {
        return null;
    }

    public List<InvitationInfoDto> getInvitationInfoByCategoryAndLocation(String category, String location) {
        return null;
    }

    public List<InvitationInfoDto> getInvitationInfoByCategoryAndLocationAndDateBetween(String category, String location, LocalDate from, LocalDate to) {
        return null;
    }

    public void saveInvitation(InvitationInfoDto dto) {

    }

    public void deleteInvitation(Long id, String token) {

    }
}
