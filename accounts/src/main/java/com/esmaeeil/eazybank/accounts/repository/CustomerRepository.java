package com.esmaeeil.eazybank.accounts.repository;

import com.esmaeeil.eazybank.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByMobileNumber(String mobileNumber);

    Optional<Customer> findByMobileNumber(String mobileNumber);

}
