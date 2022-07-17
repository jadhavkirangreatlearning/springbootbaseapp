package com.csi.repo;

import com.csi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    //Custom Methods

    public Employee findByEmpContactNumber(long empContactNumber);

    public Employee findByEmpEmailId(String empEmailId);


}