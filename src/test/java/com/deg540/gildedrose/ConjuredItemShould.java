package com.deg540.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConjuredItemShould {

    @Test
    public void lowerDaysToSellByOneInConjuredItem() {
        Item item = new Item("Conjured Mana Cake", 10, 20);
        ConjuredItemUpdater conjuredItemUpdater = new ConjuredItemUpdater();

        conjuredItemUpdater.update(item);

        assertEquals(9, item.getSellIn());
    }

    @Test
    public void lowerQualityByTwoForAConjuredItemBeforeSellDate() {
        Item item = new Item("Conjured Mana Cake", 10, 20);
        ConjuredItemUpdater conjuredItemUpdater = new ConjuredItemUpdater();

        conjuredItemUpdater.update(item);

        assertEquals(18, item.getQuality());
    }

    @Test
    public void lowerQualityByFourForAConjuredItemAfterSellDate() {
        Item item = new Item("Conjured Mana Cake", 0, 20);
        ConjuredItemUpdater conjuredItemUpdater = new ConjuredItemUpdater();

        conjuredItemUpdater.update(item);

        assertEquals(16, item.getQuality());
    }

    @Test
    public void neverLowerQualityBelowZeroForConjuredItemBeforeSellDate() {
        Item item = new Item("Conjured Mana Cake", 5, 1);
        ConjuredItemUpdater conjuredItemUpdater = new ConjuredItemUpdater();

        conjuredItemUpdater.update(item);

        assertEquals(0, item.getQuality());
    }

    @Test
    public void neverLowerQualityBelowZeroForConjuredItemAfterSellDate() {
        Item item = new Item("Conjured Mana Cake", 0, 3);
        ConjuredItemUpdater conjuredItemUpdater = new ConjuredItemUpdater();

        conjuredItemUpdater.update(item);

        assertEquals(0, item.getQuality());
    }
}
