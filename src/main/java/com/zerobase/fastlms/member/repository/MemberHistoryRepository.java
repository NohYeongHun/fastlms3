package com.zerobase.fastlms.member.repository;

import com.zerobase.fastlms.member.entity.MemberHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberHistoryRepository extends JpaRepository<MemberHistory, Long> {

}
