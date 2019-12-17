class Battle{
    public static void main(String[] args){
        Hero hero1 = new Wizard(100, 0, 0, 0, 0, "0", 2, 3);
        Hero hero2 = new Knight("REEE");
        Hero hero3 = new Healer(100, 0, 0, 0, 0, "healer", 0, 0);

        Hero hero4 = new Assassin(100, 0, 0, 0, 0, "ass ass in", 2);
        Hero hero5 = new Archer(100, 0, 0, 0, 0, "aRcHEr", 5)
        Hero hero6 = new Knight("name");
        
        Item item = new Item(Item.ItemType.HEALING);


        hero1.attack(hero2, 100);
        System.out.println(hero2.getHealth());
        hero2.useItem(item);
        System.out.println(hero2.getHealth());

    }
}