package com.talhacgdem.yapikredi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Employee {
    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private Date workStartDate;

    public Employee() {
    }

    public Employee(String name, String surname, Date workStartDate) {
        this.name = name;
        this.surname = surname;
        this.workStartDate = workStartDate;
    }

}
