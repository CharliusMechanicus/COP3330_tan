/*************************************
  CHARLIE TAN
  COP 3330 SECTION 2
  ASSIGNMENT 2
*************************************/

import java.util.Scanner;
import java.util.ArrayList;

public class App
{

  /***********************************************************
                    HELPER FUNCTIONS
  ***********************************************************/

  // RETURNS 'true' IF 'user_choice_string' IS SOME FORM OF ACCEPTABLE INPUT CHARACTER, RETURNS 'false' OTHERWISE
  private static boolean is_acceptable_input(String user_choice_string)
  {

    // IF THE USER ENTERED A CHARACTER
    if(user_choice_string.length() == 1)
    {
      // IF USER ENTERED YES
      if(user_choice_string.charAt(0) == 'Y' || user_choice_string.charAt(0) == 'y')
        return true;

      // IF USER ENTERED NO
      if(user_choice_string.charAt(0) == 'N' || user_choice_string.charAt(0) == 'n')
        return true;      
    } // END if

    // OTHERWISE, UNACCEPTABLE INPUT
    return false;
  } // END is_acceptable_input()

  /**************************************** NEXT FUNCTION ******************************************/

  // FUNCTION ASSUMES THAT 'user_choice_string' IS A STRING REPRESENTATION OF A CHARACTER
  private static char extract_character(String user_choice_string)
  {
    return user_choice_string.charAt(0);
  } // END extract_character()

  /**************************************** NEXT FUNCTION ******************************************/

  // RETURNS 'true' IF 'user_choice_character' INDICATES THAT THE USER HAS MORE INPUT, RETURNS 'false' OTHERWISE
  // FUNCTION ASSUMES THAT 'user_choice_character' IS SOME FORM OF ACCEPTABLE INPUT
  private static boolean user_has_more_input(char user_choice_character)
  {

    if(user_choice_character == 'Y' || user_choice_character == 'y')
      return true;

    return false;

  } // END user_has_more_input()

  /**************************************** NEXT FUNCTION ******************************************/

  private static boolean moreInput()
  {

    /************************************
      VARIABLES
    ************************************/

    boolean has_more_input_boolean = false;
    boolean acceptable_input_boolean = false;
    String user_choice_string = "";
    char user_choice_character;

    Scanner user_input = new Scanner(System.in);

    /**************************************************/

    // CONTINUE TRYING TO GET ACCEPTABLE INPUT FROM USER
    while(!acceptable_input_boolean)
    {
      System.out.printf("Do you wish to input values for Body Mass Index (BMI) calculations?\n");
      System.out.printf("Enter Y for yes, or N for no: ");

      user_choice_string = user_input.next();

      acceptable_input_boolean = is_acceptable_input(user_choice_string);

      if(!acceptable_input_boolean)
      {
        System.out.printf("Your input was not valid, please try again.\n\n");
      } // END if

    } // END while LOOP

    /**************************************************/

    user_choice_character = extract_character(user_choice_string);
    has_more_input_boolean = user_has_more_input(user_choice_character);

    return has_more_input_boolean;

  } // END moreInput()

  /**************************************** NEXT FUNCTION ******************************************/

  // RETURNS 'true' IF 'user_height_or_weight_dbl' IS A POSITIVE NUMBER, RETURNS 'false' OTHERWISE
  private static boolean is_acceptable_input(double user_height_or_weight_dbl)
  {
    if(user_height_or_weight_dbl > 0)
      return true;

    return false;
  } // END is_acceptable_input()

  /**************************************** NEXT FUNCTION ******************************************/

  private static double getUserHeight()
  {

    /************************************
      VARIABLES
    ************************************/

    double user_height_dbl = 0.0;
    boolean acceptable_input_boolean;
    boolean exception_crisis_avoided_boolean;

    Scanner user_input = new Scanner(System.in);

    /****************************************************************/

    System.out.println("");
    System.out.printf("Please enter the height in inches: ");

    /****************************************************************/

    // CONTINUE ATTEMPTING TO GET NUMERIC VALUE FOR INPUT
    do
    {

      // TRY TO GET NUMERIC INPUT
      try
      {
        user_height_dbl = user_input.nextDouble(); // CATCH POSSIBLE EXCEPTION FROM HERE CAUSED BY NON-NUMERIC INPUT

        acceptable_input_boolean = is_acceptable_input(user_height_dbl);

        // CONTINUE ATTEMPTING TO GET ACCEPTABLE INPUT IN THE FORM OF A POSITIVE NUMBER
        while(!acceptable_input_boolean)
        {

          System.out.printf("\nI'm sorry, that input was unacceptable. Please enter the height in inches (as a positive number): ");
          user_height_dbl = user_input.nextDouble(); // CATCH POSSIBLE EXCEPTION FROM HERE CAUSED BY NON-NUMERIC INPUT
          acceptable_input_boolean = is_acceptable_input(user_height_dbl);

        } // END while LOOP

        exception_crisis_avoided_boolean = true;
      } // END try

      /***********************************************************/

      // EXCEPTION HANDLING IN CASE OF NON-NUMERIC INPUT
      catch(java.util.InputMismatchException ex)
      {
        System.out.printf("\nI'm sorry, that input was unacceptable. Please enter the height in inches (as a positive number): ");
        user_input.nextLine(); // CLEAR INPUT BUFFER
        exception_crisis_avoided_boolean = false;
      } // END catch

    }
    while(!exception_crisis_avoided_boolean); // END do while LOOP

    /****************************************************************/

    return user_height_dbl;

  } // END getUserHeight()

  /**************************************** NEXT FUNCTION ******************************************/

  private static double getUserWeight()
  {

    /************************************
      VARIABLES
    ************************************/

    double user_weight_dbl = 0.0;
    boolean acceptable_input_boolean;
    boolean exception_crisis_avoided_boolean;

    Scanner user_input = new Scanner(System.in);

    /****************************************************************/

    System.out.println("");
    System.out.printf("Please enter the weight in pounds: ");

    /****************************************************************/

    // CONTINUE ATTEMPTING TO GET NUMERIC VALUE FOR INPUT
    do
    {

      // TRY TO GET NUMERIC INPUT
      try
      {
        user_weight_dbl = user_input.nextDouble(); // CATCH POSSIBLE EXCEPTION FROM HERE CAUSED BY NON-NUMERIC INPUT

        acceptable_input_boolean = is_acceptable_input(user_weight_dbl);

        // CONTINUE ATTEMPTING TO GET ACCEPTABLE INPUT IN THE FORM OF A POSITIVE NUMBER
        while(!acceptable_input_boolean)
        {

          System.out.printf("\nI'm sorry, that input was unacceptable. Please enter the weight in pounds (as a positive number): ");
          user_weight_dbl = user_input.nextDouble(); // CATCH POSSIBLE EXCEPTION FROM HERE CAUSED BY NON-NUMERIC INPUT
          acceptable_input_boolean = is_acceptable_input(user_weight_dbl);

        } // END while LOOP

        exception_crisis_avoided_boolean = true;
      } // END try

      /***********************************************************/

      // EXCEPTION HANDLING IN CASE OF NON-NUMERIC INPUT
      catch(java.util.InputMismatchException ex)
      {
        System.out.printf("\nI'm sorry, that input was unacceptable. Please enter the weight in pounds (as a positive number): ");
        user_input.nextLine(); // CLEAR INPUT BUFFER
        exception_crisis_avoided_boolean = false;
      } // END catch

    }
    while(!exception_crisis_avoided_boolean); // END do while LOOP

    /****************************************************************/

    return user_weight_dbl;

  } // END getUserWeight()

  /**************************************** NEXT FUNCTION ******************************************/

  private static void displayBmiInfo(BodyMassIndex bmi)
  {

    System.out.println("");

    System.out.printf("DISPLAYING BMI INFO\n"); // DISPLAY HEADER
    System.out.printf("BMI Score: %.1f\n", bmi.calculate_bmi_score()); // DISPLAY BMI SCORE
    System.out.printf("BMI Category: %s\n", bmi.calculate_bmi_category()); // DISPLAY BMI CATEGORY

    System.out.println("");

  } // END displayBmiInfo()

  /**************************************** NEXT FUNCTION ******************************************/

  private static void displayBmiStatistics(ArrayList<BodyMassIndex> bmiData)
  {

    /****************************************
      VARIABLES
    ****************************************/

    int number_of_bmi_entries = bmiData.size();
    double bmi_score_summation = 0.0;
    double average_bmi_score = 0.0;

    /****************************************************/

    // IF NO BMI INFORMATION WAS INPUT
    if(bmiData.isEmpty())
    {
      System.out.println("");
      System.out.printf("No information was collected. Average BMI unable to be calculated.\n");
      System.out.printf("Thank you, have a nice day!\n");
      return;
    } // END if

    /****************************************************/

    // ADD UP ALL BMI SCORES
    for(BodyMassIndex bmi : bmiData)
    {
     bmi_score_summation += bmi.calculate_bmi_score();
    } // END ENHANCED for LOOP

    average_bmi_score = (bmi_score_summation / number_of_bmi_entries);

    System.out.println("");
    System.out.printf("DISPLAYING BMI STATISTICS\n"); // DISPLAY HEADER
    System.out.printf("Average BMI Score: %.1f\n", average_bmi_score); // DISPLAY BMI AVERAGE
    System.out.printf("Thank you, have a nice day!\n");

  } // END displayBmiStatistics()

  /***********************************************************
                            MAIN
  ***********************************************************/

  public static void main(String[] args) {
    ArrayList<BodyMassIndex> bmiData = new ArrayList<BodyMassIndex>();

    while(moreInput()) {
      double height = getUserHeight();
      double weight = getUserWeight();

      BodyMassIndex bmi = new BodyMassIndex(height, weight);
      bmiData.add(bmi);

      displayBmiInfo(bmi);
    }

    displayBmiStatistics(bmiData);

  }

  /************************************************************/

} // END App CLASS