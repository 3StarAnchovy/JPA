package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {
    @PersistenceContext
    // EntityManager 주입
    private EntityManager em;

    // 저장 메소드 생성
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
        // 멤버 반환해도 되지 않나?
        // 커맨드랑 쿼리 분리하는게 원칙임
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
