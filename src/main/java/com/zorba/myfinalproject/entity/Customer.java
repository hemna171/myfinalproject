package com.zorba.myfinalproject.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="project_Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "cust_Id" , nullable = false, insertable = false)
    private Integer custId;

    @Column(name="cust_Name")
    private String custName;

    @Column(name= "cust_mobNum")
    private Long mobNum;

    @Column(name= "cust_Country")
    private String country;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Items> itemsList =  new ArrayList<>();

    public  void addItems(Items items){
        this.itemsList.add(items);
        items.setCustomer(this);
    }
}
