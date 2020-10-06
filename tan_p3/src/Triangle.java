/*************************************
  CHARLIE TAN
  COP 3330 SECTION 2
  ASSIGNMENT 3
*************************************/

public class Triangle extends Shape2D
{

  /*********************************
    CONSTRUCTORS
  **********************************/

  public Triangle(double base_arg, double height_arg)
  {

    base = base_arg;
    height = height_arg;

  } // END Triangle CONSTRUCTOR

  /********************************
    PUBLIC INTERFACE
  *********************************/

  @Override
  public String getName()
  {
    return "triangle";
  } // END getName()

  /**********************************************/

  @Override
  public double getArea()
  {
    return (0.5 * base * height);
  } // END getArea()

  /**************************************
    PRIVATE DATA
  **************************************/

  private double base;
  private double height;

} // END Triangle CLASS