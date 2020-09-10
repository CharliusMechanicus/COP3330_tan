/*************************************
  CHARLIE TAN
  COP 3330 SECTION 2
  ASSIGNMENT 1
*************************************/

public class Encrypter
{

  /********************************
    PUBLIC INTERFACE
  ********************************/

  public String encrypt(String number_string)
  {

    int[] number_sequence;

    number_sequence = to_integer_converter(number_string);
    encryption_converter(number_sequence);

    return (to_string_converter(number_sequence));

  } // END encrypt()

  /*********************************
    HELPER FUNCTIONS
  *********************************/

  // TAKES A STRING REPRESENTATION OF A NUMBER AND RETURNS AN INTEGER ARRAY OF THAT NUMBER
  private int[] to_integer_converter(String string2convert)
  {

    int[] integer_array = new int[string2convert.length()];
    char[] char_array = new char[string2convert.length()];

    /***********************************/

    // FILL CHARACTER ARRAY WITH string2convert
    for(int i = 0; i < string2convert.length(); ++i)
    {
      char_array[i] = string2convert.charAt(i);
    } // END for loop

    /***********************************/

    // TRANSLATE CHARACTER ARRAY TO INTEGER ARRAY
    for(int i = 0; i < string2convert.length(); ++i)
    {
      integer_array[i] = Character.digit(char_array[i], 10); // CONVERT CHARACTER TO A NUMBER USING BASE '10'
    } // END for loop

    return integer_array;

  } // END to_integer_converter()

  /************************************************** NEXT FUNCTION *****************************************************************************/

  // TAKES A NUMBER REPRESENTED BY AN ARRAY OF INTEGERS, AND ALTERS THE NUMBER ACCORDING TO THE ENCRYPTION ALGORITHM GIVEN BY PROFESSOR HOLLANDER
  private void encryption_converter(int[] integer_array)
  {

    // ADD 7 TO EACH DIGIT OF THE NUMBER (IF 1234 IS THE NUMBER, THEN 1\2\3\4 BECOMES 8\9\10\11)
    for(int i = 0; i < integer_array.length; ++i)
    {
      integer_array[i] += 7;
    } // END for loop

    /***********************************/

    // REPLACE EACH INTEGER IN THE INTEGER ARRAY WITH ITS REMAINDER AFTER DIVISION BY 10
    for(int i = 0; i < integer_array.length; ++i)
    {
      integer_array[i] %= 10;
    } // END for loop

    /***********************************/

    int temp;

    // SWAP THE FIRST AND THIRD ENTRIES IN THE INTEGER ARRAY
    temp = integer_array[0];
    integer_array[0] = integer_array[2];
    integer_array[2] = temp;

    // SWAP THE SECOND AND FOURTH ENTRIES IN THE INTEGER ARRAY
    temp = integer_array[1];
    integer_array[1] = integer_array[3];
    integer_array[3] = temp;

  } // END encryption_converter()

  /************************************************** NEXT FUNCTION *****************************************************************************/

  // TAKES A SEQUENCE OF INTEGERS REPRESENTING A NUMBER AND RETURNS A STRING REPRESENTING THAT NUMBER
  private String to_string_converter(int[] integer_array)
  {

    // RETURN EMPTY STRING IN THE EVENT AN EMPTY ARRAY IS PASSED
    if(integer_array.length == 0)
      return "";

    /******************************/

    String number_string = "";

    // MAKE A STRING REPRESENTATION OF THE NUMBER REPRESENTED BY THE INTEGER ARRAY
    for(int number : integer_array)
    {
      number_string += String.valueOf(number);
    } // END for loop

    return number_string;

  } // END to_string_converter()

} // END Encrypter class