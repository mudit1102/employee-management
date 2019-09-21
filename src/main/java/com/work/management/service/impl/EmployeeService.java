package com.work.management.service.impl;

import com.work.management.entity.Employee;
import com.work.management.entity.EmployeeDto;
import com.work.management.repository.EmployeeRepository;
import com.work.management.web.rest.resource.EmployeeResource;
import com.work.management.service.DataPersistingService;
import com.work.management.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
final class EmployeeService implements DataPersistingService<EmployeeResource, Employee> {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResource preHandle(EmployeeResource document) {
        LOGGER.info("PreHandling employee{} ", document.getFirstName() + "." + document.getLastName());
        if (Objects.nonNull(employeeRepository.findByPhoneNumber(document.getPhoneNumber()))) {
            ExceptionUtils.throwEntityAlreadyExistsException("Employee already exists, choose a different phone Number");
        }
        return document;
    }

    @Override
    public Employee handle(EmployeeResource document) {
        LOGGER.info("Handling employee{} ", document.getFirstName() + " " + document.getLastName());
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(document, employeeDto);
        return Employee.builder().firstName(employeeDto.getFirstName()).lastName(employeeDto.getLastName()).manager(employeeDto.getManager()).
                phoneNumber(employeeDto.getPhoneNumber()).createdBy(employeeDto.getManager()).createdAtTimeStamp(new Date()).lastUpdatedTimeStamp(new Date()).
                lastUpdatedBy(employeeDto.getManager()).userName(employeeDto.getFirstName() + "." + employeeDto.getLastName()).teamId(employeeDto.getTeamId()).build();
    }

    @Override
    public void postHandle(Employee document) {
        LOGGER.info("postHandling employee{} ", document.getFirstName() + " " + document.getLastName());
        employeeRepository.save(document);
    }
}
