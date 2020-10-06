/*************************************
  CHARLIE TAN
  COP 3330 SECTION 2
  ASSIGNMENT 3
*************************************/

public class Circle extends Shape2D
{

  /*********************************
    CONSTRUCTORS
  **********************************/

  public Circle(double radius_arg)
  {
    radius = radius_arg;
  } // END Circle CONSTRUCTOR

  /********************************
    PUBLIC INTERFACE
  *********************************/

  @Override
  public String getName()
  {
    return "circle";
  } // END getName()

  /**********************************************/

  @Override
  public double getArea()
  {
    return (Math.PI * radius * radius);
  } // END getArea()

  /*************************************
    PRIVATE DATA
  **************************************/

  private double radius;

} // END Circle CLASS