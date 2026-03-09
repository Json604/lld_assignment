package com.example.payments;

public class SecureWalletSDK {
    public SecureWalletTransaction initiate(int cents, String accountId) {
        return new SecureWalletTransaction(cents, accountId);
    }
}
