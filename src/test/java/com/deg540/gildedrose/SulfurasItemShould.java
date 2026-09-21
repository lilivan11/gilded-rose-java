package com.deg540.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SulfurasItemShould {

    @Test
    public void neverIncreaseQualityAbove80ForSulfuras() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 10, 80);
        SulfurasItemUpdater sulfurasItemUpdater = new SulfurasItemUpdater();

        sulfurasItemUpdater.update(item);

        assertEquals(80, item.getQuality());
    }

    @Test
    public void neverLowerSellInForSulfurasBeforeSellDate() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 10, 50);
        SulfurasItemUpdater sulfurasItemUpdater = new SulfurasItemUpdater();

        sulfurasItemUpdater.update(item);

        assertEquals(10, item.getSellIn());
    }

    @Test
    public void neverLowerQualityForSulfurasBeforeSellDate() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 10, 50);
        SulfurasItemUpdater sulfurasItemUpdater = new SulfurasItemUpdater();

        sulfurasItemUpdater.update(item);

        assertEquals(50, item.getQuality());
    }

    @Test
    public void neverLowerSellInForSulfurasAfterSellDate() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 0, 50);
        SulfurasItemUpdater sulfurasItemUpdater = new SulfurasItemUpdater();

        sulfurasItemUpdater.update(item);

        assertEquals(0, item.getSellIn());
    }

    @Test
    public void neverLowerQualityForSulfurasAfterSellDate() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 0, 50);
        SulfurasItemUpdater sulfurasItemUpdater = new SulfurasItemUpdater();

        sulfurasItemUpdater.update(item);

        assertEquals(50, item.getQuality());
    }

    @Test
    public void neverLowerSellInForSulfurasWithNegativeSellIn() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", -1, 50);
        SulfurasItemUpdater sulfurasItemUpdater = new SulfurasItemUpdater();

        sulfurasItemUpdater.update(item);

        assertEquals(-1, item.getSellIn());
    }

    @Test
    public void neverLowerQualityForSulfurasWithNegativeSellIn() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", -1, 50);
        SulfurasItemUpdater sulfurasItemUpdater = new SulfurasItemUpdater();

        sulfurasItemUpdater.update(item);

        assertEquals(50, item.getQuality());
    }
}
