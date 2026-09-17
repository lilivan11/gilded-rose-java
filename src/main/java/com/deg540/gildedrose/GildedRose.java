package com.deg540.gildedrose;

import java.util.ArrayList;
import java.util.List;


public class GildedRose {

    private List<Item> items = null;

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

            if (isSulfurasItem(item)) {
                return;
            }

            if (isGeneralItem(item)) {
                decreaseGeneralItemQuality(item);
            } else if (isAgedBrieItem(item)) {
                increaseAgedBrieQuality(item);
            } else {
                increaseBackstagePassesQuality(item);
            }

            item.setSellIn(item.getSellIn() - 1);

            if (item.getSellIn() < 0) {
                if (isGeneralItem(item)) {
                    decreaseGeneralItemQuality(item);
                } else if (isAgedBrieItem(item)) {
                    increaseAgedBrieQuality(item);
                } else {
                    expireBackstagePassesQuality(item);
                }
            }
        }
    }

    private boolean isSulfurasItem(Item item) {
        return item.getName().contains("Sulfuras");
    }

    private boolean isGeneralItem(Item item) {
        return !isAgedBrieItem(item) && !isBackstagePassesItem(item);
    }

    private void decreaseGeneralItemQuality(Item item) {
        decreaseQuality(item);
    }

    private void decreaseQuality(Item item) {
        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }
    }

    private boolean isAgedBrieItem(Item item) {
        return item.getName().contains("Aged Brie");
    }

    private void increaseAgedBrieQuality(Item item) {
        increaseQuality(item);
    }

    private boolean isBackstagePassesItem(Item item) {
        return item.getName().contains("Backstage passes");
    }

    private void increaseBackstagePassesQuality(Item item) {
        increaseQuality(item);

        if (item.getSellIn() < 11) {
            increaseQuality(item);
        }

        if (item.getSellIn() < 6) {
            increaseQuality(item);
        }
    }

    private void expireBackstagePassesQuality(Item item) {
        item.setQuality(0);
    }

    private void increaseQuality(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }
}