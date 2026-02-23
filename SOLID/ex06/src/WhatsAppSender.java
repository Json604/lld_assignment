public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) { super(audit); }

    @Override
    public SendResult send(Notification n) {
        // whatsapp needs phone with country code prefix
        if (n.phone == null || !n.phone.startsWith("+")) {
            return SendResult.failure("phone must start with + and country code");
        }
        System.out.println("WHATSAPP -> " + n.phone + " | " + n.body);
        audit.add("whatsapp sent to " + n.phone);
        return SendResult.success();
    }
}
