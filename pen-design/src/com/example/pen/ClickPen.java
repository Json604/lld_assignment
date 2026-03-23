package com.example.pen;

public class ClickPen implements WritingInstrument {

    private InkCartridge cartridge;
    private boolean deployed;

    public ClickPen(InkCartridge cartridge) {
        this.cartridge = cartridge;
        this.deployed = false;
    }

    @Override
    public int getInkConsumptionRate() {
        return 1;
    }

    @Override
    public boolean isReady() {
        return deployed;
    }

    @Override
    public void uncap() {
        if (deployed) {
            System.out.println("[Click] Tip is already extended.");
            return;
        }
        deployed = true;
        System.out.println("[Click] Tip extended. Writing with " + cartridge.getShade() + " ink.");
    }

    @Override
    public void scribe(String text) {
        if (!deployed) {
            System.out.println("[Click] Tip retracted. Please call uncap() first.");
            return;
        }
        if (cartridge.isDepleted()) {
            System.out.println("[Click] No ink remaining. Unable to scribe.");
            return;
        }

        var inkNeeded = text.length() * getInkConsumptionRate();
        var consumed = cartridge.useInk(inkNeeded);

        var written = text.substring(0, consumed / getInkConsumptionRate());
        System.out.println("[Click] Scribed: \"" + written + "\" | " + cartridge);

        if (consumed < inkNeeded) {
            System.out.println("[Click] Ink depleted during scribing!");
        }
    }

    @Override
    public void cap() {
        if (!deployed) {
            System.out.println("[Click] Tip is already retracted.");
            return;
        }
        deployed = false;
        System.out.println("[Click] Tip retracted.");
    }

    @Override
    public void replaceCartridge(InkCartridge cartridge) {
        this.cartridge = cartridge;
        System.out.println("[Click] Swapped in new cartridge: " + cartridge);
    }
}
