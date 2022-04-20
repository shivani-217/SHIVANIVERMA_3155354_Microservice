package com.learning.msa.consumer.controller;

import com.learning.msa.consumer.entity.Consumer;
import com.learning.msa.consumer.repository.ConsumerRepository;
import com.learning.msa.consumer.service.ConsumerService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Resource
    private ConsumerService consumerService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<Consumer> findAll()
    {
        return consumerRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Consumer createConsumer(@RequestBody Consumer consumer)
    {
        return consumerRepository.save(consumer);
    }

    @GetMapping("/{email}")
    private Consumer getConsumerById(@PathVariable(value="email")String email)
    {
        Optional<Consumer> optional =  consumerService.findUserById(email);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
