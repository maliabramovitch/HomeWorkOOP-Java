import java.lang.Math;


public class Triangle2 implements Triangle
{
	private Point leftCorner;
	private double edgeLen;
	private boolean up;
	
	public Triangle2()
	{
		Point p = new Point();
		double edgeLen = 1;
		leftCorner = HW2Utils.getCenterFromLeftPointLengthEdge(p, edgeLen, up);
		up = true;
	}
	
	
	public Triangle2(Point leftCorner, double sideLen, boolean up)
	{
		if (sideLen != 0)
		{
			this.edgeLen = sideLen;
			this.leftCorner = leftCorner;
			this.up = up;
		}
		else
		{
			System.out.println("height given was incorrect and set to defaulte value");
			this.up = up;
			this.edgeLen = 1;
			Point p = new Point();
			this.leftCorner = HW2Utils.getCenterFromLeftPointLengthEdge(p, sideLen, up);
		}
	}
		
		
	public Point[] getVertices() 
	{
		double height = HW2Utils.getHeightFromLengthEdge(edgeLen);
		Point centerPoint = HW2Utils.getCenterFromLeftPointLengthEdge(leftCorner, edgeLen, up);
		Point arr[];
		arr = HW2Utils.getVertices(centerPoint, height);
		return arr;
	}
		
		
	public Point getCenter()
	{
		Point centerPoint =HW2Utils.getCenterFromLeftPointLengthEdge(leftCorner, edgeLen, up);
		return centerPoint.copy();
	}
		
		
	public double height()
	{
		double height = HW2Utils.getHeightFromLengthEdge(edgeLen);
		return height;
	}
		
		
	public double getLengthEdge()
	{
		return edgeLen;
	}
		
		
	public boolean isUpTriangle()
	{
		return up;
	}
		
		
	public void setCenter(Point p)
	{
		if (p != null)
		{
			Point centerPoint = HW2Utils.getCenterFromLeftPointLengthEdge(p, edgeLen, up);
			centerPoint = HW2Utils.getLeftPointFromCenterLengthEdge(p.copy(), edgeLen, up);
		}
	}
	
	
	public void updateHeight(double height)
	{
		if (height>0)
		{
			edgeLen = HW2Utils.getHeightFromLengthEdge(height);
		}
	}
	
	
	public void inverse()
	{
		double height = HW2Utils.getHeightFromLengthEdge(edgeLen);
		if(up)
		{
			up = false;
			leftCorner.moveVertical(height);
		}
		else 
		{
			up = true;
			leftCorner.moveVertical(-height);
		}
	}
	
	
	public void updateLengthEdge(double lengthEdge)
	{
		leftCorner = HW2Utils.getLeftPointFromCenterLengthEdge(leftCorner, lengthEdge, up);
		edgeLen = lengthEdge;
	}
	
	
	public void scale(double scalePar)
	{
		if (scalePar>0)
		{
			updateLengthEdge(edgeLen*scalePar);
		}
	}
		
		
	public void moveVertical(double delta)
	{
		leftCorner.moveVertical(delta); 
	}
	
	
	public void moveHorizontal(double delta)
	{
		leftCorner.moveHorizontal(delta);
	}
	
	
	public void move(Point delta)
	{
		leftCorner.move(delta);
	}
	
	
	public boolean isEqual(Triangle triangle)
	{
		return up == triangle.isUpTriangle() && leftCorner == triangle.getVertices()[1] && edgeLen == triangle.getLengthEdge();
	}
	
	
	public double getArea()
	{
		return (edgeLen*height())/2;
	}
	
	
	public double getPerimeter()
	{
		return edgeLen*3;
	}
	
	
	public boolean contains(Point p)
	{
		Point arr[] = getVertices();
		Point centerPoint = getCenter();
		double height = height();		
		return HW2Utils.contains(p, arr, centerPoint, height);
	}
	
	
	public boolean contains(Triangle triangle)
	{
		Point arr[] = triangle.getVertices();
		return (contains(arr[0]) && contains(arr[1])&& contains(arr[2]));		
	}
}

