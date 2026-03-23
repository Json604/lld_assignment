package com.example.pen;

public class HighlighterPen implements WritingInstrument {

    private final InkCartridge cartridge;
    private boolean active;

    public HighlighterPen(InkCartridge cartridge) {
        this.cartridge = cartridge;
        this.active = false;
    }

    @Override
    public int getInkConsumptionRate() {
        return 3;
    }

    @Override
    public boolean isReady() {
        return active;
    }

    @Override
    public void uncap() {
        if (active) {
            System.out.println("[Highlighter] Already uncapped and ready.");
            return;
        }
        active = true;
        System.out.println("[Highlighter] Uncapped. Highlighting with " + cartridge.getShade() + " ink.");
    }

    @Override
    public void scribe(String text) {
        if (!active) {
            System.out.println("[Highlighter] Capped. Must uncap() before use.");
            return;
        }
        if (cartridge.isDepleted()) {
            System.out.println("[Highlighter] Ink has dried up. Cannot scribe.");
            return;
        }

        // highlighters have the highest ink consumption rate
        var rate = getInkConsumptionRate();
        var inkNeeded = text.length() * rate;
        var consumed = cartridge.useInk(inkNeeded);
        var charsScribed = consumed / rate;

        var written = text.substring(0, Math.min(charsScribed, text.length()));
        System.out.println("[Highlighter] Scribed: \"" + written + "\" | " + cartridge);

        if (charsScribed < text.length()) {
            System.out.println("[Highlighter] Ink exhausted mid-scribe!");
        }
    }

    @Override
    public void cap() {
        if (!active) {
            System.out.println("[Highlighter] Was already capped.");
            return;
        }
        active = false;
        System.out.println("[Highlighter] Capped shut.");
    }

    // Highlighters are single-use and cannot be replenished
    @Override
    public void replaceCartridge(InkCartridge cartridge) {
        throw new UnsupportedOperationException("Highlighter pens are single-use and do not support cartridge replacement.");
    }
}
