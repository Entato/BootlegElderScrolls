package BootlegElderScrolls;

public class Assassin extends Hero{
    //fields
    private int lethality;
    
    //constructor
    public Assassin(String name){
        super(100, 100, 50, 10, 50, name);
        this.lethality = 50;
    }

    //useItem
    public void useItem(Item item){
        super.useItem(item);

        if (item.getItemType() == Item.ItemType.ATTACK) {
            this.lethality += 50;
        }
    }
   
    public void specialAttack(Hero hero){
        int damage = 50 + (150 * hero.getHealth() / hero.getMaxHealth());
        hero.setHealth(hero.getHealth() - damage);
    }
}
