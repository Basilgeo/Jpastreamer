package com.ust.Employee_jpastreamer.controller;

import com.ust.Employee_jpastreamer.model.Employee;
import com.ust.Employee_jpastreamer.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/save")
    public List<Employee> saveEmployee(@RequestBody List<Employee> employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/groupByCity")
    public Map<String, List<Employee>> groupbyEmployeeByCity(){
        return employeeService.groupbyEmployeeByCity();
    }
    @GetMapping("/findall")
    public List<Employee> groupbyEmployeeByPaymentTier(){
        return employeeService.groupbyEmployeeByPaymentTier();
    }
    @GetMapping("/countByGender/{gender}")
    public long countEmployeeByGender(@PathVariable String gender){
        return employeeService.countEmployeeByGender(gender);
    }
    @GetMapping("/ageRange")
    public List<Employee> findEmployeesByAgeRange(@RequestParam int minAge, @RequestParam int maxAge) {
        return employeeService.findEmployeesByAgeRange(minAge, maxAge);
    }
    @GetMapping("/joiningYear")
    public List<Employee> findEmployeesByJoiningYear(@RequestParam int year) {
        return employeeService.findEmployeesByJoiningYear(year);
    }
    @GetMapping("/genderCountByYear/{year}/{gender}")
    public Long getGenderCountByYear(@PathVariable int year, @PathVariable String gender) {
        return employeeService.getGenderCountByYear(year, gender);
    }
    @GetMapping("/groupByEducation")
    public List<Employee> groupByEmployeeByEducation( ) {
        return employeeService.groupByEducation( );
    }
    @GetMapping("/search")
    public List<Employee> searchEmployees(@RequestParam(required = false) String education,
                                          @RequestParam(required = false) Integer joiningYear,
                                          @RequestParam(required = false) Integer age,
                                          @RequestParam(required = false) String gender
                                          ) {
        return employeeService.searchEmployees(education, joiningYear, age, gender);
    }

}