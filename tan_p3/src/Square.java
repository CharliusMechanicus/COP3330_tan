/*************************************
  CHARLIE TAN
  COP 3330 SECTION 2
  ASSIGNMENT 3
*************************************/

public class Square extends Shape2D
{

  /*********************************
    CONSTRUCTORS
  **********************************/

  public Square(double side_arg)
  {
    side = side_arg;
  } // END Square CONSTRUCTOR

  /********************************
    PUBLIC INTERFACE
  *********************************/

  @Override
  public String getName()
  {
    return "square";
  } // END getName()

  /*********************************************/

  @Override
  public double getArea()
  {
    return (side * side);
  } // END getArea()

  /*************************************
    PRIVATE DATA
  **************************************/

  private double side;

} // END Square CLASS