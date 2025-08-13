package com.esmaeeil.eazybank.loans.repository;

import com.esmaeeil.eazybank.loans.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface LoansRepository extends JpaRepository<Loans, Long> {
    Optional<Loans> findByLoanNumber(String loanNumber);

    Optional<Loans> findByMobileNumber(String mobileNumber);
}
