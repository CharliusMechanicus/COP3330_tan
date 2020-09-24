/*************************************
  CHARLIE TAN
  COP 3330 SECTION 2
  ASSIGNMENT 2
*************************************/

import java.math.BigDecimal;

public class BodyMassIndex
{

  /***********************************
    CONSTRUCTOR
  ***********************************/

  public BodyMassIndex(double height_dbl, double weight_dbl)
  {
    this.height_in_inches = height_dbl;
    this.weight_in_lbs = weight_dbl;
  } // END BodyMassIndex TWO ARGUMENT CONSTRUCTOR

  /****************************************
    PUBLIC INTERFACE
  ****************************************/

  // RETURNS THE BMI SCORE BASED ON HEIGHT AND WEIGHT
  // THE RETURNED DOUBLE VALUE IS ROUNDED TO 1 DECIMAL PLACE
  public double calculate_bmi_score()
  {

    double bmi_score;
    bmi_score = (703.0 * weight_in_lbs) / (height_in_inches * height_in_inches);

    return round_to_one_decimal_place(bmi_score);

  } // END calculate_bmi_score()

  /**************************************** NEXT FUNCTION ************************************************/

  // RETURNS A STRING DESCRIBING THE BMI CATEGORY BASED ON HEIGHT AND WEIGHT
  public String calculate_bmi_category()
  {

    double bmi_score = this.calculate_bmi_score();

    if( bmi_score < (18.5) )
    {
      return "Underweight";
    } // END if

    /************************************************/

    if( bmi_score >= (18.5) && bmi_score <= (24.9) )
    {
      return "Normal weight";
    } // END if

    /************************************************/

    if( bmi_score >= (25.0) && bmi_score <= (29.9) )
    {
      return "Overweight";
    } // END if

    /************************************************/

    return "Obesity";

  } // END calculate_bmi_category()

  /*******************************************
    HELPER FUNCTIONS
  *******************************************/

  private double round_to_one_decimal_place(double unrounded_dbl)
  {

    BigDecimal big_dec_unrounded = new BigDecimal(unrounded_dbl); // CONVERT DOUBLE TO 'BigDecimal'
    BigDecimal big_dec_rounded = big_dec_unrounded.setScale(1, java.math.RoundingMode.HALF_UP); // ROUND TO 1 DECIMAL PLACE, COMMON HALF UP ROUNDING METHOD

    return big_dec_rounded.doubleValue(); // CONVERT BACK TO DOUBLE AND RETURN

  } // END round_to_one_decimal_place()

  /*****************************************
    PRIVATE DATA
  *****************************************/

  private double height_in_inches;
  private double weight_in_lbs;

} // END BodyMassIndex CLASS