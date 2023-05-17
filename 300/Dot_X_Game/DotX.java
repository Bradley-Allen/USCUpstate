/**
 * DotX Game
 * 
 * User will start the game by typing "READY". For 10 seconds, targets will approach and the user will be
 * prompted to fire or hold their fire. Firing on certain targets will result in a loss of points.
 * After the game, the user will be shown their targets hit and missed and the total score.
 * 
 * @author Bradley Allen
 */

import java.util.*;

class DotX {
   public static void main(String []args) {
	   /** Initialization	*/
	   Scanner input = new Scanner(System.in);
	   boolean flag = true;
       long starttime = 0;
       int points = 0;
       int counter = 0;
       ArrayList<String> targetList = new ArrayList<String>();
       
       
       /** Prints instructions and prompts user input	*/
       System.out.println("You have 10 seconds, let's go!");
       System.out.println("Use x to shoot, . to hold your fire.");
       System.out.println("Type \"READY\" to start!");
       String ready = input.nextLine();
       
       
       /** Handles user input to start game...	*/
       while (flag == true) {
	       if (ready.toLowerCase().equals("ready")) {
	           flag = false;
	    	   starttime = System.currentTimeMillis();
	    	   System.out.println();
	       } 	       
	       else {
	    	   ready = input.nextLine();
	       }
       }
       

       /** Selects targets at random and prompts user	*/
       while (flag == false) {
    	   double targetrandomizer = (Math.random() * 4);

    	   /** Creates/handles "Ally" target	*/
    	   if (targetrandomizer <= 1){
        	   Ally currenttarget = new Ally();
        	   System.out.println(currenttarget);
        	   System.out.println(currenttarget.receiveShot(input.nextLine()));
        	   if (currenttarget.value() < 0) {
        		   targetList.add("Ally" + "\t\t----- (Hit)\t" + currenttarget.value());
        	   }
        	   else {
        		   targetList.add("Ally" + "\t\t----- (Missed)\t" + currenttarget.value());
        	   }
        	   counter += 1;
        	   points += currenttarget.value();
        	   System.out.println("---------\n");
           }
           
           /** Creates/handles "Enemy" target	*/
           else if (targetrandomizer > 1 && targetrandomizer <=2){
          	   Enemy currenttarget = new Enemy();
         	   System.out.println(currenttarget);
         	   System.out.println(currenttarget.receiveShot(input.nextLine()));
         	   if (currenttarget.value() > 0) {
            	   targetList.add("Enemy" + "\t\t----- (Hit)\t" + currenttarget.value());
         	   }
         	   else {
            	   targetList.add("Enemy" + "\t\t----- (Missed)\t" + currenttarget.value());
         	   }
         	   counter += 1;
         	   points += currenttarget.value();
          	   System.out.println("---------\n");
           }
           
           /** Creates/handles "EnemyLeader" target	*/
           else if (targetrandomizer > 2 && targetrandomizer <=3){
        	   EnemyLeader currenttarget = new EnemyLeader();
        	   System.out.println(currenttarget);
        	   System.out.println(currenttarget.receiveShot(input.nextLine()));
        	   if (currenttarget.value() > 0) {
        		   targetList.add("Enemy Leader" + "\t----- (Hit)\t" + currenttarget.value());
        	   }
        	   else {
        		   targetList.add("Enemy Leader" + "\t----- (Missed)\t" + currenttarget.value());
        	   }
        	   counter += 1;
        	   points += currenttarget.value();
          	   System.out.println("---------\n");
           }
           
           /** Creates/handles "Civilian" target	*/
           else {
        	   Civilian currenttarget = new Civilian();
        	   System.out.println(currenttarget);
          	   System.out.println(currenttarget.receiveShot(input.nextLine()));
          	   if (currenttarget.value() < 0) {
          		   targetList.add("Civilian" + "\t----- (Hit)\t" + currenttarget.value());
          	   }
          	   else {
          		   targetList.add("Civilian" + "\t----- (Missed)\t" + currenttarget.value());
          	   }
          	   counter += 1;
          	   points += currenttarget.value();
          	   System.out.println("---------\n");
           }
           	  
    	   
           /** Calculates how much time has elapsed, if 10 seconds have elapsed, game will end	*/
           long endtime = System.currentTimeMillis();
           if (endtime - starttime > 10000) {
        	   flag = true;
           }
           else {
        	   continue;
           }
       }
       input.close();
       
       
       /** Prints game over with target hit list and points earned	*/
	   System.out.println("\nGame over!\n");
       System.out.println("List of encounters:");
       for(int i=0; i<counter; ++i) {
    	   System.out.println(targetList.get(i));
       }
       System.out.println("\nYou finished with " + points + " points!");
	}
}