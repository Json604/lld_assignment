package com.example.tickets;

// handles ticket operations
// every method that "changes" a ticket actually returns a new one —
// the original object is never touched
public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        return new IncidentTicket.Builder(id, reporterEmail, title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .addTag("NEW")
                .build();
    }

    public IncidentTicket assign(IncidentTicket ticket, String assignee) {
        return ticket.toBuilder()
                .assigneeEmail(assignee)
                .build();
    }

    public IncidentTicket escalateToCritical(IncidentTicket ticket) {
        return ticket.toBuilder()
                .priority("CRITICAL")
                .addTag("ESCALATED")
                .build();
    }
}
