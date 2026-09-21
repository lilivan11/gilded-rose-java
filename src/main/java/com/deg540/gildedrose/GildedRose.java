package com.deg540.gildedrose;

import java.util.ArrayList;
import java.util.List;

public class GildedRose {

    private static final String CONJURED_NAME = "Conjured";
    private static final int STANDARD_DECREASED_DAYS_CONJURED_ITEM = 1;
    private static final int MINIMUM_QUALITY_POSSIBLE_CONJURED_ITEM = 0;
    private static final int CONJURED_ITEM_DECREASED_QUALITY_BEFORE_DAY_PASSED = 2;
    private static final int CONJURED_ITEM_DECREASED_QUALITY_AFTER_DAY_PASSED = 4;
    private static final int STANDARD_LAST_DAY_BEFORE_DAY_PASSED_ALL_ITEMS = 0;
    
    private List<Item> items = null;
    private GeneralItemUpdater generalItemUpdater = new GeneralItemUpdater();
    private SulfurasItemUpdater sulfurasItemUpdater = new SulfurasItemUpdater();
    private AgedBrieItemUpdater agedBrieItemUpdater = new AgedBrieItemUpdater();
    private BackstagePassItemUpdater backstagePassItemUpdater = new BackstagePassItemUpdater();

    public GildedRose(List<Item> items) {
        this.items = items;

    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("OMGHAI!");

        List<Item> items = new ArrayList<>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));

        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
    }

    public void updateQuality() {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            updateQualityOfItem(item);
        }
    }

    private void updateQualityOfItem(Item item){
        if (sulfurasItemUpdater.isSulfurasItem(item)) {
            sulfurasItemUpdater.update(item);
            return;
        }

        if (agedBrieItemUpdater.isAgedBrieItem(item)) {
            agedBrieItemUpdater.update(item);
            return;
        }

        if (backstagePassItemUpdater.isBackstagePassItem(item)) {
            backstagePassItemUpdater.update(item);
            return;
        }

        if (isConjuredItem(item)) {
            updateConjuredItem(item);
            return;
        }

        generalItemUpdater.update(item);
    }



    private boolean isConjuredItem(Item item) {
        return item.getName().contains(CONJURED_NAME);
    }

    private void updateConjuredItem(Item item){
        updateConjuredItemDaysLeftToSellIn(item);
        updateConjuredItemQuality(item);
    }

    private void updateConjuredItemDaysLeftToSellIn(Item item){
        item.setSellIn(item.getSellIn() - STANDARD_DECREASED_DAYS_CONJURED_ITEM);
    }

    private void updateConjuredItemQuality(Item item){
        if(isQualityBelowPossibleInConjuredItem(item)){
            return;
        }

        if(isItemDayPassed(item)){
            updateQualityAfterDayPassedInConjuredItem(item);
        }
        else{
            updateQualityBeforeDayPassedInConjuredItem(item);
        }

        setMinimumQualityIfIsBelowPossibleInConjuredItem(item);
    }

    private void updateQualityBeforeDayPassedInConjuredItem(Item item) {
        item.setQuality(item.getQuality() - CONJURED_ITEM_DECREASED_QUALITY_BEFORE_DAY_PASSED);
    }

    private void setMinimumQualityIfIsBelowPossibleInConjuredItem(Item item) {
        if(isQualityBelowPossibleInConjuredItem(item)){
            item.setQuality(MINIMUM_QUALITY_POSSIBLE_CONJURED_ITEM);
        }
    }

    private void updateQualityAfterDayPassedInConjuredItem(Item item) {
        item.setQuality(item.getQuality() - CONJURED_ITEM_DECREASED_QUALITY_AFTER_DAY_PASSED);
    }

    private boolean isQualityBelowPossibleInConjuredItem(Item item) {
        return item.getQuality() <= MINIMUM_QUALITY_POSSIBLE_CONJURED_ITEM;
    }

    private boolean isItemDayPassed(Item item){
        return item.getSellIn() < STANDARD_LAST_DAY_BEFORE_DAY_PASSED_ALL_ITEMS;
    }
}