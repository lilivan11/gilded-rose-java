package com.deg540.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SulfurasItemShould {

    @Test
    public void neverIncreaseQualityAboveMax() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 10, 80);
        SulfurasItemUpdater sulfurasItemUpdater = new SulfurasItemUpdater();

        sulfurasItemUpdater.update(item);

        assertEquals(80, item.getQuality());
    }

    @Test
    public void neverDecreaseSellInBeforeSellDate() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 10, 50);
        SulfurasItemUpdater sulfurasItemUpdater = new SulfurasItemUpdater();

        sulfurasItemUpdater.update(item);

        assertEquals(10, item.getSellIn());
    }

    @Test
    public void neverDecreaseQualityBeforeSellDate() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 10, 50);
        SulfurasItemUpdater sulfurasItemUpdater = new SulfurasItemUpdater();

        sulfurasItemUpdater.update(item);

        assertEquals(50, item.getQuality());
    }

    @Test
    public void neverDecreaseSellInAfterSellDate() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 0, 50);
        SulfurasItemUpdater sulfurasItemUpdater = new SulfurasItemUpdater();

        sulfurasItemUpdater.update(item);

        assertEquals(0, item.getSellIn());
    }

    @Test
    public void neverDecreaseQualityAfterSellDate() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 0, 50);
        SulfurasItemUpdater sulfurasItemUpdater = new SulfurasItemUpdater();

        sulfurasItemUpdater.update(item);

        assertEquals(50, item.getQuality());
    }

    @Test
    public void neverDecreaseSellInWithNegativeSellDate() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", -1, 50);
        SulfurasItemUpdater sulfurasItemUpdater = new SulfurasItemUpdater();

        sulfurasItemUpdater.update(item);

        assertEquals(-1, item.getSellIn());
    }

    @Test
    public void neverDecreaseQualityWithNegativeSellDate() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", -1, 50);
        SulfurasItemUpdater sulfurasItemUpdater = new SulfurasItemUpdater();

        sulfurasItemUpdater.update(item);

        assertEquals(50, item.getQuality());
    }
}
