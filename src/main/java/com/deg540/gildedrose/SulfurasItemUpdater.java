package com.deg540.gildedrose;

public class SulfurasItemUpdater {

    private static final String NAME = "Sulfuras";

    public boolean isSulfurasItem(Item item) {
        return item.getName().contains(NAME);
    }

    public void update(Item item) {
        return;
    }
}
