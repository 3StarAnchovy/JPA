package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    private Long id;

    private String username;

    @Embedded
    private Address address;

    //거울일때는 필드 이름 ㅇㅇ
    //얘는 ReadOnly임
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Orders> orders = new ArrayList<>();
}
