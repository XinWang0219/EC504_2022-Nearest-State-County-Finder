

import java.util.ArrayList;

public class Point implements Comparable<Point>{
    private double x;
    private double y;

    public String name="undefined";
    public boolean judge=false; // false -> x , true -> y
    public int depth;
    public Point left=null;
    public Point right=null;
    public Point father=null;
    public ArrayList<Point> leftChilds;
    public ArrayList<Point> rightChilds;
    public Point(double x,double y,String name){
        this.x=x;
        this.y=y;
        this.depth=0;
        this.leftChilds=new ArrayList<>();
        this.rightChilds=new ArrayList<>();
        this.name=name;
    }
    public Point(double x,double y){
        this.x=x;
        this.y=y;
        this.depth=0;
        this.leftChilds=new ArrayList<>();
        this.rightChilds=new ArrayList<>();
    }
    public Point(){
        this.x=0;
        this.y=0;
        this.depth=0;
        this.leftChilds=new ArrayList<>();
        this.rightChilds=new ArrayList<>();
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    public double distance(Point other) {
        double x_other=other.getX();
        double y_other=other.getY();
        double R=6371;
        double x1=x*Math.PI/180;
        double x2= x_other*Math.PI/180;
        double y1=y*Math.PI/180;
        double y2=y_other*Math.PI/180;
        double xx=(y1-y2)*Math.cos((x1+x2)/2);
        double yy=x1-x2;
        double distince=R*Math.sqrt(xx*xx+yy*yy);
        return distince;


//        double x_other=other.getX();
//        double y_other=other.getY();
//        return(Math.sqrt(Math.pow(this.x-x_other,2)+Math.pow(this.y-y_other,2)));
    }


    @Override
    public int compareTo(Point o) {
        if(judge) {
            if(this.getY()>o.getY()) {return 1;}
            else if(this.getY()==o.getY()) {return 0;}
            else {return -1;}
        }
        else {
            if(this.getX()>o.getX()) {return 1;}
            else if(this.getX()==o.getX()) {return 0;}
            else {return -1;}
        }
    }
}
