public class EmailSender extends NotificationSender {
    private static final int BODY_LIMIT = 40;

    public EmailSender(AuditLog audit) { super(audit); }

    @Override
    public SendResult send(Notification n) {
        // truncate body if needed
        String body = n.body;
        if (body.length() > BODY_LIMIT) {
            body = body.substring(0, BODY_LIMIT);
        }
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + body);
        audit.add("email sent to " + n.email);
        return SendResult.success();
    }
}
