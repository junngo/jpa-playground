package com.example.jpaplayground;

import com.example.jpaplayground.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootTest
public class EntityManagerFactoryTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    public void testEntityManager() {
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("username");
        member.setAge(13);

        // Save
        entityManager.persist(member);

        // Get one data
        Member findMember = entityManager.find(Member.class, id);
        log.info("Get one data: {}", findMember);

        // Update
        member.setAge(23);
        log.info("Update: {}", findMember);

        // List
        List<Member> memberList = entityManager
                .createQuery("select m from Member m", Member.class)
                .getResultList();
        log.info("JPQL: {}", memberList.stream().toList());
    }
}
