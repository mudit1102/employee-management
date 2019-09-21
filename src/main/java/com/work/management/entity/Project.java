package com.work.management.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * This class represents a single row in Projects Table.
 */

@Entity
@Table(name = "PROJECTS")
@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public final class Project {

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
