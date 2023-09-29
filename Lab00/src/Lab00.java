/* this part is as comment similar to comments in Python
CPE/CSC 203 Lab00
Name: Noah Giboney

Section: CSC-203-15-2238

*/
public class Lab00 {
   public static void main(String[] arguments) {

      // declaring and initializing some variables

      int x = 12;

      String y = "WELCOME to CSC/CPE203";

      double z = 3.1415;

      // printing the variables

      System.out.println("x: " + x + " " + "y: " + y + " " + "Z: " + z);

      // array in java

      int[] nums = {5,16,-8,2,11};

      for(int i : nums){
         System.out.print(i);
         System.out.println();
      }

      // call a function

      int numFound = charCount(y, 'C');
      System.out.println("Number of C in " + y);
      System.out.println("Found: " + numFound);

      // a counting for loop

      for (int i = 1; i <= 15; i++) {
         System.out.print(i + " ");
      }
      System.out.println();
   }

   public static int charCount(String message, char c) {
      int counter = 0;
      char[] array = message.toCharArray();
      for (int i : array){
         if (i == c){
            counter++;
         }
      }
      return counter;
   }
}