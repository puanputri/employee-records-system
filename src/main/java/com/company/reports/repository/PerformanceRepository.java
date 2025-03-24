package com.company.reports.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.reports.model.PerformanceEvaluation;

@Repository
public interface PerformanceEvaluationRepository extends JpaRepository<PerformanceEvaluation, Long> {
    
    List<PerformanceEvaluation> findByEmployeeIdOrderByEvaluationYearDesc(Long employeeId);
}
