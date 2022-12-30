import java.lang.Math;


public class Triangle1 implements Triangle
{
	private Point centerPoint;
	private double height;
	
	public Triangle1()
	{
		centerPoint = new Point();
		height = Math.sqrt(3)/2;
	}
	
	public Triangle1(Point centerPoint, double hight)
	{
		this.centerPoint = centerPoint;
		if (hight != 0)
			this.height = hight;
		else
		{
			System.out.println("height given was incorrect and set to defaulte value");
			Point p = new Point();
			double h = 1 ;
		}
	}
	
	
	public Point[] getVertices()
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
	
	
	public Point getCenter()
	{
		return centerPoint.copy();
	}
	
	
	public double height()
	{
		return height;
	}
	
	
	public double getLengthEdge()
	{
		Point[] arr;
		arr = getVertices();
		return arr[2].getX()-arr[1].getX();
	}
	
	
	public boolean isUpTriangle()
	{
		if (height>0)
			return true;
		else
			return false;
	}
	
	
	public void setCenter(Point p)
	{
		if (p != null)
			centerPoint=p.copy();
	}
	
	
	public void updateHeight(double height)
	{
		if (height>0)
			
			if (this.height>0)
				this.height = height;
			else 
				this.height = -height;
	}
	
	
	public void inverse()
	{
		height *= (-1);
	}
	
	
	public void updateLengthEdge(double lengthEdge)
	{
		if (lengthEdge>0)
			height = Math.sqrt((lengthEdge*lengthEdge)-(lengthEdge/2)*(lengthEdge/2));
	}
	
	
	public void scale(double scalePar)
	{
		double edge = 0;
		if (scalePar>0)
			edge = getLengthEdge();
			edge *= scalePar;
			updateLengthEdge(edge);			
	}
	
	
	public void moveHorizontal(double delta)
	{
		centerPoint.setX(centerPoint.getX()+delta);
	}
	
	
	public void moveVertical(double delta)
	{
		centerPoint.setY(centerPoint.getY()+delta);
	}
	
	
	public void move(Point delta)
	{
		moveVertical(delta.getY());
		moveHorizontal(delta.getX());
	}
	
	
	public boolean isEqual(Triangle triangle)
	{
		if (triangle.height() == height && triangle.getCenter() == centerPoint)
		{
			return true;
		}
		return false;
	}
	
	
	public double getArea()
	{
		return (getLengthEdge()*height)/2;
	}

	
	
	public double getPerimeter()
	{
		return 3*getLengthEdge();
	}	
	
	private double y(double x, Point p0, Point p1) 
	{
		double m = (p1.getY() - p0.getY()) / (p1.getX() - p0.getX());
		return m*(x - p1.getX()) + p1.getY();
	}
	
	
	public boolean contains(Point p)
	{
		Point []arr;
		Point p0,p1,p2;
		double y;
		arr = getVertices();
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
			if (p.getX()<centerPoint.getX()) 
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
	
	
	public boolean contains(Triangle triangle)
	{
		Point arr[] = triangle.getVertices();
		return (contains(arr[0]) && contains(arr[1])&& contains(arr[2]));		
	}
	
}
