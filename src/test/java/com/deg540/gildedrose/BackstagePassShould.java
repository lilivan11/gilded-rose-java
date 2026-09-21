package com.deg540.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BackstagePassShould {

    @Test
    public void decreaseDaysToSell() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        BackstagePassItemUpdater backstagePassItemUpdater = new BackstagePassItemUpdater();

        backstagePassItemUpdater.update(item);

        assertEquals(14, item.getSellIn());
    }

    @Test
    public void increaseQualityInFirstPeriod() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        BackstagePassItemUpdater backstagePassItemUpdater = new BackstagePassItemUpdater();

        backstagePassItemUpdater.update(item);

        assertEquals(21, item.getQuality());
    }

    @Test
    public void increaseQualityInSecondPeriod() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20);
        BackstagePassItemUpdater backstagePassItemUpdater = new BackstagePassItemUpdater();

        backstagePassItemUpdater.update(item);

        assertEquals(22, item.getQuality());
    }

    @Test
    public void increaseQualityInThirdPeriod() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20);
        BackstagePassItemUpdater backstagePassItemUpdater = new BackstagePassItemUpdater();

        backstagePassItemUpdater.update(item);

        assertEquals(23, item.getQuality());
    }

    @Test
    public void increaseQualityInFinalDayOfThirdPeriod() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20);
        BackstagePassItemUpdater backstagePassItemUpdater = new BackstagePassItemUpdater();

        backstagePassItemUpdater.update(item);

        assertEquals(23, item.getQuality());
    }

    @Test
    public void expireAfterEvent() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20);
        BackstagePassItemUpdater backstagePassItemUpdater = new BackstagePassItemUpdater();

        backstagePassItemUpdater.update(item);

        assertEquals(0, item.getQuality());
    }

    @Test
    public void neverIncreaseQualityAboveMaxInFirstPeriod() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 20, 50);
        BackstagePassItemUpdater backstagePassItemUpdater = new BackstagePassItemUpdater();

        backstagePassItemUpdater.update(item);

        assertEquals(50, item.getQuality());
    }

    @Test
    public void neverIncreaseQualityAboveMaxInSecondPeriod() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 9, 49);
        BackstagePassItemUpdater backstagePassItemUpdater = new BackstagePassItemUpdater();

        backstagePassItemUpdater.update(item);

        assertEquals(50, item.getQuality());
    }

    @Test
    public void neverIncreaseQualityAboveMaxInThirdPeriod() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 3, 49);
        BackstagePassItemUpdater backstagePassItemUpdater = new BackstagePassItemUpdater();

        backstagePassItemUpdater.update(item);

        assertEquals(50, item.getQuality());
    }
}
