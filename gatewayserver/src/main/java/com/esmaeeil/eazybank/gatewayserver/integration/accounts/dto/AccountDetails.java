package com.esmaeeil.eazybank.gatewayserver.integration.accounts.dto;

public class AccountDetails {

    private Long accountNumber;

    private String accountType;

    private String branchAddress;


    public static AccountDetails noFound() {
        return new AccountDetails(0L, "N/A", "NotFound");
    }

    public AccountDetails(Long accountNumber, String accountType, String branchAddress) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.branchAddress = branchAddress;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }
}
