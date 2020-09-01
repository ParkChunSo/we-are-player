package com.chun.crud.repository;

import com.chun.crud.entity.InvitationMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationMemberCrudRepository extends JpaRepository<InvitationMember, Long> {
}
