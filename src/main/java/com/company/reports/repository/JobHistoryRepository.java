package com.company.reports.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.reports.model.JobHistory;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {
    
    List<JobHistory> findByEmployeeIdOrderByStartDateDesc(Long employeeId);
}
