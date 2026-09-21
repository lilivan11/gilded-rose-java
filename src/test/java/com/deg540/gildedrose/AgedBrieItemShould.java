package com.deg540.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AgedBrieItemShould {

    @Test
    public void decreaseDaysToSell() {
        Item item = new Item("Aged Brie", 10, 20);
        AgedBrieItemUpdater agedBrieItemUpdater = new AgedBrieItemUpdater();

        agedBrieItemUpdater.update(item);

        assertEquals(9, item.getSellIn());
    }

    @Test
    public void increaseQualityBeforeSellDate() {
        Item item = new Item("Aged Brie", 10, 20);
        AgedBrieItemUpdater agedBrieItemUpdater = new AgedBrieItemUpdater();

        agedBrieItemUpdater.update(item);

        assertEquals(21, item.getQuality());
    }

    @Test
    public void increaseQualityAfterSellDate() {
        Item item = new Item("Aged Brie", 0, 20);
        AgedBrieItemUpdater agedBrieItemUpdater = new AgedBrieItemUpdater();

        agedBrieItemUpdater.update(item);

        assertEquals(22, item.getQuality());
    }

    @Test
    public void neverIncreaseQualityAboveMaxBeforeSellDate() {
        Item item = new Item("Aged Brie", 10, 50);
        AgedBrieItemUpdater agedBrieItemUpdater = new AgedBrieItemUpdater();

        agedBrieItemUpdater.update(item);

        assertEquals(50, item.getQuality());
    }

    @Test
    public void neverIncreaseQualityAboveMaxAfterSellDate() {
        Item item = new Item("Aged Brie", 0, 49);
        AgedBrieItemUpdater agedBrieItemUpdater = new AgedBrieItemUpdater();

        agedBrieItemUpdater.update(item);

        assertEquals(50, item.getQuality());
    }
}
