package com.work.management.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * This class represents a single row in Projects Table.
 */
@Builder
@Entity
@Table(name = "PROJECTS")
@Setter
@Getter
@NoArgsConstructor
public class Project {
    @Column(name = "name")
    String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    String id;
    @Column(name = "owner")
    Integer owner;
    @Column(name = "startDate")
    Date startDate;
    @Column(name = "endDate")
    Date endDate;
    @Column(name = "createdAtTimeStamp")
    Date createdAtTimeStamp;
    @Column(name = "lastUpdatedTimeStamp")
    Date lastUpdatedTimeStamp;
    @Column(name = "lastUpdatedBy")
    Integer lastUpdatedBy;
    @Column(name = "createdBy")
    Integer createdBy;

}
