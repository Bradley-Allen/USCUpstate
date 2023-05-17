/**
 * Enemy Leader target for DotX Game.
 */

class EnemyLeader extends Target {
   public String receiveShot(String projectile){
      if (projectile.equals(".")){
         wasMissed = true;
         return "That was one of their leaders! Shoot next time!";
      }
      else if (projectile.equals("x")){
         wasHit = true;
         return "Nice, got one of their leaders!";
      }
      else {
    	 wasMissed = true;
    	 return "What was that?? Aim before shooting next time!";
      }
   }
   
   public int value(){
      if (wasHit){
         return 150;
      }
      else {
         return 0;
      }
   }
   
   public String toString(){
      return "An enemy leader approaches... :";
   }
}