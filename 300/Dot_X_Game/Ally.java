/**
 * Ally target for DotX Game.
 */

class Ally extends Target {
   public String receiveShot(String projectile){
      if (projectile.equals(".")){
         wasMissed = true;
         return "The ally thanks you, cheers.";
      }
      else if (projectile.equals("x")){
         wasHit = true;
         return "That was an ally, you don't shoot allies!";
      }
      else {
    	 wasMissed = true;
    	 return "Hey! Watch your fire, they will NOT miss!";
      }
   }

   public int value(){
      if (wasHit){
         return -100;
      }
      else {
         return 0;
      }
   }
   
   public String toString(){
      return "An ally approaches... :";
   }
}