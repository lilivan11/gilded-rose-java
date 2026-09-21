package com.deg540.gildedrose;

public class GeneralItemUpdater {

    private static final int DECREASED_DAYS = 1;
    private static final int MINIMUM_QUALITY = 0;
    private static final int DECREASED_QUALITY_BEFORE_DAY_PASSED = 1;
    private static final int DECREASED_QUALITY_AFTER_DAY_PASSED = 2;
    private static final int LAST_DAY_BEFORE_DAY_PASSED = 0;

    public void update(Item item) {
        updateDaysLeftToSellIn(item);
        updateQuality(item);
    }

    private static void updateDaysLeftToSellIn(Item item) {
        item.setSellIn(item.getSellIn() - DECREASED_DAYS);
    }

    private void updateQuality(Item item) {
        if (isQualityBelowPossible(item)) {
            return;
        }

        if (isItemDayPassed(item)) {
            decreaseQuality(item, DECREASED_QUALITY_AFTER_DAY_PASSED);
        } else {
            decreaseQuality(item, DECREASED_QUALITY_BEFORE_DAY_PASSED);
        }       
    }

    private static boolean isQualityBelowPossible(Item item) {
        return item.getQuality() <= MINIMUM_QUALITY;
    }

    private static boolean isItemDayPassed(Item item) {
        return item.getSellIn() < LAST_DAY_BEFORE_DAY_PASSED;
    }

    private void decreaseQuality(Item item, int decreasedQuality){
        item.setQuality(item.getQuality() - decreasedQuality);
        setMinimumQualityIfIsBelowPossible(item);
    }

    private static void setMinimumQualityIfIsBelowPossible(Item item) {
        if (isQualityBelowPossible(item)) {
            item.setQuality(MINIMUM_QUALITY);
        }
    }
}
