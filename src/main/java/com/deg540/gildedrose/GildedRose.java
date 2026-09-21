package com.deg540.gildedrose;

import java.util.ArrayList;
import java.util.List;

public class GildedRose {

    private List<Item> items = null;
    private GeneralItemUpdater generalItemUpdater = new GeneralItemUpdater();
    private SulfurasItemUpdater sulfurasItemUpdater = new SulfurasItemUpdater();
    private AgedBrieItemUpdater agedBrieItemUpdater = new AgedBrieItemUpdater();
    private BackstagePassItemUpdater backstagePassItemUpdater = new BackstagePassItemUpdater();
    private ConjuredItemUpdater conjuredItemUpdater = new ConjuredItemUpdater();

    public GildedRose(List<Item> items) {
        this.items = items;

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
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
        if (sulfurasItemUpdater.isSulfurasItem(item)) {
            sulfurasItemUpdater.update(item);
            return;
        }

        if (agedBrieItemUpdater.isAgedBrieItem(item)) {
            agedBrieItemUpdater.update(item);
            return;
        }

        if (backstagePassItemUpdater.isBackstagePassItem(item)) {
            backstagePassItemUpdater.update(item);
            return;
        }

        if (conjuredItemUpdater.isConjuredItem(item)) {
            conjuredItemUpdater.update(item);
            return;
        }

        generalItemUpdater.update(item);
    }
}