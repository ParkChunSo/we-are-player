package com.chun.modules.crud.invitation.match.repository;

import com.chun.modules.crud.invitation.match.entity.InvitationMatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface InvitationMatchCrudRepository extends JpaRepository<InvitationMatch, Long> {
    Optional<List<InvitationMatch>> findAllByDateAfter(LocalDateTime date);
    Optional<List<InvitationMatch>> findByCityAndDistrict(String city, String district);

}
