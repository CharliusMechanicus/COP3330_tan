/*************************************
  CHARLIE TAN
  COP 3330 SECTION 2
  ASSIGNMENT 3
*************************************/

public class Cube extends Shape3D
{

  /*********************************
    CONSTRUCTORS
  **********************************/

  public Cube(double edge_arg)
  {
     edge = edge_arg;
  } // END Cube CONSTRUCTOR

  /********************************
    PUBLIC INTERFACE
  *********************************/

  @Override
  public String getName()
  {
    return "cube";
  } // END getName()

  /************************************************/

  @Override
  public double getArea()
  {
    return (6.0 * edge * edge);
  } // END getArea()

  /************************************************/

  @Override
  public double getVolume()
  {
    return (edge * edge * edge);
  } // END getVolume()

  /**************************************
    PRIVATE DATA
  **************************************/

  private double edge;

} // END Cube CLASS