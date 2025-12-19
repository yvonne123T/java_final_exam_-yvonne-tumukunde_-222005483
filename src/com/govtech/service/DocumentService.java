package com.govtech.service;

import com.govtech.dao.DepartmentDAO;
import com.govtech.model.Department;
import java.util.List;

public class DepartmentService {
    private DepartmentDAO departmentDAO;
    
    public DepartmentService() {
        this.departmentDAO = new DepartmentDAO();
    }
    
    public List<Department> getAllDepartments() {
        return departmentDAO.getAllDepartments();
    }
    
    public Department getDepartmentById(String departmentID) {
        return departmentDAO.getDepartmentById(departmentID);
    }
    
    public List<Department> getDepartmentsByCapacity(int minCapacity) {
        return departmentDAO.getAllDepartments().stream()
                .filter(dept -> dept.getCapacity() >= minCapacity)
                .toList();
    }
    
    public List<Department> searchDepartmentsByName(String name) {
        return departmentDAO.getAllDepartments().stream()
                .filter(dept -> dept.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
    
    public int getTotalDepartmentCapacity() {
        return departmentDAO.getAllDepartments().stream()
                .mapToInt(Department::getCapacity)
                .sum();
    }
    
    public int getDepartmentCount() {
        return departmentDAO.getAllDepartments().size();
    }
    
    public Department getDepartmentWithHighestCapacity() {
        return departmentDAO.getAllDepartments().stream()
                .max((d1, d2) -> Integer.compare(d1.getCapacity(), d2.getCapacity()))
                .orElse(null);
    }
    
    public Department getDepartmentWithLowestCapacity() {
        return departmentDAO.getAllDepartments().stream()
                .min((d1, d2) -> Integer.compare(d1.getCapacity(), d2.getCapacity()))
                .orElse(null);
    }
    
    public double getAverageDepartmentCapacity() {
        return departmentDAO.getAllDepartments().stream()
                .mapToInt(Department::getCapacity)
                .average()
                .orElse(0.0);
    }
    
    public List<String> getAllDepartmentManagers() {
        return departmentDAO.getAllDepartments().stream()
                .map(Department::getManager)
                .toList();
    }
    
    public boolean validateDepartmentData(Department department) {
        if (department == null) {
            return false;
        }
        if (department.getName() == null || department.getName().trim().isEmpty()) {
            return false;
        }
        if (department.getCapacity() < 0) {
            return false;
        }
        if (department.getManager() == null || department.getManager().trim().isEmpty()) {
            return false;
        }
        return true;
    }
}