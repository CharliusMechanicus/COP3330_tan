/************************************
  CHARLIE TAN
  COP 3330
  ASSIGNMENT 5
*************************************/

import java.util.Scanner;

// EXCEPTIONS IMPORT
import java.util.InputMismatchException;

public abstract class ConsoleBasedMenuApp
{

  /***************************************************
    CLASS DATA
  ****************************************************/

  protected boolean program_is_still_running;
  protected Scanner user_input_scanner;

  /***************************************************
   ABSTRACT CLASS FUNCTIONS
  ****************************************************/

  public abstract void run_program();

  // MUST OVERRIDE THIS FOR 'grab_numeric_menu_screen_choice' CLASS FUNCTION TO WORK
  protected abstract boolean is_menu_choice_valid(int menu_choice_number);

  /***************************************************
    CLASS FUNCTIONS
  ****************************************************/

  /******************************************************************
    CLASS FUNCTIONS LISTING
    -----------------------
    void clear_input_buffer();
    int grab_any_numeric_input();
    int grab_numeric_menu_screen_choice();
  *******************************************************************/

  protected void clear_input_buffer()
  {

    this.user_input_scanner = new Scanner(System.in);

  } // END clear_input_buffer()

  /********************************* NEXT FUNCTION **************************************/

  protected int grab_any_numeric_input()
  {

    /*******************************************
      LOCAL VARIABLES
    ********************************************/

    int numeric_input = 0;
    boolean is_numeric_input_received = false;
    int loop_counter = 0;

    /**************************************************************/
    // KEEP TRYING TO GRAB A NUMBER FROM USER
    while(!is_numeric_input_received)
    {

      // DISPLAY THIS AFTER FAILING TO RECEIVE NUMERIC INPUT
      if(loop_counter > 0)
      {
        System.out.println("Enter a number:");
        System.out.printf("> ");
      } // END if

      /**************************************************/
      // TRY TO GET NUMERIC INPUT
      try
      {
        clear_input_buffer();
        numeric_input = this.user_input_scanner.nextInt();
        is_numeric_input_received = true;
      } // END try

      // IN THE EVENT OF NON-NUMERIC INPUT
      catch(InputMismatchException ex)
      {
        System.out.println("Error - input was not a number");
      } // END catch
      /**************************************************/

      ++loop_counter;

    } // END while LOOP
    /**************************************************************/
    // AT THIS POINT, NUMERIC INPUT HAS BEEN RECEIVED

    return numeric_input;

  } // END grab_any_numeric_input()

  /********************************* NEXT FUNCTION **************************************/

  protected int grab_numeric_menu_screen_choice()
  {

    /*******************************************
      LOCAL VARIABLES
    ********************************************/

    int menu_choice_number = -1;
    int loop_counter = 0;

    /**************************************************************/

    // KEEP TRYING TO GET APPROPRIATE MENU CHOICE
    do
    {
      clear_input_buffer();

      if(loop_counter > 0)
      {
        System.out.println("Invalid menu choice - try again:");
        System.out.printf("> ");
      } // END if

      /***************************************************/

      // TRY TO GET A NUMBER
      try
      {
        menu_choice_number = this.user_input_scanner.nextInt();
      } // END try

      // IN THE EVENT OF NON-NUMERIC INPUT
      catch(InputMismatchException ex)
      {
        System.out.println("Error - input was not a number");
      } // END catch

      /***************************************************/
      ++loop_counter;
    }
    while( !is_menu_choice_valid(menu_choice_number) );

    return menu_choice_number;

  } // END grab_numeric_menu_screen_choice()

} // END App CLASS