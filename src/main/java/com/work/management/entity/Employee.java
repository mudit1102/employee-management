package com.work.management.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "EMPLOYEES")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public final class Employee {

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "userName")
    private String userName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "manager")
    private Integer manager;

    @Column(name = "teamId")
    private String teamId;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "createdAtTimeStamp")
    private Date createdAtTimeStamp;

    @Column(name = "lastUpdatedTimeStamp")
    private Date lastUpdatedTimeStamp;

    @Column(name = "lastUpdatedBy")
    private Integer lastUpdatedBy;

    @Column(name = "createdBy")
    private Integer createdBy;

}
