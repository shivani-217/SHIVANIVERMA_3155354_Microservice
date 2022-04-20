package com.learning.msa.provider.service.impl;

import com.learning.msa.provider.entity.ServiceProvider;
import com.learning.msa.provider.repository.ProviderRepository;
import com.learning.msa.provider.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DefaultProviderService implements ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

//    public ServiceProvider findProviderByIdAndServiceCode(Long providerId, String serviceCode) {
//        return providerRepository.findByIdAndServiceCode(providerId, serviceCode);
//    }
}
