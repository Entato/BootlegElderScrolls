public class Item{
    enum ItemType{
        HEALING,
        MANA,
        ATTACK,
        DEFENCE;
    }
    private ItemType itemType;
   
   public Item(ItemType itemType){
       switch(itemType){
           case HEALING:
               break;
       }
   }
   public void useItem(){
      
   }
}