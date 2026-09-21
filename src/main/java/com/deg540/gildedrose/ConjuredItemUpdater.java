package com.deg540.gildedrose;

public class ConjuredItemUpdater {

    private static final String CONJURED_NAME = "Conjured";
    private static final int STANDARD_DECREASED_DAYS_CONJURED_ITEM = 1;
    private static final int MINIMUM_QUALITY_POSSIBLE_CONJURED_ITEM = 0;
    private static final int CONJURED_ITEM_DECREASED_QUALITY_BEFORE_DAY_PASSED = 2;
    private static final int CONJURED_ITEM_DECREASED_QUALITY_AFTER_DAY_PASSED = 4;
    private static final int STANDARD_LAST_DAY_BEFORE_DAY_PASSED = 0;

    public boolean isConjuredItem(Item item) {
        return item.getName().contains(CONJURED_NAME);
    }

    public void update(Item item) {
        updateDaysLeftToSellIn(item);
        updateQuality(item);
    }

    private void updateDaysLeftToSellIn(Item item) {
        item.setSellIn(item.getSellIn() - STANDARD_DECREASED_DAYS_CONJURED_ITEM);
    }

    private void updateQuality(Item item) {
        if (isQualityBelowPossible(item)) {
            return;
        }

        if (isItemDayPassed(item)) {
            decreaseQualityAfterDayPassed(item);
        } else {
            decreaseQualityBeforeDayPassed(item);
        }

        setMinimumQualityIfIsBelowPossible(item);
    }

    private void decreaseQualityBeforeDayPassed(Item item) {
        item.setQuality(item.getQuality() - CONJURED_ITEM_DECREASED_QUALITY_BEFORE_DAY_PASSED);
    }

    private void decreaseQualityAfterDayPassed(Item item) {
        item.setQuality(item.getQuality() - CONJURED_ITEM_DECREASED_QUALITY_AFTER_DAY_PASSED);
    }

    private void setMinimumQualityIfIsBelowPossible(Item item) {
        if (isQualityBelowPossible(item)) {
            item.setQuality(MINIMUM_QUALITY_POSSIBLE_CONJURED_ITEM);
        }
    }

    private boolean isQualityBelowPossible(Item item) {
        return item.getQuality() <= MINIMUM_QUALITY_POSSIBLE_CONJURED_ITEM;
    }

    private boolean isItemDayPassed(Item item) {
        return item.getSellIn() < STANDARD_LAST_DAY_BEFORE_DAY_PASSED;
    }
}
