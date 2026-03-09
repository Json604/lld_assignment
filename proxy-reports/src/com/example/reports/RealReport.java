package com.example.reports;

/**
 * Concrete report implementation with expensive loading logic.
 * Loading happens in constructor, so proxy controls when instantiation occurs.
 */
public class RealReport implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final String content;

    public RealReport(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
        this.content = loadFromDisk();
    }

    private String loadFromDisk() {
        System.out.println("[disk] loading report " + reportId + " ...");
        try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return "Internal report body for " + title;
    }

    @Override
    public void display(User user) {
        System.out.println("REPORT -> id=" + reportId
                + " title=" + title
                + " classification=" + classification
                + " openedBy=" + user.getName());
        System.out.println("CONTENT: " + content);
    }

    public String getClassification() { return classification; }
}
