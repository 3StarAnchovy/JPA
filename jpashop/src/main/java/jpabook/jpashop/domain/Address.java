package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Embeddable
@Getter
public class Address {
    private String city;
    private String strret;
    private String zipcode;

    //응애 절대 건들지 마세요
    protected Address() {
    }

    public Address(String city, String strret, String zipcode) {
        this.city = city;
        this.strret = strret;
        this.zipcode = zipcode;
    }
}
