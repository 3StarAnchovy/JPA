package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain_select_조건 {
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
            //나이가 18살 이상인 회원을 모두 검색하고 싶다면..?
            //JPQL 활용해야한다.
            //테이블을 대상으로 하는게 아니라 객체를 대상으로 함 -> 멤버객체 다 가져와!!
            //jpa는 SQL을 추상화한 객체지향 쿼리 언어 제공
            // 객체를 대상으로 하는 쿼리 -> jpql
            // sql은 데이터 베이스 테이블을 대상으로 함
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();
            for (Member member : result)
                System.out.println("member.name = " + member.getUsername());
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
