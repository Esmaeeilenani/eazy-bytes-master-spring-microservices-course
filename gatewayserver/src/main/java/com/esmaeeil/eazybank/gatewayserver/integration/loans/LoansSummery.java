package com.esmaeeil.eazybank.gatewayserver.integration.loans;


public class LoansSummery {

    private String loanNumber;


    private String loanType;

    private int totalLoan;


    private int amountPaid;


    private int outstandingAmount;

    public static LoansSummery notFound() {
        return new LoansSummery("N/A", "N/A", 0, 0, 0);
    }


    public LoansSummery(String loanNumber, String loanType, int totalLoan, int amountPaid, int outstandingAmount) {
        this.loanNumber = loanNumber;
        this.loanType = loanType;
        this.totalLoan = totalLoan;
        this.amountPaid = amountPaid;
        this.outstandingAmount = outstandingAmount;
    }

    public String getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public int getTotalLoan() {
        return totalLoan;
    }

    public void setTotalLoan(int totalLoan) {
        this.totalLoan = totalLoan;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public int getOutstandingAmount() {
        return outstandingAmount;
    }

    public void setOutstandingAmount(int outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }
}
