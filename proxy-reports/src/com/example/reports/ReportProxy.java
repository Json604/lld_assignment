package com.example.reports;

/**
 * Proxy controlling report access with authorization and lazy loading.
 *
 * - Permission check happens first. Unauthorized users are blocked.
 * - RealReport is instantiated only on first authorized access (lazy).
 * - Subsequent accesses reuse the same RealReport instance (caching).
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();

    // null until first authorized access
    private RealReport realReport;

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("ACCESS DENIED: " + user.getName()
                    + " cannot view \"" + title + "\"");
            return;
        }

        if (realReport == null) {
            realReport = new RealReport(reportId, title, classification);
        }

        realReport.display(user);
    }
}
