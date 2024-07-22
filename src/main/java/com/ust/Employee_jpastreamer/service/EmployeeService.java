package com.ust.Employee_jpastreamer.service;



import com.speedment.jpastreamer.application.JPAStreamer;
import com.ust.Employee_jpastreamer.model.Employee;
import com.ust.Employee_jpastreamer.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    private final JPAStreamer jpaStreamer;

    @Autowired
    public EmployeeService(final JPAStreamer jpaStreamer) {
        this.jpaStreamer = jpaStreamer;
    }


    public Map<String, List<Employee>> groupbyEmployeeByCity() {
        return jpaStreamer.stream(Employee.class)
                .collect(Collectors.groupingBy(Employee::getCity));
    }

    public List<Employee> groupbyEmployeeByPaymentTier() {
        return employeeRepository.findAll() ;
    }

    public List<Employee> saveEmployee(List<Employee> employee) {
        return employeeRepository.saveAll(employee);
    }

    public long countEmployeeByGender(String gender)
    {
        return jpaStreamer.stream(Employee.class)
                .filter(employee -> employee.getGender().equalsIgnoreCase(gender))
                .count();
    }
    public List<Employee> findEmployeesByAgeRange(int minAge, int maxAge) {
        return jpaStreamer.stream(Employee.class)
                .filter(employee -> employee.getAge() >= minAge && employee.getAge() <= maxAge)
                .toList();
    }
    public List<Employee> findEmployeesByJoiningYear(int year) {
        return jpaStreamer.stream(Employee.class)
                .filter(employee -> employee.getJoiningYear() == year)
                .toList();
    }
    public Long getGenderCountByYear(int year, String gender) {
        return jpaStreamer.stream(Employee.class)
                .filter(employee -> employee.getJoiningYear() == year && employee.getGender().equalsIgnoreCase(gender))
                .count();
    }

    public List<Employee> groupByEducation()
    {
            return jpaStreamer.stream(Employee.class)
                    .collect(Collectors.groupingBy(Employee::getEducation))
                    .entrySet().stream()
                    .flatMap(entry -> entry.getValue().stream())
                    .toList();
    }
    public List<Employee> searchEmployees(String education, Integer joiningYear, Integer age, String gender) {
        return jpaStreamer.stream(Employee.class)
                .filter(employee -> education == null || employee.getEducation().equalsIgnoreCase(education))
                .filter(employee -> joiningYear == null || employee.getJoiningYear() == joiningYear)
                .filter(employee -> age == null || employee.getAge() == age)
                .filter(employee -> gender == null || employee.getGender().equalsIgnoreCase(gender))
                .toList();
    }




}





