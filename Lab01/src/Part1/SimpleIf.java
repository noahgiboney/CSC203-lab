package Part1;

import Part2.Applicant;

public class SimpleIf {

  /**
   * Takes an applicant's average score and accepts the applicant if the average
   * is higher than 85.
   * 
   * @param avg The applicant's average score
   * @param threshold The threshold score
   * @return true if the applicant's average is over the threshold, and false otherwise
   */
  public static boolean analyzeApplicant(double avg, double threshold) {
    /*
     * TO DO: Write an if statement to determine if the avg is larger than the threshold and
     * return true if so, false otherwise
     */

    return avg > threshold;
  }

  /**
   * Takes two applicants' average scores and returns the score of the applicant
   * with the higher average.
   * 
   * @param avg1 The first applicant's average score
   * @param avg2 The second applicant's average score
   * @return the higher average score
   */
  public static double maxAverage(double avg1, double avg2) {
    /*
     * TO DO: Write an if statement to determine which average is larger and return
     * that value.
     */
    if (avg1 > avg2){
      return  avg1;
    }
    else {
      return avg2;
    }
  }

  /*The explanation for this filter method for my applicant class is to filter if the applicant has
  * a certain years of experience. The Applicant instance is passed in as a parameter to the function
  * along with the threshold for years of experience the company wants to set. The function returns a boolean
  * value (true/false) if the applicant has greater than or equal to the threshold. The applicant years of
  * experience is accessed through the get years of experience getter method since the years of experience
  *  per applicant is a private attribute.*/

  public static boolean analyzeApplicant2(Applicant applicant, int threshold){
    return applicant.getYearsOfExperience() >= threshold;
  }
}
