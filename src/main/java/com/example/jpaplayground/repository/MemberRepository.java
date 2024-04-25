package com.example.jpaplayground.repository;

import com.example.jpaplayground.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
