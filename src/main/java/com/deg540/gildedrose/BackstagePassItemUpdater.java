package com.deg540.gildedrose;

public class BackstagePassItemUpdater {

    private static final String NAME = "Backstage passes";
    private static final int DECREASED_DAYS = 1;
    private static final int INCREASED_QUALITY_IN_FIRST_PERIOD = 1;
    private static final int INCREASED_QUALITY_IN_SECOND_PERIOD = 2;
    private static final int INCREASED_QUALITY_IN_THIRD_PERIOD = 3;
    private static final int MAX_QUALITY = 50;
    private static final int FIRST_DAY_TO_CONSIDER = -1;
    private static final int DAYS_LEFT_IN_THIRD_PERIOD = 6;
    private static final int DAYS_LEFT_IN_SECOND_PERIOD = 11;
    private static final int LAST_DAY_BEFORE_DAY_PASSED = 0;

    public boolean isBackstagePassItem(Item item) {
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
        if (isExpired(item)) {
            return;
        }

        if(isItemDayPassed(item)){
            expire(item);
            return;
        }

        if (hasMaxQuality(item)) {
            return;
        }

        if (isInThirdPeriod(item)) {
            increaseQuality(item, INCREASED_QUALITY_IN_THIRD_PERIOD);
            return;
        }

        if (isInSecondPeriod(item)) {
            increaseQuality(item, INCREASED_QUALITY_IN_SECOND_PERIOD);
            return;
        }

        increaseQuality(item, INCREASED_QUALITY_IN_FIRST_PERIOD);
    }

    private boolean isExpired(Item item) {
        return item.getSellIn() < FIRST_DAY_TO_CONSIDER;
    }

    private boolean isItemDayPassed(Item item) {
        return item.getSellIn() < LAST_DAY_BEFORE_DAY_PASSED;
    }

    private void expire(Item item) {
        item.setQuality(0);
    }

    private boolean hasMaxQuality(Item item) {
        return item.getQuality() >= MAX_QUALITY;
    }

    private boolean isInThirdPeriod(Item item) {
        return item.getSellIn() < DAYS_LEFT_IN_THIRD_PERIOD;
    }

    private void increaseQuality(Item item, int increasedQuality){
        item.setQuality(item.getQuality() + increasedQuality);
        setMaxQualityIfExceeded(item);
    }

    private boolean isInSecondPeriod(Item item) {
        return item.getSellIn() < DAYS_LEFT_IN_SECOND_PERIOD;
    }

    private void setMaxQualityIfExceeded(Item item) {
        if (hasMaxQuality(item)) {
            item.setQuality(MAX_QUALITY);
        }
    }
}
