package com.deg540.gildedrose;

public class AgedBrieItemUpdater {

    private static final String NAME = "Aged Brie";
    private static final int DECREASED_DAYS = 1;
    private static final int INCREASED_QUALITY_BEFORE_DAY_PASSED = 1;
    private static final int INCREASED_QUALITY_AFTER_DAY_PASSED = 2;
    private static final int MAX_QUALITY = 50;
    private static final int LAST_DAY_BEFORE_DAY_PASSED = 0;

    public boolean isAgedBrieItem(Item item) {
        return item.getName().contains(NAME);
    }

    public void update(Item item) {
        updateDaysLeftToSellIn(item);
        updateQuality(item);
    }

    private void updateDaysLeftToSellIn(Item item) {
        item.setSellIn(item.getSellIn() - DECREASED_DAYS);
    }

    private void updateQuality(Item item) {
        if (hasMaxQuality(item)) {
            return;
        }

        if (isItemDayPassed(item)) {
            increaseQuality(item, INCREASED_QUALITY_AFTER_DAY_PASSED);
        } else {
            increaseQuality(item, INCREASED_QUALITY_BEFORE_DAY_PASSED);
        }
    }

    private boolean hasMaxQuality(Item item) {
        return item.getQuality() == MAX_QUALITY;
    }

    private boolean isItemDayPassed(Item item) {
        return item.getSellIn() < LAST_DAY_BEFORE_DAY_PASSED;
    }

    private void increaseQuality(Item item,int increasedQuality){
        item.setQuality(item.getQuality() + increasedQuality);
        setMaxQualityIfExceeded(item);
    }

    private void setMaxQualityIfExceeded(Item item) {
        if (isMaxQualityExceeded(item)) {
            item.setQuality(MAX_QUALITY);
        }
    }

    private boolean isMaxQualityExceeded(Item item){
        return item.getQuality() > MAX_QUALITY;
    }
}
