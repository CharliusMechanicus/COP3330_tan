/************************************
  CHARLIE TAN
  COP 3330
  ASSIGNMENT 5
*************************************/

import java.util.Scanner;

public class EntryPointApp extends ConsoleBasedMenuApp
{

  /*****************************************************
    MAIN
  ******************************************************/

  public static void main(String[] args)
  {

    EntryPointApp entry_point_instance = new EntryPointApp();
    entry_point_instance.run_program();

  } // END main()

  /*****************************************************
    HELPER FUNCTIONS
  ******************************************************/

  /************************************************************************
    HELPER FUNCTION LISTING
    -----------------------
    void run_program();
    void execute_selection_screen();
    boolean is_menu_choice_valid(int);
  *************************************************************************/

  @Override
  public void run_program()
  {

    this.program_is_still_running = true;

    while(this.program_is_still_running)
    {
      execute_selection_screen();
    } // END while LOOP

  } // END run_program()

  /********************************* NEXT FUNCTION **************************************/

  private void execute_selection_screen()
  {

    /*******************************************
      LOCAL VARIABLES
    ********************************************/

    int menu_choice_number = -1;
    ContactApp contact_app_instance;
    TaskApp task_app_instance;

    /**************************************************************/

    System.out.println("Select Your Application");
    System.out.println("-----------------------");
    System.out.println("");

    System.out.println("1) task list");
    System.out.println("2) contact list");
    System.out.println("3) quit");
    System.out.println("");

    System.out.println("Enter a number for your choice:");
    System.out.printf("> ");
    menu_choice_number = grab_numeric_menu_screen_choice();

    /**************************************************************/
    // AT THIS POINT, WE CAN ASSUME A VALID MENU SELECTION FROM USER

    switch(menu_choice_number)
    {

      // TASK LIST
      case 1:
        System.out.println("");
        task_app_instance = new TaskApp();
        task_app_instance.run_program();
        break;

      // CONTACT LIST
      case 2:
        System.out.println("");
        contact_app_instance = new ContactApp();
        contact_app_instance.run_program();
        break;

      // QUIT
      case 3:
        this.program_is_still_running = false;
        break;

    } // END switch

  } // END execute_selection_screen()

  /********************************* NEXT FUNCTION **************************************/

  @Override
  protected boolean is_menu_choice_valid(int menu_choice_number)
  {

    // IF MENU CHOICE IS BETWEEN 1 AND 3 INCLUSIVE
    if(menu_choice_number >= 1 && menu_choice_number <= 3)
    {
      return true; // VALID
    } // END if

    // OTHERWISE, NOT VALID
    return false;

  } // END is_menu_choice_valid()

} // END EntryPointApp CLASS