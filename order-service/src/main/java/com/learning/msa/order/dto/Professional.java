package com.learning.msa.order.dto;


import javax.persistence.Id;
import java.util.List;

public class Professional {

    @Id
    private String serviceCode;
    private List<ServiceProvider> serviceProviderList;

    public String getServiceCode() {
        return serviceCode;
    }

    public List<ServiceProvider> getServiceProviderList() {
        return serviceProviderList;
    }

    public void setServiceProviderList(List<ServiceProvider> serviceProviderList) {
        this.serviceProviderList = serviceProviderList;
    }
}

