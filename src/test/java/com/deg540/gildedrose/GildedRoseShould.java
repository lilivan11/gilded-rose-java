package com.deg540.gildedrose;

import org.junit.Test;

import java.util.List;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class GildedRoseShould {

    @Test
    public void neverIncreaseQualityAbove80ForSulfuras() {
        List<Item> items = Arrays.asList(new Item("Sulfuras, Hand of Ragnaros", 10, 80));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(80, items.get(0).getQuality());
    }

    @Test
    public void neverLowerSellInForSulfurasBeforeSellDate() {
        List<Item> items = Arrays.asList(new Item("Sulfuras, Hand of Ragnaros", 10, 50));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(10, items.get(0).getSellIn());
    }

    @Test
    public void neverLowerQualityForSulfurasBeforeSellDate() {
        List<Item> items = Arrays.asList(new Item("Sulfuras, Hand of Ragnaros", 10, 50));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(50, items.get(0).getQuality());
    }

    @Test
    public void neverLowerSellInForSulfurasAfterSellDate() {
        List<Item> items = Arrays.asList(new Item("Sulfuras, Hand of Ragnaros", 0, 50));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(0, items.get(0).getSellIn());
    }

    @Test
    public void neverLowerQualityForSulfurasAfterSellDate() {
        List<Item> items = Arrays.asList(new Item("Sulfuras, Hand of Ragnaros", 0, 50));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(50, items.get(0).getQuality());
    }

    @Test
    public void neverLowerSellInForSulfurasWithNegativeSellIn() {
        List<Item> items = Arrays.asList(new Item("Sulfuras, Hand of Ragnaros", -1, 50));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(-1, items.get(0).getSellIn());
    }

    @Test
    public void neverLowerQualityForSulfurasWithNegativeSellIn() {
        List<Item> items = Arrays.asList(new Item("Sulfuras, Hand of Ragnaros", -1, 50));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(50, items.get(0).getQuality());
    }

    @Test
    public void lowerDaysToSellForBackstagePasses() {
        List<Item> items = Arrays.asList(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(14, items.get(0).getSellIn());
    }

    @Test
    public void increaseQualityByOneForBackstagePassesWhenMoreThanTenDaysRemain() {
        List<Item> items = Arrays.asList(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(21, items.get(0).getQuality());
    }

    @Test
    public void increaseQualityByTwoForBackstagePassesWhenTenDaysOrLessRemain() {
        List<Item> items = Arrays.asList(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(22, items.get(0).getQuality());
    }

    @Test
    public void increaseQualityByThreeForBackstagePassesWhenFiveDaysOrLessRemain() {
        List<Item> items = Arrays.asList(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(23, items.get(0).getQuality());
    }

    @Test
    public void increaseQualityByThreeForBackstagePassesWhenOneDayRemains() {
        List<Item> items = Arrays.asList(new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(23, items.get(0).getQuality());
    }

    @Test
    public void dropQualityToZeroForBackstagePassesAfterTheConcert() {
        List<Item> items = Arrays.asList(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(0, items.get(0).getQuality());
    }

    @Test
    public void lowerSellInBelowZeroForBackstagePassesAfterTheConcert() {
        List<Item> items = Arrays.asList(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(-1, items.get(0).getSellIn());
    }

    @Test
    public void neverIncreaseQualityAboveFiftyForBackstagePassesWhenTenDaysOrLessRemain() {
        List<Item> items = Arrays.asList(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 48));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(50, items.get(0).getQuality());
    }

    @Test
    public void neverIncreaseQualityAboveFiftyForBackstagePassesWhenAlreadyAtFifty() {
        List<Item> items = Arrays.asList(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(50, items.get(0).getQuality());
    }

    @Test
    public void lowerDaysToSellByOneInConjuredItem() {
        List<Item> items = Arrays.asList(new Item("Conjured Mana Cake", 10, 20));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(9, items.get(0).getSellIn());
    }

    @Test
    public void lowerQualityByTwoForAConjuredItemBeforeSellDate() {
        List<Item> items = Arrays.asList(new Item("Conjured Mana Cake", 10, 20));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(18, items.get(0).getQuality());
    }

    @Test
    public void lowerQualityByFourForAConjuredItemAfterSellDate() {
        List<Item> items = Arrays.asList(new Item("Conjured Mana Cake", 0, 20));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(16, items.get(0).getQuality());
    }

    @Test
    public void neverLowerQualityBelowZeroForConjuredItemBeforeSellDate() {
        List<Item> items = Arrays.asList(new Item("Conjured Mana Cake", 5, 1));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(0, items.get(0).getQuality());
    }

    @Test
    public void neverLowerQualityBelowZeroForConjuredItemAfterSellDate() {
        List<Item> items = Arrays.asList(new Item("Conjured Mana Cake", 0, 3));
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(0, items.get(0).getQuality());
    }
}
