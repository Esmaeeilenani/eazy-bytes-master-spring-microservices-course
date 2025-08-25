package com.esmaeeil.eazybank.gatewayserver.integration.accounts.dto;

public class AccountSummary {

    private String name;

    private String email;

    private String mobileNumber;

    private AccountDetails accountDetails;


    public static AccountSummary notFound() {
        return new AccountSummary("not found", "not found", "0", AccountDetails.noFound());
    }


    public AccountSummary(String name, String email, String mobileNumber, AccountDetails accountDetails) {
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.accountDetails = accountDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public AccountDetails getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(AccountDetails accountDetails) {
        this.accountDetails = accountDetails;
    }
}
