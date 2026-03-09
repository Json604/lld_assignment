package com.example.reports;

public class App {

    public static void main(String[] args) {
        User student = new User("Maya", "STUDENT");
        User staff = new User("Dr. Chen", "STAFF");
        User admin = new User("Jordan", "ADMIN");

        Report publicReport  = new ReportProxy("R-101", "Campus Guidelines", "PUBLIC");
        Report staffReport   = new ReportProxy("R-202", "Semester Analysis",   "CONFIDENTIAL");
        Report adminReport   = new ReportProxy("R-303", "Financial Audit",     "ADMIN");

        ReportViewer viewer = new ReportViewer();

        System.out.println("=== CampusVault Demo ===");

        viewer.open(publicReport, student);
        System.out.println();

        viewer.open(staffReport, student);
        System.out.println();

        viewer.open(staffReport, staff);
        System.out.println();

        viewer.open(adminReport, admin);
        System.out.println();

        // same proxy, same admin — should NOT reload from disk
        viewer.open(adminReport, admin);
    }
}
