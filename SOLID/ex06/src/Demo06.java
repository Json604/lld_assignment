public class Demo06 {
    public static void main(String[] args) {
        System.out.println("--- Notification System ---");

        AuditLog audit = new AuditLog();
        Notification n = new Notification("Welcome", "Hey welcome to the university portal!",
                "kartikey@university.edu", "9012345678");

        // try sending via all channels
        NotificationSender[] senders = {
            new EmailSender(audit),
            new SmsSender(audit),
            new WhatsAppSender(audit)
        };

        for (NotificationSender sender : senders) {
            SendResult res = sender.send(n);
            if (!res.ok) {
                System.out.println("FAILED: " + res.message);
                audit.add("send failed via " + sender.getClass().getSimpleName());
            }
        }

        System.out.println("Total audit entries: " + audit.size());
    }
}
