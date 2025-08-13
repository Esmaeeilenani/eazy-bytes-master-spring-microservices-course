package com.esmaeeil.eazybank.accounts.repository;

import com.esmaeeil.eazybank.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByMobileNumber(String mobileNumber);

}
