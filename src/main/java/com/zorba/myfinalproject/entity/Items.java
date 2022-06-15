package com.zorba.myfinalproject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name="project_Items")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="items_Id", nullable = false, insertable = false)
    private Integer foodId;

    @Column(name="item_foodName")
    private String foodName;

    @Column(name= "item_quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;


}
