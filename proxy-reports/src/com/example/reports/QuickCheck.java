package com.example.reports;

public class QuickCheck {

    public static void main(String[] args) {
        User student = new User("Priya", "STUDENT");
        User admin = new User("Jordan", "ADMIN");

        Report adminReport = new ReportProxy("R-303", "Financial Audit", "ADMIN");
        Report staffReport = new ReportProxy("R-202", "Semester Analysis", "CONFIDENTIAL");

        System.out.println("=== QuickCheck ===");
        staffReport.display(student);
        System.out.println();
        adminReport.display(admin);
        System.out.println();
        adminReport.display(admin);
    }
}
