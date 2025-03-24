package com.company.reports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.reports.dto.ReportRequest;
import com.company.reports.service.ReportService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    
    @Autowired
    private ReportService reportService;
    
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<byte[]> generateEmployeeReport(
            @PathVariable Long employeeId,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) throws ParseException {
        
        ReportRequest request = new ReportRequest();
        request.setEmployeeId(employeeId);
        request.setDepartment(department);
        
        if (startDate != null && !startDate.isEmpty()) {
            request.setStartDate(dateFormat.parse(startDate));
        }
        
        if (endDate != null && !endDate.isEmpty()) {
            request.setEndDate(dateFormat.parse(endDate));
        }
        
        byte[] reportBytes = reportService.generateEmployeeReport(request);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "employee_report.pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        
        return new ResponseEntity<>(reportBytes, headers, HttpStatus.OK);
    }
    
    @GetMapping("/department/{department}")
    public ResponseEntity<byte[]> generateDepartmentReport(
            @PathVariable String department,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) throws ParseException {
        
        ReportRequest request = new ReportRequest();
        
        if (startDate != null && !startDate.isEmpty()) {
            request.setStartDate(dateFormat.parse(startDate));
        }
        
        if (endDate != null && !endDate.isEmpty()) {
            request.setEndDate(dateFormat.parse(endDate));
        }
        
        byte[] reportBytes = reportService.generateDepartmentReport(department, request);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", department + "_report.pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        
        return new ResponseEntity<>(reportBytes, headers, HttpStatus.OK);
    }
}
