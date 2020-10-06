/*************************************
  CHARLIE TAN
  COP 3330 SECTION 2
  ASSIGNMENT 3
*************************************/

public class Sphere extends Shape3D
{

  /*********************************
    CONSTRUCTORS
  **********************************/

  public Sphere(double radius_arg)
  {
    radius = radius_arg;
  } // END Sphere CONSTRUCTOR

  /********************************
    PUBLIC INTERFACE
  *********************************/

  @Override
  public String getName()
  {
    return "sphere";
  } // END getName()

  /*****************************************************/

  @Override
  public double getArea()
  {
    return ( 4.0 * Math.PI * radius * radius );
  } // END getArea()

  /*****************************************************/

  @Override
  public double getVolume()
  {
    return ( (4.0 / 3.0) * Math.PI * radius * radius * radius );
  } // END getVolume()

  /**************************************
    PRIVATE DATA
  **************************************/

  private double radius;

} // END Sphere CLASS