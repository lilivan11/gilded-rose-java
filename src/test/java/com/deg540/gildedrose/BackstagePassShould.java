package com.deg540.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BackstagePassShould {

    @Test
    public void lowerDaysToSellForBackstagePasses() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        BackstagePassItemUpdater backstagePassItemUpdater = new BackstagePassItemUpdater();

        backstagePassItemUpdater.update(item);

        assertEquals(14, item.getSellIn());
    }

    @Test
    public void increaseQualityByOneForBackstagePassesWhenMoreThanTenDaysRemain() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        BackstagePassItemUpdater backstagePassItemUpdater = new BackstagePassItemUpdater();

        backstagePassItemUpdater.update(item);

        assertEquals(21, item.getQuality());
    }

    @Test
    public void increaseQualityByTwoForBackstagePassesWhenTenDaysOrLessRemain() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20);
        BackstagePassItemUpdater backstagePassItemUpdater = new BackstagePassItemUpdater();

        backstagePassItemUpdater.update(item);

        assertEquals(22, item.getQuality());
    }

    @Test
    public void increaseQualityByThreeForBackstagePassesWhenFiveDaysOrLessRemain() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20);
        BackstagePassItemUpdater backstagePassItemUpdater = new BackstagePassItemUpdater();

        backstagePassItemUpdater.update(item);

        assertEquals(23, item.getQuality());
    }

    @Test
    public void increaseQualityByThreeForBackstagePassesWhenOneDayRemains() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20);
        BackstagePassItemUpdater backstagePassItemUpdater = new BackstagePassItemUpdater();

        backstagePassItemUpdater.update(item);

        assertEquals(23, item.getQuality());
    }

    @Test
    public void dropQualityToZeroForBackstagePassesAfterTheConcert() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20);
        BackstagePassItemUpdater backstagePassItemUpdater = new BackstagePassItemUpdater();

        backstagePassItemUpdater.update(item);

        assertEquals(0, item.getQuality());
    }

    @Test
    public void lowerSellInBelowZeroForBackstagePassesAfterTheConcert() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20);
        BackstagePassItemUpdater backstagePassItemUpdater = new BackstagePassItemUpdater();

        backstagePassItemUpdater.update(item);

        assertEquals(-1, item.getSellIn());
    }

    @Test
    public void neverIncreaseQualityAboveFiftyForBackstagePassesWhenTenDaysOrLessRemain() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 48);
        BackstagePassItemUpdater backstagePassItemUpdater = new BackstagePassItemUpdater();

        backstagePassItemUpdater.update(item);

        assertEquals(50, item.getQuality());
    }

    @Test
    public void neverIncreaseQualityAboveFiftyForBackstagePassesWhenAlreadyAtFifty() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50);
        BackstagePassItemUpdater backstagePassItemUpdater = new BackstagePassItemUpdater();

        backstagePassItemUpdater.update(item);

        assertEquals(50, item.getQuality());
    }
}
