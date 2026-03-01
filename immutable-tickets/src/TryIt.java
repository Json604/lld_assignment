import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

// quick demo to show the immutable ticket design in action
public class TryIt {

    public static void main(String[] args) {
        TicketService svc = new TicketService();

        // create a ticket
        IncidentTicket t1 = svc.createTicket("BUG-2047", "neha.sharma@gmail.com", "Login page crashes on Safari");
        System.out.println("Created ticket: " + t1);

        // assign it — returns a NEW ticket, t1 stays the same
        IncidentTicket t2 = svc.assign(t1, "rahul.dev@company.in");
        System.out.println("\nAfter assigning:");
        System.out.println("  New ticket : " + t2);
        System.out.println("  Original   : " + t1);

        // escalate — again a new object
        IncidentTicket t3 = svc.escalateToCritical(t2);
        System.out.println("\nAfter escalation:");
        System.out.println("  Escalated  : " + t3);
        System.out.println("  Previous   : " + t2);

        // try to hack the tags list from outside
        List<String> tagList = t3.getTags();
        try {
            tagList.add("INJECTED");
            System.out.println("\nWhoops — tags got modified from outside!");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nCant modify tags from outside — UnsupportedOperationException thrown.");
        }

        // build one manually with the builder
        IncidentTicket custom = new IncidentTicket.Builder("REQ-007", "priya.k@outlook.com", "AC not working in lab 4")
                .priority("HIGH")
                .description("Lab 4 second floor, AC makes noise but no cooling")
                .addTag("MAINTENANCE")
                .addTag("URGENT")
                .slaMinutes(120)
                .source("PORTAL")
                .customerVisible(true)
                .build();
        System.out.println("\nCustom built ticket: " + custom);
    }
}
