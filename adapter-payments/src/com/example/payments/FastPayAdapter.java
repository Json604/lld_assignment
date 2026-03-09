package com.example.payments;

import java.util.Objects;

/**
 * Adapter wrapping QuickPaySDK to conform to PaymentGateway interface.
 */
public class FastPayAdapter implements PaymentGateway {

    private final QuickPaySDK client;

    public FastPayAdapter(QuickPaySDK client) {
        this.client = Objects.requireNonNull(client, "client");
    }

    @Override
    public String charge(String customerId, int amountCents) {
        return client.executePayment(customerId, amountCents);
    }
}
