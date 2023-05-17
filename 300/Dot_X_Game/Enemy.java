/**
 * Enemy target for DotX Game.
 */

class Enemy extends Target {
   public String receiveShot(String projectile){
      if (projectile.equals(".")){
         wasMissed = true;
         return "The enemy ran away! Why didn't you shoot?";
      }
      else if (projectile.equals("x")){
         wasHit = true;
         return "Nice shot, enemy down!";
      }
      else {
    	 wasMissed = true;
    	 return "You missed! You need to go back to target practice!";
      }
   }
   
   public int value(){
      if (wasHit){
         return 50;
      }
      else {
         return 0;
      }
   }
   
   public String toString(){
	   return "An enemy approaches... :";
   }
}