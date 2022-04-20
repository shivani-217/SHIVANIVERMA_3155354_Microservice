package com.learning.msa.consumer.service.impl;

import com.learning.msa.consumer.entity.Consumer;
import com.learning.msa.consumer.repository.ConsumerRepository;
import com.learning.msa.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultConsumerService implements ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;
    public Optional<Consumer> findUserById(String email)
    {
        return consumerRepository.findById(email);
    }
}
