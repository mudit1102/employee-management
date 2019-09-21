package com.work.management.entity;

import com.work.management.converter.ListConverter;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "TEAMS")
@Data
@Builder
@AllArgsConstructor
public final class Team {

    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "manager")
    private Integer manager;

    @Column(name = "createdAtTimeStamp")
    private Date createdAtTimeStamp;

    @Column(name = "lastUpdatedTimeStamp")
    private Date lastUpdatedTimeStamp;

    @Column(name = "lastUpdatedBy")
    private Integer lastUpdatedBy;

    @Column(name = "createdBy")
    private Integer createdBy;

    @Convert(converter = ListConverter.class)
    @Column(name = "employeeIds")
    private List<String> employeeIds;

}
