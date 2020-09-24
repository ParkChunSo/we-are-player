package com.chun.modules.crud.invitation.member.respository;

import com.chun.commons.enums.InvitationType;
import com.chun.modules.crud.invitation.member.entity.InvitationMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface InvitationMemberCrudRepository extends JpaRepository<InvitationMember, Long> {
     Optional<List<InvitationMember>> findAllByInvitationTypeAndEndDateAfter(InvitationType type, LocalDateTime endDate);
     Optional<List<InvitationMember>> findByClub_CityAndClub_District(String clubCity, String clubDistrict);
}
