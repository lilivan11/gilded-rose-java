package com.deg540.gildedrose;

import org.junit.Test;

import java.util.List;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class GildedRoseShould {

    @Test
    public void lowerDaysToSellByOneInGeneralItem() {
        List<Item> items = Arrays.asList(new Item("+5 Dexterity Vest", 10, 20));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(9, items.get(0).getSellIn());
    }

    @Test
    public void lowerQualityByOneForAGeneralItemBeforeSellDate() {
        List<Item> items = Arrays.asList(new Item("+5 Dexterity Vest", 10, 20));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(19, items.get(0).getQuality());
    }

    @Test
    public void lowerQualityTwiceAsFastForAGeneralItemAfterSellDate() {
        List<Item> items = Arrays.asList(new Item("+5 Dexterity Vest", 0, 20));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(18, items.get(0).getQuality());
    }

    @Test
    public void neverLowerQualityBelowZero() {
        List<Item> items = Arrays.asList(new Item("+5 Dexterity Vest", 5, 0));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(0, items.get(0).getQuality());
    }
}
