package com.learning.msa.provider.controller;

import com.learning.msa.provider.entity.Professional;
import com.learning.msa.provider.repository.ProviderRepository;
import com.learning.msa.provider.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/provider")
public class ProviderController {

    @Resource
    private ProviderService providerService;

    @Autowired
    private ProviderRepository providerRepository;


   @GetMapping("/{serviceCode}")
   public Professional findOrderById(@PathVariable Long serviceCode)
   {
       return providerRepository.findById(serviceCode).get();
   }


    @PostMapping
    public String saveAllServiceProviders(@RequestBody final List<Professional> professionalList)
    {
        providerRepository.saveAll(professionalList);
        return "Service Providers Data Saved successfully";
    }
}
