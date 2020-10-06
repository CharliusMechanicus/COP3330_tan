/*************************************
  CHARLIE TAN
  COP 3330 SECTION 2
  ASSIGNMENT 3
*************************************/

public class Pyramid extends Shape3D
{

  /*********************************
    CONSTRUCTORS
  **********************************/

  public Pyramid(double length_arg, double width_arg, double altitude_arg)
  {

    base_length = length_arg;
    base_width = width_arg;
    altitude = altitude_arg;

  } // END Pyramid CLASS

  /********************************
    PUBLIC INTERFACE
  *********************************/

  @Override
  public String getName()
  {
    return "pyramid";
  } // END getName()

  /************************************************/

  @Override
  public double getArea()
  {

    double base_area;
    double lateral_area_on_length_sides;
    double lateral_area_on_width_sides;

    base_area = (base_length * base_width);
    lateral_area_on_length_sides = ( base_length * Math.sqrt(Math.pow( (base_width / 2.0), 2.0 ) + Math.pow(altitude, 2.0)) );
    lateral_area_on_width_sides = ( base_width * Math.sqrt(Math.pow( (base_length / 2.0), 2.0 ) + Math.pow(altitude, 2.0)) );

    return (base_area + lateral_area_on_length_sides + lateral_area_on_width_sides);

  } // END getArea()

  /************************************************/

  @Override
  public double getVolume()
  {

    return ( (base_length * base_width * altitude) / 3.0 );

  } // END getVolume()

  /**************************************
    PRIVATE DATA
  **************************************/

  private double base_length;
  private double base_width;
  private double altitude;

} // END Pyramid CLASS