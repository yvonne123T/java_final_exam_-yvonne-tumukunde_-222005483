package com.govtech.model;

public class Department {
    private String departmentID;
    private String name;
    private String address;
    private int capacity;
    private String manager;
    private String contact;

    public Department() {}

    public Department(String departmentID, String name, String address, int capacity, String manager, String contact) {
        this.departmentID = departmentID;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.manager = manager;
        this.contact = contact;
    }

    public String getDepartmentID() { return departmentID; }
    public void setDepartmentID(String departmentID) { this.departmentID = departmentID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public String getManager() { return manager; }
    public void setManager(String manager) { this.manager = manager; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
}