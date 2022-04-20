package com.learning.msa.consumer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consumer {

    @Id
    private String email;
    private String name;
    private String address;
    private String contactNumber;

}
