package com.deg540.gildedrose;

public class AgedBrieItemUpdater {

    private static final String AGED_BRIE_NAME = "Aged Brie";
    private static final int STANDARD_DECREASED_DAYS_AGED_BRIE = 1;
    private static final int AGED_BRIE_INCREASED_QUALITY_BEFORE_DAY_PASSED = 1;
    private static final int AGED_BRIE_INCREASED_QUALITY_AFTER_DAY_PASSED = 2;
    private static final int AGED_BRIE_MAX_QUALITY = 50;
    private static final int STANDARD_LAST_DAY_BEFORE_DAY_PASSED = 0;

    public boolean isAgedBrieItem(Item item) {
        return item.getName().contains(AGED_BRIE_NAME);
    }

    public void update(Item item) {
        updateDaysLeftToSellIn(item);
        updateQuality(item);
    }

    private void updateDaysLeftToSellIn(Item item) {
        item.setSellIn(item.getSellIn() - STANDARD_DECREASED_DAYS_AGED_BRIE);
    }

    private void updateQuality(Item item) {
        if (hasMaxQuality(item)) {
            return;
        }

        if (isItemDayPassed(item)) {
            increaseQualityAfterDayPassed(item);
        } else {
            increaseQualityBeforeDayPassed(item);
        }

        setMaxQualityIfExceeded(item);
    }

    private void increaseQualityBeforeDayPassed(Item item) {
        item.setQuality(item.getQuality() + AGED_BRIE_INCREASED_QUALITY_BEFORE_DAY_PASSED);
    }

    private void increaseQualityAfterDayPassed(Item item) {
        item.setQuality(item.getQuality() + AGED_BRIE_INCREASED_QUALITY_AFTER_DAY_PASSED);
    }

    private void setMaxQualityIfExceeded(Item item) {
        if (hasMaxQuality(item)) {
            item.setQuality(AGED_BRIE_MAX_QUALITY);
        }
    }

    private boolean hasMaxQuality(Item item) {
        return item.getQuality() >= AGED_BRIE_MAX_QUALITY;
    }

    private boolean isItemDayPassed(Item item) {
        return item.getSellIn() < STANDARD_LAST_DAY_BEFORE_DAY_PASSED;
    }
}
