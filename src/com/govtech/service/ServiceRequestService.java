package com.govtech.service;

import com.govtech.dao.ServiceRequestDAO;
import com.govtech.model.ServiceRequest;

import java.util.List;

public class ServiceRequestService {
    private ServiceRequestDAO serviceRequestDAO;
    
    public ServiceRequestService() {
        this.serviceRequestDAO = new ServiceRequestDAO();
    }
    
    public boolean createServiceRequest(ServiceRequest serviceRequest) {
        return serviceRequestDAO.createServiceRequest(serviceRequest);
    }
    
    public List<ServiceRequest> getServiceRequestsByCitizen(String citizenID) {
        return serviceRequestDAO.getServiceRequestsByCitizen(citizenID);
    }
    
    public List<ServiceRequest> getAllServiceRequests() {
        return serviceRequestDAO.getAllServiceRequests();
    }
}