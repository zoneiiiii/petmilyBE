package com.petmily.backend.member.login.repository;

import com.petmily.backend.member.login.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select count(m) from Member m where m.memberId=:memberId and m.memberPw=:memberPw")
    long countByMemberIdAndMemberPw(@Param("memberId") String memberId, @Param("memberPw") String memberPw);

    Member findByMemberId(String memberId);

    @Query("select count(m) from Member m where m.memberId=:memberId and m.memberEmail=:memberEmail")
    long countByMemberIdAndMemberEmail(@Param("memberId") String memberId, @Param("memberEmail") String memberEmail);
}
