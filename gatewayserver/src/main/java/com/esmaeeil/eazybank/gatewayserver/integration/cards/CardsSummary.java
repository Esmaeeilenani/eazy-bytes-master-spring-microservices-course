package com.esmaeeil.eazybank.gatewayserver.integration.cards;


public class CardsSummary {


    private String cardNumber;


    private String cardType;


    private int totalLimit;


    private int amountUsed;

    private int availableAmount;

    public static CardsSummary notFound() {
        return new CardsSummary("00000", "no type", -1, 0, 0);
    }


    public CardsSummary() {
    }

    public CardsSummary(String cardNumber, String cardType, int totalLimit, int amountUsed, int availableAmount) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.totalLimit = totalLimit;
        this.amountUsed = amountUsed;
        this.availableAmount = availableAmount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getTotalLimit() {
        return totalLimit;
    }

    public void setTotalLimit(int totalLimit) {
        this.totalLimit = totalLimit;
    }

    public int getAmountUsed() {
        return amountUsed;
    }

    public void setAmountUsed(int amountUsed) {
        this.amountUsed = amountUsed;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }
}
