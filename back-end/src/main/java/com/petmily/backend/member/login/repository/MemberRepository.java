package com.petmily.backend.member.login.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.petmily.backend.member.login.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	@Query("select count(m) from Member m where m.memberId=:memberId and m.memberPw=:memberPw")
	long countByMemberIdAndMemberPw(@Param("memberId") String memberId, @Param("memberPw") String memberPw);

	Member findByMemberId(String memberId);

	@Query("select count(m) from Member m where m.memberId=:memberId and m.memberEmail=:memberEmail")
	long countByMemberIdAndMemberEmail(@Param("memberId") String memberId, @Param("memberEmail") String memberEmail);

	// 회원가입
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO member (memberId, memberPw, memberNickname, memberEmail, memberName, memberGender, memberBirth, memberTel, memberRole) "
			+ "VALUES (:memberId, :memberPw, :memberNickname, :memberEmail, :memberName, :memberGender, :memberBirth, :memberTel, 'User')", nativeQuery = true)
	void register(@Param("memberId") String memberId, @Param("memberPw") String memberPw,
			@Param("memberNickname") String memberNickname, @Param("memberEmail") String memberEmail,
			@Param("memberName") String memberName, @Param("memberGender") String memberGender,
			@Param("memberBirth") Date memberBirth, @Param("memberTel") String memberTel);

	// 아이디 중복체크
	@Query("select count(m) from Member m where m.memberId = :memberId")
	long idCheck(@Param("memberId") String memberId);

	// 이메일 중복체크
	@Query("select count(m) from Member m where m.memberEmail = :memberEmail")
	long emailCheck(@Param("memberEmail") String memberEmail);

	// 닉네임 중복체크
	@Query("select count(m) from Member m where m.memberNickname = :memberNickname")
	long nicknameCheck(@Param("memberNickname") String memberNickname);

	// 전화번호 중복체크
	@Query("select count(m) from Member m where m.memberTel = :memberTel")
	long telCheck(@Param("memberTel") String memberTel);
	
	//회원 정보 수정(자신의 데이터 제외하고 중복체크)
	// 이메일 중복체크
	@Query("select count(m) from Member m where m.memberEmail = :memberEmail and memberNum != :memberNum")
	long emailChk(@Param("memberEmail") String memberEmail, @Param("memberNum") Long memberNum);

	// 닉네임 중복체크
	@Query("select count(m) from Member m where m.memberNickname = :memberNickname and memberNum != :memberNum")
	long nicknameChk(@Param("memberNickname") String memberNickname, Long memberNum);

	// 전화번호 중복체크
	@Query("select count(m) from Member m where m.memberTel = :memberTel and memberNum != :memberNum")
	long telChk(@Param("memberTel") String memberTel, Long memberNum);
}
