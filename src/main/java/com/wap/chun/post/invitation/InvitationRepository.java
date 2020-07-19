package com.wap.chun.post.invitation;

import com.wap.chun.domain.entitys.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    List<Invitation> findAllByStartDateAfterAndEndDateBefore(LocalDateTime startDate, LocalDateTime endDate);
}
