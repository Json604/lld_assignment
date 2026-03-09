package com.example.payments;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Map<String, PaymentGateway> gateways = new HashMap<>();
        gateways.put("quickpay", new FastPayAdapter(new QuickPaySDK()));
        gateways.put("securewallet", new SafeCashAdapter(new SecureWalletSDK()));

        OrderService svc = new OrderService(gateways);

        String id1 = svc.charge("quickpay", "cust-101", 2499);
        String id2 = svc.charge("securewallet", "cust-202", 2499);
        
        System.out.println(id1);
        System.out.println(id2);
    }
}
