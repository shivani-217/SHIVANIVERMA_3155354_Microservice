package com.learning.msa.consumer.repository;

import com.learning.msa.consumer.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer,String> {
}
