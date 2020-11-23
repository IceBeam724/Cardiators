public class Card
{
   private String cardType;
   private int currentHP;
   
   public Card()
   {
      cardType = "red";
      currentHP = 200;
   }
   
   public Card(String type, int hp)
   {
      cardType = type;
      currentHP = hp;
   }
   
   public String getType()
   {
      return this.cardType;
   }
   
   public int getCurrentHP()
   {
      return this.currentHP;
   }
   
   public void setCurrentHP(int damageTaken)
   {
      this.currentHP = this.currentHP - damageTaken;
   }
   
   
}