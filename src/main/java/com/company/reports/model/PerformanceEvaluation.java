package com.company.reports.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "performance_evaluations", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id", "evaluation_year"}))
public class PerformanceEvaluation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    
    @Column(name = "evaluation_year", nullable = false)
    private Integer evaluationYear;
    
    @Column(nullable = false)
    private BigDecimal rating;
    
    @Column
    private String feedback;
    
    @Column(name = "evaluation_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date evaluationDate;
    
    // Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getEvaluationYear() {
        return evaluationYear;
    }

    public void setEvaluationYear(Integer evaluationYear) {
        this.evaluationYear = evaluationYear;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Date getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(Date evaluationDate) {
        this.evaluationDate = evaluationDate;
    }
}
