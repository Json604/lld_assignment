package com.example.payments;

public class QuickPaySDK {
    public String executePayment(String accountId, int cents) {
        return "QP-TXN#" + accountId + ":" + cents;
    }
}
