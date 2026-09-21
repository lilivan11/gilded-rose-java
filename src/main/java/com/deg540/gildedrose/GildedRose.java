package com.deg540.gildedrose;

import java.util.ArrayList;
import java.util.List;

public class GildedRose {

    private static final String SULFURAS_NAME = "Sulfuras";
    private static final String AGED_BRIE_NAME = "Aged Brie";
    private static final int STANDARD_DECREASED_DAYS_AGED_BRIE = 1;
    private static final int AGED_BRIE_INCREASED_QUALITY_BEFORE_DAY_PASSED = 1;
    private static final int AGED_BRIE_INCREASED_QUALITY_AFTER_DAY_PASSED = 2;
    private static final int AGED_BRIE_MAX_QUALITY = 50;
    private static final String BACKSTAGE_PASSES_NAME = "Backstage passes";
    private static final int STANDARD_DECREASED_DAYS_BACKSTAGE_PASSES = 1;
    private static final int BACKSTAGE_PASSES_INCREASED_QUALITY_IN_FIRST_PERIOD = 1;
    private static final int BACKSTAGE_PASSES_INCREASED_QUALITY_IN_SECOND_PERIOD = 2;
    private static final int BACKSTAGE_PASSES_INCREASED_QUALITY_IN_THIRD_PERIOD = 3;
    private static final int BACKSTAGE_PASSES_MAX_QUALITY = 50;
    private static final int FIRST_DAY_TO_CONSIDER_BACKSTAGE_PASS = -1;
    private static final int DAYS_LEFT_IN_THIRD_PERIOD_BACKSTAGE_PASSES = 6;
    private static final int DAYS_LEFT_IN_SECOND_PERIOD_BACKSTAGE_PASSES = 11;
    private static final String CONJURED_NAME = "Conjured";
    private static final int STANDARD_DECREASED_DAYS_CONJURED_ITEM = 1;
    private static final int MINIMUM_QUALITY_POSSIBLE_CONJURED_ITEM = 0;
    private static final int CONJURED_ITEM_DECREASED_QUALITY_BEFORE_DAY_PASSED = 2;
    private static final int CONJURED_ITEM_DECREASED_QUALITY_AFTER_DAY_PASSED = 4;
    private static final int STANDARD_LAST_DAY_BEFORE_DAY_PASSED_ALL_ITEMS = 0;
    
    private List<Item> items = null;
    private GeneralItemUpdater generalItemUpdater = new GeneralItemUpdater();

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
        if (isSulfurasItem(item)) {
            updateSulfurasItem(item);
            return;
        }

        if (isAgedBrieItem(item)) {
            updateAgedBrieItem(item);
            return;
        }

        if (isBackstagePassesItem(item)) {
            updateBackstagePassesItem(item);
            return;
        }

        if (isConjuredItem(item)) {
            updateConjuredItem(item);
            return;
        }

        generalItemUpdater.update(item);
    }

    private boolean isSulfurasItem(Item item) {
        return item.getName().contains(SULFURAS_NAME);
    }

    private void updateSulfurasItem(Item item){
        return;
    }

    private boolean isAgedBrieItem(Item item) {
        return item.getName().contains(AGED_BRIE_NAME);
    }

    private void updateAgedBrieItem(Item item) {
        updateAgedBrieDaysLeftToSellIn(item);
        updateAgedBrieQuality(item);
    }

    private void updateAgedBrieDaysLeftToSellIn(Item item){
        item.setSellIn(item.getSellIn() - STANDARD_DECREASED_DAYS_AGED_BRIE);
    }

    private void updateAgedBrieQuality(Item item) {
        if(hasAgedBrieItemMaxQuality(item)){
            return;
        }

        if (isItemDayPassed(item)) {
            updateQualityBeforeDayPassedInAgedBrie(item);
        }
        else{
            updateQualityAfterDayPassedInAgedBrie(item);
        }

        setMaxQualityIfItIsExcededInAgedBrie(item);
    }

    private void updateQualityAfterDayPassedInAgedBrie(Item item) {
        item.setQuality(item.getQuality() + AGED_BRIE_INCREASED_QUALITY_BEFORE_DAY_PASSED);
    }

    private void updateQualityBeforeDayPassedInAgedBrie(Item item) {
        item.setQuality(item.getQuality() + AGED_BRIE_INCREASED_QUALITY_AFTER_DAY_PASSED);
    }

    private void setMaxQualityIfItIsExcededInAgedBrie(Item item) {
        if(hasAgedBrieItemMaxQuality(item)){
            item.setQuality(AGED_BRIE_MAX_QUALITY);
        }
    }

    private boolean hasAgedBrieItemMaxQuality(Item item){
        return item.getQuality() >= AGED_BRIE_MAX_QUALITY;
    }

    private boolean isBackstagePassesItem(Item item) {
        return item.getName().contains(BACKSTAGE_PASSES_NAME);
    }

    private void updateBackstagePassesItem(Item item){
        updateBackstagePassesDaysLeftToSellIn(item);
        updateBackstagePassesQuality(item);
    }

    private void updateBackstagePassesDaysLeftToSellIn(Item item){
        item.setSellIn(item.getSellIn() - STANDARD_DECREASED_DAYS_BACKSTAGE_PASSES);
    }

    private void updateBackstagePassesQuality(Item item) {
        if(isExpiredBackstagePasses(item)){
            expireBackstagePasses(item);
            return;
        }
        
        if(hasBackstagePassesMaxQuality(item)){
            setMaxQualityIfItIsExcededInBackstagePass(item);
            return;
        }

        if(isInThirdPeriodBackstagePasses(item)){
            updateQualityForThirdPeriodBackstagePass(item);
            setMaxQualityIfItIsExcededInBackstagePass(item);
            return;
        }

        if(isInSecondPeriodBackstagePasses(item)){
            updateQualityForSecondPeriodBackstagePass(item);
            setMaxQualityIfItIsExcededInBackstagePass(item);
            return;
        }

        updateQualityForFirstPeriodBackstagePass(item);
        setMaxQualityIfItIsExcededInBackstagePass(item);
    }

    private void updateQualityForFirstPeriodBackstagePass(Item item) {
        item.setQuality(item.getQuality() + BACKSTAGE_PASSES_INCREASED_QUALITY_IN_FIRST_PERIOD);
    }

    private void updateQualityForSecondPeriodBackstagePass(Item item) {
        item.setQuality(item.getQuality() + BACKSTAGE_PASSES_INCREASED_QUALITY_IN_SECOND_PERIOD);
    }

    private void updateQualityForThirdPeriodBackstagePass(Item item) {
        item.setQuality(item.getQuality() + BACKSTAGE_PASSES_INCREASED_QUALITY_IN_THIRD_PERIOD);
    }

    private void setMaxQualityIfItIsExcededInBackstagePass(Item item) {
        if(hasBackstagePassesMaxQuality(item)){
            item.setQuality(BACKSTAGE_PASSES_MAX_QUALITY);
        }
    }

    private boolean hasBackstagePassesMaxQuality(Item item){
        return item.getQuality() >= BACKSTAGE_PASSES_MAX_QUALITY;
    }

    private boolean isExpiredBackstagePasses(Item item){
        return item.getSellIn() <= FIRST_DAY_TO_CONSIDER_BACKSTAGE_PASS;
    }

    private boolean isInThirdPeriodBackstagePasses(Item item) {
        return item.getSellIn() < DAYS_LEFT_IN_THIRD_PERIOD_BACKSTAGE_PASSES;
    }

    private boolean isInSecondPeriodBackstagePasses(Item item) {
        return item.getSellIn() < DAYS_LEFT_IN_SECOND_PERIOD_BACKSTAGE_PASSES;
    }

    private void expireBackstagePasses(Item item) {
        item.setQuality(0);
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