/**
 * Civilian target for DotX Game.
 */

class Civilian extends Target {
   public String receiveShot(String projectile){
      if (projectile.equals(".")){
         wasMissed = true;
         return "Well, they make nice target practice if they keep running at us!";
      }
      else if (projectile.equals("x")){
         wasHit = true;
         return "That's a war crime, soldier!";
      }
      else {
    	 wasMissed = true;
    	 return "I hope that was a warning shot, soldier!";
      }
   }
   
   public int value(){
      if (wasHit){
         return -200;
      }
      else {
         return 0;
      }
   }
   
   public String toString(){
      return "A startled civilian approaches... :";
   }
   
}