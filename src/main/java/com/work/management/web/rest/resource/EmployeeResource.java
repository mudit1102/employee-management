package com.work.management.web.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EmployeeResource {

    private String firstName;
    private String lastName;
    private Integer manager;
    private String teamId;
    private String phoneNumber;
    private List<String> teamIds;
}
