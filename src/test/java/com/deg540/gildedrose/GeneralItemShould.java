package com.deg540.gildedrose;

import org.junit.Test;

import java.util.List;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class GeneralItemShould {

    @Test
    public void decreaseDaysToSell() {
        GeneralItemUpdater generalItemUpdater = new GeneralItemUpdater();
        Item item = new Item("+5 Dexterity Vest", 10, 20);
        
        generalItemUpdater.update(item);

        assertEquals(9, item.getSellIn());
    }

    @Test
    public void decreaseQualityBeforeSellDate() {
        GeneralItemUpdater generalItemUpdater = new GeneralItemUpdater();
        Item item = new Item("+5 Dexterity Vest", 10, 20);
        
        generalItemUpdater.update(item);

        assertEquals(19, item.getQuality());
    }

    @Test
    public void decreaseQualityAfterSellDate() {
        GeneralItemUpdater generalItemUpdater = new GeneralItemUpdater();
        Item item = new Item("+5 Dexterity Vest", 0, 20);
        
        generalItemUpdater.update(item);

        assertEquals(18, item.getQuality());
    }

    @Test
    public void neverDecreaseQualityBelowMinimum() {
        GeneralItemUpdater generalItemUpdater = new GeneralItemUpdater();
        Item item = new Item("+5 Dexterity Vest", 5, 0);
    
        generalItemUpdater.update(item);

        assertEquals(0, item.getQuality());
    }
}
