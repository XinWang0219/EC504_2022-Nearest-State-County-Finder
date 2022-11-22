import java.util.ArrayList;

public class Point implements Comparable<Point>{
    private double x;
    private double y;
    public boolean judge=false; // false -> x , true -> y
    public int depth;
    public Point left=null;
    public Point right=null;
    public Point father=null;
    public ArrayList<Point> leftChilds;
    public ArrayList<Point> rightChilds;
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
        double phi1=x*Math.PI/180;
        double phi2= x_other*Math.PI/180;
        double delta_phi=(phi2-phi1);
        double delta_lamda=(y_other-y)*Math.PI/180;
        double a=Math.sin(delta_phi/2)*Math.sin(delta_phi/2)+Math.cos(phi1)*Math.cos(phi2)*Math.sin(delta_lamda)*Math.sin(delta_lamda);
        double c=2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));

        return R*c;
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
