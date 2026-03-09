package com.example.payments;

public class SecureWalletTransaction {
    private final int cents;
    private final String accountId;

    public SecureWalletTransaction(int cents, String accountId) {
        this.cents = cents;
        this.accountId = accountId;
    }

    public String execute() {
        return "SW-CONF[" + accountId + "," + cents + "]";
    }
}
