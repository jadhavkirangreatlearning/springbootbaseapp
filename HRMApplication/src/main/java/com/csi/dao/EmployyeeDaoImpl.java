package com.csi.dao;

import com.csi.model.Employee;
import com.csi.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EmployyeeDaoImpl {

    @Autowired
    EmployeeRepository employeeRepositoryImpl;

    public Employee signUp(Employee employee){
        return employeeRepositoryImpl.save(employee);
    }

    public boolean signIn(String empEmailId, String empPassword){
        boolean status= false;

        for(Employee emp: employeeRepositoryImpl.findAll()){
            if(emp.getEmpEmailId().equals(empEmailId) && emp.getEmpPassword().equals(empPassword)){
                status=true;
            }
        }

        return status;
    }

    public List<Employee> saveBulOfData(List<Employee> employees){
        return employeeRepositoryImpl.saveAll(employees);
    }

    public Optional<Employee> getDataById(int empId){
        return employeeRepositoryImpl.findById(empId);
    }

    public Employee getDatByEmail(String empEmailId){
        return employeeRepositoryImpl.findByEmpEmailId(empEmailId);
    }

    public Employee getDataByContactNumber(long empContactNumber){
        return employeeRepositoryImpl.findByEmpContactNumber(empContactNumber);
    }

    public List<Employee> filterDataBySalary(double empSalary){

     return employeeRepositoryImpl.findAll().stream().filter(emp->emp.getEmpSalary()>=empSalary).collect(Collectors.toList());
    }

    public List<Employee> getAllData(){
        return employeeRepositoryImpl.findAll();
    }

    public Employee updateData(Employee employee){
        return employeeRepositoryImpl.save(employee);
    }

    public void deleteDataById(int empId){
        employeeRepositoryImpl.deleteById(empId);
    }

    public void deleteAllData(){
        employeeRepositoryImpl.deleteAll();
    }
}
