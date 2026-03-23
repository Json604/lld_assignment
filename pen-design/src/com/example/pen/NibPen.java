package com.example.pen;

public class NibPen implements WritingInstrument {

    private InkCartridge cartridge;
    private boolean active;

    public NibPen(InkCartridge cartridge) {
        this.cartridge = cartridge;
        this.active = false;
    }

    @Override
    public int getInkConsumptionRate() {
        return 2;
    }

    @Override
    public boolean isReady() {
        return active;
    }

    @Override
    public void uncap() {
        if (active) {
            System.out.println("[Nib] Cap has already been taken off.");
            return;
        }
        active = true;
        System.out.println("[Nib] Cap off. Nib exposed with " + cartridge.getShade() + " ink loaded.");
    }

    @Override
    public void scribe(String text) {
        if (!active) {
            System.out.println("[Nib] Still capped. Use uncap() before scribing.");
            return;
        }
        if (cartridge.isDepleted()) {
            System.out.println("[Nib] Reservoir has run dry. Cannot scribe.");
            return;
        }

        // nib pens consume more ink per character due to broader tip
        var rate = getInkConsumptionRate();
        var inkNeeded = text.length() * rate;
        var consumed = cartridge.useInk(inkNeeded);
        var charsScribed = consumed / rate;

        var written = text.substring(0, Math.min(charsScribed, text.length()));
        System.out.println("[Nib] Scribed: \"" + written + "\" | " + cartridge);

        if (charsScribed < text.length()) {
            System.out.println("[Nib] Ink ran out while scribing!");
        }
    }

    @Override
    public void cap() {
        if (!active) {
            System.out.println("[Nib] Already capped.");
            return;
        }
        active = false;
        System.out.println("[Nib] Capped securely.");
    }

    // Replenishes the existing reservoir (simulates filling from an ink bottle)
    @Override
    public void replaceCartridge(InkCartridge cartridge) {
        this.cartridge.restore();
        System.out.println("[Nib] Reservoir replenished from bottle. " + this.cartridge);
    }
}
