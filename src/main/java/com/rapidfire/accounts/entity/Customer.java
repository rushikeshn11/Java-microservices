package com.rapidfire.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
@Entity
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // We are tryiing to tell the spring
    @Column(name = "customer_id")                                                    // framwework to automatically generate the primary key values
    private Long customerId;

    private String name;

    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;
}
