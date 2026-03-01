package com.example.tickets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Immutable ticket class. Once built, nothing can change.
 *
 * Fields are private final, no setters exist.
 * Tags list gets defensively copied + wrapped in unmodifiableList
 * so nobody can sneak in changes from outside.
 *
 * The only way to create one is through the Builder.
 * To "modify" a ticket, use toBuilder() to get a pre-filled builder,
 * tweak what you need, then call build() to get a fresh object.
 */
public final class IncidentTicket {

    private final String id;
    private final String reporterEmail;
    private final String title;
    private final String description;
    private final String priority;       // LOW / MEDIUM / HIGH / CRITICAL
    private final List<String> tags;
    private final String assigneeEmail;
    private final boolean customerVisible;
    private final Integer slaMinutes;
    private final String source;         // CLI, PORTAL, WEBHOOK, etc.

    // only builder can instantiate
    private IncidentTicket(Builder builder) {
        this.id = builder.id;
        this.reporterEmail = builder.reporterEmail;
        this.title = builder.title;
        this.description = builder.description;
        this.priority = builder.priority;
        this.assigneeEmail = builder.assigneeEmail;
        this.customerVisible = builder.customerVisible;
        this.slaMinutes = builder.slaMinutes;
        this.source = builder.source;
        // defensive copy — make our own list and wrap it as unmodifiable
        this.tags = Collections.unmodifiableList(new ArrayList<>(builder.tags));
    }

    // --- getters only, no setters ---

    public String getId()              { return id; }
    public String getReporterEmail()   { return reporterEmail; }
    public String getTitle()           { return title; }
    public String getDescription()     { return description; }
    public String getPriority()        { return priority; }
    public List<String> getTags()      { return tags; }  // already unmodifiable
    public String getAssigneeEmail()   { return assigneeEmail; }
    public boolean isCustomerVisible() { return customerVisible; }
    public Integer getSlaMinutes()     { return slaMinutes; }
    public String getSource()          { return source; }

    // creates a builder with all current values pre-filled
    public Builder toBuilder() {
        Builder b = new Builder(this.id, this.reporterEmail, this.title);
        b.description(this.description);
        b.priority(this.priority);
        b.assigneeEmail(this.assigneeEmail);
        b.customerVisible(this.customerVisible);
        b.slaMinutes(this.slaMinutes);
        b.source(this.source);
        // copy tags into builder's own list
        b.tags(new ArrayList<>(this.tags));
        return b;
    }

    @Override
    public String toString() {
        return "IncidentTicket{" +
                "id='" + id + "'" +
                ", reporter='" + reporterEmail + "'" +
                ", title='" + title + "'" +
                ", desc='" + description + "'" +
                ", priority='" + priority + "'" +
                ", tags=" + tags +
                ", assignee='" + assigneeEmail + "'" +
                ", visible=" + customerVisible +
                ", sla=" + slaMinutes +
                ", source='" + source + "'" +
                "}";
    }

    // ==================== Builder ====================

    public static class Builder {
        // required params
        private final String id;
        private final String reporterEmail;
        private final String title;

        // optional params with sensible defaults
        private String description = null;
        private String priority = null;
        private List<String> tags = new ArrayList<>();
        private String assigneeEmail = null;
        private boolean customerVisible = false;
        private Integer slaMinutes = null;
        private String source = null;

        public Builder(String id, String reporterEmail, String title) {
            this.id = id;
            this.reporterEmail = reporterEmail;
            this.title = title;
        }

        public Builder description(String val) {
            this.description = val;
            return this;
        }

        public Builder priority(String val) {
            this.priority = val;
            return this;
        }

        public Builder tags(List<String> list) {
            this.tags = (list != null) ? new ArrayList<>(list) : new ArrayList<>();
            return this;
        }

        public Builder addTag(String tag) {
            this.tags.add(tag);
            return this;
        }

        public Builder assigneeEmail(String val) {
            this.assigneeEmail = val;
            return this;
        }

        public Builder customerVisible(boolean val) {
            this.customerVisible = val;
            return this;
        }

        public Builder slaMinutes(Integer val) {
            this.slaMinutes = val;
            return this;
        }

        public Builder source(String val) {
            this.source = val;
            return this;
        }

        // validates everything and returns the immutable object
        public IncidentTicket build() {

            Validation.requireTicketId(id);
            Validation.requireEmail(reporterEmail, "reporterEmail");

            Validation.requireNonBlank(title, "title");
            Validation.requireMaxLen(title, 80, "title");

            if (priority != null) {
                Validation.requireOneOf(priority, "priority",
                        "LOW", "MEDIUM", "HIGH", "CRITICAL");
            }
            if (assigneeEmail != null) {
                Validation.requireEmail(assigneeEmail, "assigneeEmail");
            }
            if (slaMinutes != null) {
                Validation.requireRange(slaMinutes, 5, 7200, "slaMinutes");
            }

            return new IncidentTicket(this);
        }
    }
}
