package com.example.pen;

public class App {

    public static void main(String[] args) {

        // --- ClickPen Demonstration ---
        System.out.println("=== Click Pen ===");
        var blueCartridge = new InkCartridge(Shade.BLUE, 20);
        WritingInstrument click = new ClickPen(blueCartridge);

        click.scribe("test");                // should warn: not ready
        click.uncap();
        click.scribe("Hello World");         // consumes 11 ink
        click.scribe("More text");           // consumes 9 ink, depletes
        click.scribe("nothing");             // should warn: depleted

        // swap in a fresh cartridge
        click.replaceCartridge(new InkCartridge(Shade.BLACK, 20));
        click.scribe("Back in action");
        click.cap();

        // --- NibPen Demonstration ---
        System.out.println("\n=== Nib Pen ===");
        var blackCartridge = new InkCartridge(Shade.BLACK, 30);
        WritingInstrument nib = new NibPen(blackCartridge);

        nib.uncap();
        nib.scribe("Elegant");              // 7 chars x 2 = 14 ink
        nib.scribe("Writing");              // 7 chars x 2 = 14 ink
        nib.scribe("More");                 // 4 chars x 2 = 8 ink, only 2 left

        // replenish from ink bottle (restores existing reservoir)
        nib.replaceCartridge(null);
        nib.scribe("Refilled");
        nib.cap();

        // --- HighlighterPen Demonstration ---
        System.out.println("\n=== Highlighter Pen ===");
        var redCartridge = new InkCartridge(Shade.RED, 30);
        WritingInstrument highlighter = new HighlighterPen(redCartridge);

        highlighter.uncap();
        highlighter.scribe("BOLD");          // 4 chars x 3 = 12 ink
        highlighter.scribe("HEADING");       // 7 chars x 3 = 21 ink, only 18 left

        // attempt to replace cartridge on a highlighter (should throw)
        try {
            highlighter.replaceCartridge(new InkCartridge(Shade.RED, 30));
        } catch (UnsupportedOperationException ex) {
            System.out.println("[Highlighter] Replacement failed: " + ex.getMessage());
        }

        highlighter.cap();
    }
}
