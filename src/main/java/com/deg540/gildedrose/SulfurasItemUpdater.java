package com.deg540.gildedrose;

public class SulfurasItemUpdater {

    private static final String SULFURAS_NAME = "Sulfuras";

    public boolean isSulfurasItem(Item item) {
        return item.getName().contains(SULFURAS_NAME);
    }

    public void update(Item item) {
        return;
    }
}
