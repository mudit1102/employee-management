package com.work.management.web.rest.assembler;

import com.work.management.dto.EmployeeDto;
import com.work.management.web.rest.resource.EmployeeResource;

public final class EmployeeAssembler {

    public static EmployeeDto convert(EmployeeResource employeeResource) {
        EmployeeDto employeeDto = EmployeeDto.builder().firstName(employeeResource.getFirstName()).lastName(employeeResource.getLastName())
                .userName(employeeResource.getFirstName() + "." + employeeResource.getLastName()).manager(employeeResource.getManager())
                .phoneNumber(employeeResource.getPhoneNumber()).teamId(employeeResource.getTeamId()).build();
        return employeeDto;

    }
}
