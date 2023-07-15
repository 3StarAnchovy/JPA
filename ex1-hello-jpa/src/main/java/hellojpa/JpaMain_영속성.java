package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain_영속성 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //비 영속 상태
            //JPA랑 상관없음. DB에 들어가지도 않음
            Member member = new Member();
            member.setId(100L);
            member.setUsername("helloJPA");

            //영속, 객체를 저장한상태
            //영속성 컨텍스트에 저장, 이때 DB에 저장되는건 아님.
            em.persist(member);

            //회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
            //em.detach(member);

            //삭제
            //em.remove(member);
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
