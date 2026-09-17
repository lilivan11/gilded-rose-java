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

    @Test
    public void lowerDaysToSellForAgedBrie() {
        List<Item> items = Arrays.asList(new Item("Aged Brie", 10, 20));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(9, items.get(0).getSellIn());
    }

    @Test
    public void increaseQualityByOneForAgedBrieBeforeSellDate() {
        List<Item> items = Arrays.asList(new Item("Aged Brie", 10, 20));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(21, items.get(0).getQuality());
    }

    @Test
    public void increaseQualityByTwoForAgedBrieAfterSellDate() {
        List<Item> items = Arrays.asList(new Item("Aged Brie", 0, 20));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(22, items.get(0).getQuality());
    }

    @Test
    public void neverIncreaseQualityAboveFiftyForAgedBrieBeforeSellDate() {
        List<Item> items = Arrays.asList(new Item("Aged Brie", 10, 50));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(50, items.get(0).getQuality());
    }

    @Test
    public void neverIncreaseQualityAboveFiftyForAgedBrieAfterSellDate() {
        List<Item> items = Arrays.asList(new Item("Aged Brie", 0, 49));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(50, items.get(0).getQuality());
    }
}
