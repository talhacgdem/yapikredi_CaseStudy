package com.talhacgdem.yapikredi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Leave {
    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status = String.valueOf(LeaveStatus.beklemede);
    private Date startDate;
    private Date endDate;
    private Integer workDay;


    private Date createdAt;
    private Date updatedAt;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;




    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = new Date();
    }

    @PrePersist
    protected void prePersist() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

}
