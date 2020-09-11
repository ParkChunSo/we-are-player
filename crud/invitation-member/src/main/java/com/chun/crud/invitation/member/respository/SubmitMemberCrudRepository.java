package com.chun.crud.invitation.member.respository;

import com.chun.crud.invitation.member.entity.SubmitMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmitMemberCrudRepository extends JpaRepository<SubmitMember, Long> {
}
