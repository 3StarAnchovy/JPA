package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain_select {
    public static void main(String[] args) {
        //req : persistenceUnitName : xml에 적혀있는거
        //어플리케이션 실행시에 딱 한번만 만들어짐
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        //jpa에서는 트랜잭션이란 단위 신경써야됨
        // 트랜잭션 얻을 수 있음.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member findMember = em.find(Member.class, 1L); // 자바 컬렉션같은거, 2번째 인자값은 PK

            System.out.println("id : " + findMember.getId());
            System.out.println("id : " + findMember.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
