package com.wap.chun.post.invitation.repository;

import com.wap.chun.domain.entitys.Invitation;
import com.wap.chun.domain.enums.InvitationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    List<Invitation> findByCategoryAndCityAndDistrictAndStartDateBetweenAndEndDateBetween(InvitationType category, String city, String district, LocalDateTime startDate, LocalDateTime startDate2, LocalDateTime endDate, LocalDateTime endDate2);
    List<Invitation> findByCategoryAndCityAndDistrictAndEndDateAfter(InvitationType category, String city, String district, LocalDateTime endDate);
    List<Invitation> findByCategoryAndEndDateAfter(InvitationType category, LocalDateTime endDate);
}
