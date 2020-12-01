/************************************
  CHARLIE TAN
  COP 3330
  ASSIGNMENT 5
*************************************/

public class ContactItem
{

  /******************************************
    CONSTRUCTORS
  *******************************************/

  public ContactItem()
  throws IllegalArgumentException
  {
    throw new IllegalArgumentException("Error - Contact fields can not be all blank");
  } // END ContactItem() CONSTRUCTOR

  /*************************************************************/

  public ContactItem(String first_name_arg, String last_name_arg, String phone_num_arg,
                     String email_address_arg)
  throws IllegalArgumentException
  {

    // IF ALL ARGUMENTS PASSED TO CONSTRUCTOR ARE EMPTY OR BLANK
    if(first_name_arg.isBlank() && last_name_arg.isBlank() && phone_num_arg.isBlank()
       && email_address_arg.isBlank())
    {
      throw new IllegalArgumentException("Error - Contact fields can not be all blank");
    } // END if

    /****************************************************/
    // AT THIS POINT, WE CAN ASSUME THAT THERE IS AT LEAST 1 VALID (NON-BLANK) ARGUMENT

    this.first_name = first_name_arg.strip();
    this.last_name = last_name_arg.strip();
    this.phone_number = phone_num_arg.strip();
    this.email_address = email_address_arg.strip();

  } // END ContactItem(String, String, String, String) CONSTRUCTOR

  /******************************************
    PUBLIC INTERFACE
  *******************************************/

  /*************************************************************
    PUBLIC INTERFACE FUNCTION LISTING
    ---------------------------------
    void edit_contact_information(String, String, String, String);
    String get_first_name();
    String get_last_name();
    String get_phone_number();
    String get_email_address();
    String toString();
  ***************************************************************/

  public void edit_contact_information(String first_name_arg, String last_name_arg,
                                       String phone_num_arg, String email_address_arg)
  throws IllegalArgumentException
  {

    // IF ALL ARGUMENTS PASSED TO FUNCTION ARE EMPTY OR BLANK
    if(first_name_arg.isBlank() && last_name_arg.isBlank() && phone_num_arg.isBlank()
       && email_address_arg.isBlank())
    {
      throw new IllegalArgumentException("Error - Contact fields can not be all blank");
    } // END if

    /****************************************************/
    // AT THIS POINT, WE CAN ASSUME THAT THERE IS AT LEAST 1 VALID (NON-BLANK) ARGUMENT

    this.first_name = first_name_arg.strip();
    this.last_name = last_name_arg.strip();
    this.phone_number = phone_num_arg.strip();
    this.email_address = email_address_arg.strip();

  } // END edit_contact_information()

  /************************************ NEXT FUNCTION ***********************************/

  public String get_first_name()
  {
    return this.first_name;
  } // END get_first_name()

  /************************************ NEXT FUNCTION ***********************************/

  public String get_last_name()
  {
    return this.last_name;
  } // END String get_last_name()

  /************************************ NEXT FUNCTION ***********************************/

  public String get_phone_number()
  {
    return this.phone_number;
  } // END get_phone_number()

  /************************************ NEXT FUNCTION ***********************************/

  public String get_email_address()
  {
    return this.email_address;
  } // END get_email_address()

  /************************************ NEXT FUNCTION ***********************************/

  @Override
  public String toString()
  {

    // IF THERE IS NO FIRST NAME
    if(this.first_name.isBlank())
    {
      return String.format("Name: %s%nPhone: %s%nEmail: %s", this.last_name,
                            this.phone_number, this.email_address);
    } // END if

    // OTHERWISE, THERE IS A FIRST NAME
    return String.format("Name: %s %s%nPhone: %s%nEmail: %s", this.first_name, this.last_name,
                          this.phone_number, this.email_address);

  } // END toString()

  /******************************************
    PRIVATE DATA
  *******************************************/

  private String first_name;
  private String last_name;
  private String phone_number;
  private String email_address;

} // END ContactItem CLASS