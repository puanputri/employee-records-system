package com.company.reports.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.reports.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    List<Employee> findByDepartment(String department);
    
    @Query("SELECT e FROM Employee e WHERE e.hireDate BETWEEN :startDate AND :endDate")
    List<Employee> findByHireDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    
    @Query("SELECT e FROM Employee e WHERE (:department IS NULL OR e.department = :department) AND " +
           "(:startDate IS NULL OR e.hireDate >= :startDate) AND " +
           "(:endDate IS NULL OR e.hireDate <= :endDate)")
    List<Employee> findByFilters(@Param("department") String department, 
                                 @Param("startDate") Date startDate, 
                                 @Param("endDate") Date endDate);
}
