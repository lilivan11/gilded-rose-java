package com.deg540.gildedrose;

public class BackstagePassItemUpdater {

    private static final String BACKSTAGE_PASSES_NAME = "Backstage passes";
    private static final int STANDARD_DECREASED_DAYS_BACKSTAGE_PASSES = 1;
    private static final int BACKSTAGE_PASSES_INCREASED_QUALITY_IN_FIRST_PERIOD = 1;
    private static final int BACKSTAGE_PASSES_INCREASED_QUALITY_IN_SECOND_PERIOD = 2;
    private static final int BACKSTAGE_PASSES_INCREASED_QUALITY_IN_THIRD_PERIOD = 3;
    private static final int BACKSTAGE_PASSES_MAX_QUALITY = 50;
    private static final int FIRST_DAY_TO_CONSIDER_BACKSTAGE_PASS = -1;
    private static final int DAYS_LEFT_IN_THIRD_PERIOD_BACKSTAGE_PASSES = 6;
    private static final int DAYS_LEFT_IN_SECOND_PERIOD_BACKSTAGE_PASSES = 11;

    public boolean isBackstagePassItem(Item item) {
        return item.getName().contains(BACKSTAGE_PASSES_NAME);
    }

    public void update(Item item) {
        updateDaysLeftToSellIn(item);
        updateQuality(item);
    }

    private void updateDaysLeftToSellIn(Item item) {
        item.setSellIn(item.getSellIn() - STANDARD_DECREASED_DAYS_BACKSTAGE_PASSES);
    }

    private void updateQuality(Item item) {
        if (isExpired(item)) {
            expire(item);
            return;
        }

        if (hasMaxQuality(item)) {
            setMaxQualityIfExceeded(item);
            return;
        }

        if (isInThirdPeriod(item)) {
            increaseQualityForThirdPeriod(item);
            setMaxQualityIfExceeded(item);
            return;
        }

        if (isInSecondPeriod(item)) {
            increaseQualityForSecondPeriod(item);
            setMaxQualityIfExceeded(item);
            return;
        }

        increaseQualityForFirstPeriod(item);
        setMaxQualityIfExceeded(item);
    }

    private void increaseQualityForFirstPeriod(Item item) {
        item.setQuality(item.getQuality() + BACKSTAGE_PASSES_INCREASED_QUALITY_IN_FIRST_PERIOD);
    }

    private void increaseQualityForSecondPeriod(Item item) {
        item.setQuality(item.getQuality() + BACKSTAGE_PASSES_INCREASED_QUALITY_IN_SECOND_PERIOD);
    }

    private void increaseQualityForThirdPeriod(Item item) {
        item.setQuality(item.getQuality() + BACKSTAGE_PASSES_INCREASED_QUALITY_IN_THIRD_PERIOD);
    }

    private void setMaxQualityIfExceeded(Item item) {
        if (hasMaxQuality(item)) {
            item.setQuality(BACKSTAGE_PASSES_MAX_QUALITY);
        }
    }

    private boolean hasMaxQuality(Item item) {
        return item.getQuality() >= BACKSTAGE_PASSES_MAX_QUALITY;
    }

    private boolean isExpired(Item item) {
        return item.getSellIn() <= FIRST_DAY_TO_CONSIDER_BACKSTAGE_PASS;
    }

    private boolean isInThirdPeriod(Item item) {
        return item.getSellIn() < DAYS_LEFT_IN_THIRD_PERIOD_BACKSTAGE_PASSES;
    }

    private boolean isInSecondPeriod(Item item) {
        return item.getSellIn() < DAYS_LEFT_IN_SECOND_PERIOD_BACKSTAGE_PASSES;
    }

    private void expire(Item item) {
        item.setQuality(0);
    }
}
