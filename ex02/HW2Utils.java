
public class HW2Utils {
	private static double PRECISION = 1.0e-2;
	
	/**
	 * @param height of the triangle
	 * @return the length of the edge of the triangle
	 */
	public static double getLengthEdgeFromHeight(double height) {
		return (height * 2) / (Math.sqrt(3));
	}

	/**
	 * @param lengthEdge of the triangle
	 * @return the height of the triangle
	 */
	public static double getHeightFromLengthEdge(double lengthEdge) {
		return (lengthEdge * Math.sqrt(3)) / 2;
	}

	/**
	 * @param leftPoint of the triangle
	 * @param lengthEdge of the triangle
	 * @param isUp - true, if the third vertex is higher than the other points. Otherwise, return false.
	 * @return the center point of the triangle.
	 */
	public static Point getCenterFromLeftPointLengthEdge(Point leftPoint, double lengthEdge, boolean isUp) {
		Point center = leftPoint.copy();
		double height = getHeightFromLengthEdge(lengthEdge);

		center.moveHorizontal(lengthEdge / 2);
		if (isUp)
			center.moveVertical(height / 3);
		else
			center.moveVertical(-height / 3);

		return center;
	}


	/**
	 * @param leftPoint of the triangle
	 * @param lengthEdge of the triangle
	 * @param isUp - true, if the third vertex is higher than the other points. Otherwise, return false.
	 * @return the left point of the triangle.
	 */
	public static Point getLeftPointFromCenterLengthEdge(Point center, double lengthEdge, boolean isUp) {

		Point leftPoint = center.copy();
		double height = getHeightFromLengthEdge(lengthEdge);

		leftPoint.moveHorizontal(-lengthEdge / 2);
		if (isUp)
			leftPoint.moveVertical(-height / 3);
		else
			leftPoint.moveVertical(height / 3);

		return leftPoint;
	}

	/**
	 * @param d : double
	 * @return String represents d with 2 places after the decimal point.
	 */
	public static String formatDouble(double d) {
		String res = String.format("%.2f", d);
		if (res.equals("-0.00"))
			res = "0.00";
		return res;
	}
	
	public static boolean areEqual(double d1, double d2) {
		return Math.abs(d1 - d2) < PRECISION;
	}
	
	
	public static Point[] getVertices(Point centerPoint, double height)
	{
		Point[] arr;
		arr = new Point[3];
		Point topOrBottom = null, left = null, right = null;
		if (height>0)
		{
			topOrBottom = new Point(centerPoint.getX(),centerPoint.getY()+height/2);
			left = new Point(centerPoint.getX()-height/2,centerPoint.getY()-height/2);
			right = new Point(centerPoint.getX()+height/2,centerPoint.getY()-height/2);
		}
		else if (height<0)
		{
			topOrBottom = new Point(centerPoint.getX(),centerPoint.getY()-height/2);
			left = new Point(centerPoint.getX()-height/2,centerPoint.getY()+height/2);
			right = new Point(centerPoint.getX()+height/2,centerPoint.getY()+height/2);
		}
		arr[0] = topOrBottom;
		arr[1] = left;
		arr[2] = right;
		return arr;
	}
	
	
	private static double y(double x, Point p0, Point p1) 
	{
		double m = (p1.getY() - p0.getY()) / (p1.getX() - p0.getX());
		return m*(x - p1.getX()) + p1.getY();
	}
	
		
		public static boolean contains(Point p, Point []arr, Point centerPoint, double height)
		{
			Point p0,p1,p2;
			double y;
			p0 = arr[0];
			p1 = arr[1];
			p2 = arr[2];
			if (height > 0)
			{
				if (p.getX() == centerPoint.getX())
				{
					if (p0.getY() >= p.getY() && p1.getY() <= p.getY())
						return true;
				}
				if (p.getX() < centerPoint.getX()) 
				{
					y = y(p.getX(), p0, p1);
					if (y >= p.getY() && p1.getY() <= p.getY())
						return true;
				}
				else
				{
					y = y(p.getX(), p0, p2);
					if (y >= p.getY() && p2.getY() <= p.getY())
						return true;
				}
			}
			else
			{
				if (p.getX() == centerPoint.getX())
				{
					if (p0.getY() <= p.getY() && p1.getY() >= p.getY())
						return true;
				}
				if (p.getX()<centerPoint.getX()) 
				{
					y = y(p.getX(), p0, p1);
					if (y <= p.getY() && p1.getY() >= p.getY())
						return true;
				}
				else
				{
					y = y(p.getX(), p0, p2);
					if (y <= p.getY() && p2.getY() >= p.getY())
						return true;
				}
			}
			return false ;
		}
}
