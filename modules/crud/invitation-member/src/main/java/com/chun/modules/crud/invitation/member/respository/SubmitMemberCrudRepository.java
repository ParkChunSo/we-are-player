package com.chun.modules.crud.invitation.member.respository;

import com.chun.modules.crud.invitation.member.entity.SubmitMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmitMemberCrudRepository extends JpaRepository<SubmitMember, Long> {
}
