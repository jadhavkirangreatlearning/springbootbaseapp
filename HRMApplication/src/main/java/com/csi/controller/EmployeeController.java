package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")

@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @GetMapping("/services")
    public String sayServices(){
        return "SOFTWARE DEVELOPMENT SERVICES";
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword){
        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId, empPassword));
    }
    @GetMapping("/getdatabyid/{empId}")
    public ResponseEntity<Optional<Employee>> getDataById(@PathVariable int empId){

        log.info("************Trying to fetch data for: "+empId);
        return ResponseEntity.ok(employeeServiceImpl.getDataById(empId));
    }

    @GetMapping("/getalldata")
    public List<Employee> getAllData(){

        log.info("************Employee Records : "+employeeServiceImpl.getAllData().size());
        return employeeServiceImpl.getAllData();
    }

    @GetMapping("/getdatabycontactnumber/{empContactNumber}")
    public Employee getDataByContactNumber(@PathVariable long empContactNumber){
        return employeeServiceImpl.getDataByContactNumber(empContactNumber);
    }

    @GetMapping("/getdatabyemail/{empEmailId}")
    public Employee getDatByEmail(@PathVariable String empEmailId){
        return employeeServiceImpl.getDatByEmail(empEmailId);
    }

    @GetMapping("/filterdata/{empSalary}")
    public List<Employee> filterData(@PathVariable double empSalary){
        return employeeServiceImpl.filterDataBySalary(empSalary);
    }

    @PostMapping("/signup")
    public Employee signUp(@RequestBody Employee employee){

        log.info("************Trying to save data for: "+employee.getEmpName());
        return employeeServiceImpl.signUp(employee);
    }
    @PostMapping("/savealldata")
    public List<Employee> saveAllData(@RequestBody List<Employee> employees){
        return employeeServiceImpl.saveBulOfData(employees);
    }
    @PutMapping("/updatedata/{empId}")
    public Employee updateData(@PathVariable int empId, @RequestBody Employee employee) throws RecordNotFoundException {
        //
        log.info("************Trying to update data for: "+employee.getEmpName());
        Employee employee1 = employeeServiceImpl.getDataById(empId).orElseThrow(()->new RecordNotFoundException("Employee data not available"));


        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());
        return employeeServiceImpl.updateData(employee1);
    }
    @DeleteMapping("/deletedata/{empId}")
    public String deleteDataById(@PathVariable int empId){
        employeeServiceImpl.deleteDataById(empId);
        return "Data Deleted Successfully";
    }
    @DeleteMapping("/deletealldata")
    public String deleteAllData(){
        employeeServiceImpl.deleteAllData();
        return "All Data Deleted Successfully";
    }
}
