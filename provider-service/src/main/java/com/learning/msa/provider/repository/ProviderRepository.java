package com.learning.msa.provider.repository;

import com.learning.msa.provider.entity.Professional;
import com.learning.msa.provider.entity.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Professional,Long> {


}
