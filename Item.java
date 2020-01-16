package BootlegElderScrolls;

public class Item{
    enum ItemType{
        HEALING,
        ATTACK,
        DEFENCE;
    }

    private ItemType itemType;

    public Item(ItemType itemType){
       this.itemType = itemType;
    }
    public ItemType getItemType() {
        return itemType;
    }




}
