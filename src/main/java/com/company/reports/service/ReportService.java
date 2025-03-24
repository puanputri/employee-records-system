package com.company.reports.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.reports.dto.ReportRequest;
import com.company.reports.exception.ReportGenerationException;
import com.company.reports.exception.ResourceNotFoundException;
import com.company.reports.model.Employee;
import com.company.reports.repository.EmployeeRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class ReportService {
    
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    public byte[] generateEmployeeReport(ReportRequest request) {
        try {
            // Validate employee exists if employeeId is provided
            if (request.getEmployeeId() != null) {
                Employee employee = employeeRepository.findById(request.getEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + request.getEmployeeId()));
            }
            
            // Load report template
            InputStream mainReportStream = getClass().getResourceAsStream("/reports/main_report.jrxml");
            if (mainReportStream == null) {
                throw new ReportGenerationException("Main report template not found");
            }
            
            // Compile main report
            JasperReport mainReport = JasperCompileManager.compileReport(mainReportStream);
            
            // Set parameters
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("departmentParam", request.getDepartment());
            parameters.put("startDateParam", request.getStartDate());
            parameters.put("endDateParam", request.getEndDate());
            parameters.put("employeeId", request.getEmployeeId());
            
            // Add subreport directory
            String subreportDir = getClass().getResource("/reports/").getPath();
            parameters.put("SUBREPORT_DIR", subreportDir);
            
            // Get database connection
            Connection conn = dataSource.getConnection();
            
            // Fill report
            JasperPrint jasperPrint = JasperFillManager.fillReport(mainReport, parameters, conn);
            
            // Export to PDF
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            
            // Close resources
            conn.close();
            
            return outputStream.toByteArray();
            
        } catch (Exception e) {
            if (e instanceof ResourceNotFoundException) {
                throw (ResourceNotFoundException) e;
            }
            throw new ReportGenerationException("Error generating employee report", e);
        }
    }
    
    public byte[] generateDepartmentReport(String department, ReportRequest request) {
        try {
            // Validate department exists
            if (department == null || department.isEmpty()) {
                throw new ResourceNotFoundException("Department parameter is required");
            }
            
            // Load report template
            InputStream departmentReportStream = getClass().getResourceAsStream("/reports/department_report.jrxml");
            if (departmentReportStream == null) {
                throw new ReportGenerationException("Department report template not found");
            }
            
            // Compile report
            JasperReport departmentReport = JasperCompileManager.compileReport(departmentReportStream);
            
            // Set parameters
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("departmentParam", department);
            parameters.put("startDateParam", request.getStartDate());
            parameters.put("endDateParam", request.getEndDate());
            
            // Get database connection
            Connection conn = dataSource.getConnection();
            
            // Fill report
            JasperPrint jasperPrint = JasperFillManager.fillReport(departmentReport, parameters, conn);
            
            // Export to PDF
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            
            // Close resources
            conn.close();
            
            return outputStream.toByteArray();
            
        } catch (Exception e) {
            if (e instanceof ResourceNotFoundException) {
                throw (ResourceNotFoundException) e;
            }
            throw new ReportGenerationException("Error generating department report", e);
        }
    }
}
