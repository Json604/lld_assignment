package com.example.tickets;

import java.util.regex.Pattern;

// all validation logic lives here so we dont repeat ourselves across the codebase
public final class Validation {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    private static final Pattern ID_PATTERN = Pattern.compile("^[A-Z0-9-]+$");

    private Validation() {
        // utility class, no instances needed
    }

    public static void requireNonBlank(String val, String field) {
        if (val == null || val.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " cannot be blank");
        }
    }

    public static void requireMaxLen(String val, int max, String field) {
        if (val != null && val.length() > max) {
            throw new IllegalArgumentException(field + " exceeds max length of " + max);
        }
    }

    public static void requireEmail(String email, String field) {
        requireNonBlank(email, field);
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException(field + " is not a valid email address");
        }
    }

    public static void requireTicketId(String id) {
        requireNonBlank(id, "id");
        requireMaxLen(id, 20, "id");
        if (!ID_PATTERN.matcher(id).matches()) {
            throw new IllegalArgumentException("id must only contain A-Z, 0-9, or hyphens");
        }
    }

    public static void requireOneOf(String val, String field, String... options) {
        if (val == null) return;
        for (String opt : options) {
            if (opt.equals(val)) return;
        }
        throw new IllegalArgumentException(field + " must be one of: " + String.join(", ", options));
    }

    public static void requireRange(Integer val, int lo, int hi, String field) {
        if (val == null) return;
        if (val < lo || val > hi) {
            throw new IllegalArgumentException(field + " must be between " + lo + " and " + hi);
        }
    }
}
