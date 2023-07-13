package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        //req : persistenceUnitName : xml에 적혀있는거
        //어플리케이션 실행시에 딱 한번만 만들어짐
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //얘는 스레드간의 공유 절대 안됨
        //요청 올때 사용하고 버려야함.
        EntityManager em = emf.createEntityManager();

        //jpa에서는 트랜잭션이란 단위 신경써야됨
        // 트랜잭션 얻을 수 있음.
        //jpa의 모든 데이터 변경은 트랜잭션 안에서 실행해야함
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();

            member.setId(2L);
            member.setName("HelloB");
            em.persist(member);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
