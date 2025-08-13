package com.esmaeeil.eazybank.loans.service;

import com.esmaeeil.eazybank.loans.constants.LoansConstants;
import com.esmaeeil.eazybank.loans.dto.LoansDto;
import com.esmaeeil.eazybank.loans.entity.Loans;
import com.esmaeeil.eazybank.loans.exception.LoanAlreadyExistsException;
import com.esmaeeil.eazybank.loans.exception.ResourceNotFoundException;
import com.esmaeeil.eazybank.loans.mapper.LoansMapper;
import com.esmaeeil.eazybank.loans.repository.LoansRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Transactional
public class LoansService {

    private final LoansRepository loansRepository;


    /**
     * @param mobileNumber - Mobile Number of the Customer
     */

    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoans= loansRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber "+mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new loan details
     */
    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    /**
     *
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan with mobile number "+ mobileNumber +" is not found"));
        return LoansMapper.mapToLoansDto(loans, new LoansDto());
    }

    /**
     *
     * @param loansDto - LoansDto Object
     */

    public void updateLoan(LoansDto loansDto) {
        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan with number "+ loansDto.getLoanNumber() +" is not found"));
        LoansMapper.mapToLoans(loansDto, loans);
        loansRepository.save(loans);

    }

    /**
     * @param mobileNumber - Input MobileNumber
     * @return boolean indicating if the delete of loan details is successful or not
     */
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan with mobile number "+ mobileNumber +" is not found"));

        loansRepository.deleteById(loans.getLoanId());
        return true;
    }

}
