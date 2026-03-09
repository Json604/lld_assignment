package com.example.payments;

import java.util.Objects;

/**
 * Adapter wrapping SecureWalletSDK to conform to PaymentGateway interface.
 * Handles the two-step flow: initiate() then execute().
 */
public class SafeCashAdapter implements PaymentGateway {

    private final SecureWalletSDK client;

    public SafeCashAdapter(SecureWalletSDK client) {
        this.client = Objects.requireNonNull(client, "client");
    }

    @Override
    public String charge(String customerId, int amountCents) {
        SecureWalletTransaction payment = client.initiate(amountCents, customerId);
        return payment.execute();
    }
}
