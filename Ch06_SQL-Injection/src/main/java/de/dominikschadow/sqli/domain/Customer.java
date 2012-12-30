package de.dominikschadow.sqli.domain;

public class Customer {
    private int custId;
    private String name;
    private String status;
    private int orderLimit;

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderLimit() {
        return orderLimit;
    }

    public void setOrderLimit(int orderLimit) {
        this.orderLimit = orderLimit;
    }
    
    @Override
    public String toString() {
        StringBuilder customer = new StringBuilder();
        customer.append("ID ").append(custId);
        customer.append(", Name ").append(name);
        customer.append(", Status ").append(status);
        customer.append(", Order Limit ").append(orderLimit);
        
        return customer.toString();
    }
}
