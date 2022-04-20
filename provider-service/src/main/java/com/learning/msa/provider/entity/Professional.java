package com.learning.msa.provider.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professional {

    @Id
    private Long serviceCode;
    private String serviceName;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ServiceProvider> serviceProviderList;


    public List<ServiceProvider> getServiceProviderList() {
        return serviceProviderList;
    }

    public void setServiceProviderList(List<ServiceProvider> serviceProviderList) {
        this.serviceProviderList = serviceProviderList;
    }
}
