package com.learning.msa.consumer.service;

import com.learning.msa.consumer.entity.Consumer;

import java.util.Optional;

public interface ConsumerService {


    Optional<Consumer> findUserById(String email);

}
