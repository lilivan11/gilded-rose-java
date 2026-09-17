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

            if ((!isAgedBrieItem(item)) && !"Backstage passes to a TAFKAL80ETC concert".equals(item.getName())) {
                if (item.getQuality() > 0) {
                    item.setQuality(item.getQuality() - 1);
                }
            } else {
                if (isAgedBrieItem(item)) {
                    increaseAgedBrieQuality(item);
                } else {
                    if (item.getQuality() < 50) {
                        item.setQuality(item.getQuality() + 1);

                        if ("Backstage passes to a TAFKAL80ETC concert".equals(item.getName())) {
                            if (item.getSellIn() < 11) {
                                if (item.getQuality() < 50) {
                                    item.setQuality(item.getQuality() + 1);
                                }
                            }

                            if (item.getSellIn() < 6) {
                                if (item.getQuality() < 50) {
                                    item.setQuality(item.getQuality() + 1);
                                }
                            }
                        }
                    }
                }
            }

            item.setSellIn(item.getSellIn() - 1);

            if (item.getSellIn() < 0) {
                if (!isAgedBrieItem(item)) {
                    if (!"Backstage passes to a TAFKAL80ETC concert".equals(item.getName())) {
                        if (item.getQuality() > 0) {
                            item.setQuality(item.getQuality() - 1);
                        }
                    } else {
                        item.setQuality(item.getQuality() - item.getQuality());
                    }
                } else {
                    increaseAgedBrieQuality(item);
                }
            }
        }
    }

    private boolean isSulfurasItem(Item item) {
        return item.getName().contains("Sulfuras");
    }

    private boolean isAgedBrieItem(Item item) {
        return item.getName().contains("Aged Brie");
    }

    private void increaseAgedBrieQuality(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }
}