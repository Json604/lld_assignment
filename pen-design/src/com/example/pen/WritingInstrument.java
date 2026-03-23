package com.example.pen;

public interface WritingInstrument {

    void uncap();

    void scribe(String text);

    void cap();

    void replaceCartridge(InkCartridge cartridge);

    boolean isReady();

    int getInkConsumptionRate();
}
