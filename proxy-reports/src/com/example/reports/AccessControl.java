package com.example.reports;

public class AccessControl {

    public boolean canAccess(User user, String classification) {
        String role = user.getRole();

        if ("PUBLIC".equals(classification)) return true;
        if ("CONFIDENTIAL".equals(classification)) {
            return "STAFF".equals(role) || "ADMIN".equals(role);
        }
        if ("ADMIN".equals(classification)) {
            return "ADMIN".equals(role);
        }
        return false;
    }
}
