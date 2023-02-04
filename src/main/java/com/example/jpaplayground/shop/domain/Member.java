package com.example.jpaplayground.shop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String name;

    @Embedded
    private Address address;

    // @JsonIgnore
    @OneToMany(mappedBy = "member") // Member(one) <-> Order(Many)
    private List<Order> orders = new ArrayList<>();
}
