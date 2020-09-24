package com.wap.api.post.invitation.repository;

import com.wap.api.domain.entitys.Invitation;
import com.wap.api.domain.enums.InvitationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    List<Invitation> findByInvitationTypeAndCityAndDistrictAndStartDateBetweenAndEndDateBetween(InvitationType invitationType, String city, String district, LocalDateTime startDate, LocalDateTime startDate2, LocalDateTime endDate, LocalDateTime endDate2);
    List<Invitation> findByInvitationTypeAndCityAndDistrictAndEndDateAfter(InvitationType invitationType, String city, String district, LocalDateTime endDate);
}
