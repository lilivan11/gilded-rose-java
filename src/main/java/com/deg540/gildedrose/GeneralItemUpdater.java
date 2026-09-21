package com.deg540.gildedrose;

public class GeneralItemUpdater {

    private static final int STANDARD_DECREASED_DAYS_GENERAL_ITEM = 1;
    private static final int MINIMUM_QUALITY_POSSIBLE_GENERAL_ITEM = 0;
    private static final int GENERAL_ITEM_DECREASED_QUALITY_AFTER_DAY_PASSED = 2;
    private static final int STANDARD_LAST_DAY_BEFORE_DAY_PASSED = 0;

    public void update(Item item) {
        updateDaysLeftToSellIn(item);
        updateQuality(item);
    }

    private static void updateDaysLeftToSellIn(Item item) {
        item.setSellIn(item.getSellIn() - STANDARD_DECREASED_DAYS_GENERAL_ITEM);
    }

    private static void updateQuality(Item item) {
        if (isQualityBelowPossible(item)) {
            return;
        }

        if (isItemDayPassed(item)) {
            updateQualityAfterDayPassed(item);
        } else {
            updateQualityBeforeDayPassed(item);
        }

        setMinimumQualityIfIsBelowPossible(item);
    }

    private static void updateQualityBeforeDayPassed(Item item) {
        item.setQuality(item.getQuality() - 1);
    }

    private static void updateQualityAfterDayPassed(Item item) {
        item.setQuality(item.getQuality() - GENERAL_ITEM_DECREASED_QUALITY_AFTER_DAY_PASSED);
    }

    private static void setMinimumQualityIfIsBelowPossible(Item item) {
        if (isQualityBelowPossible(item)) {
            item.setQuality(MINIMUM_QUALITY_POSSIBLE_GENERAL_ITEM);
        }
    }

    private static boolean isQualityBelowPossible(Item item) {
        return item.getQuality() <= MINIMUM_QUALITY_POSSIBLE_GENERAL_ITEM;
    }

    private static boolean isItemDayPassed(Item item) {
        return item.getSellIn() < STANDARD_LAST_DAY_BEFORE_DAY_PASSED;
    }
}
