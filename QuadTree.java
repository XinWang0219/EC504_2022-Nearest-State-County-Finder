import java.util.ArrayList;
import java.util.List;

public class QuadTree {
    private double mid_x;
    private double mid_y;
    private double width;
    private double height;
    private int capacity;
    public List<Point> points;
    public List<QuadTree> childs;
    private boolean isdivided;
    public QuadTree(double x,double y,double width,double height,int capacity){
        this.width=width;
        this.height=height;
        mid_x=x;
        mid_y=y;
        this.capacity=capacity;
        this.points=new ArrayList<>();
        this.childs=new ArrayList<>();
        this.isdivided=false;
    }
    public boolean contain(Point p) {
        double x=p.getX();
        double y=p.getY();
        if(x>=mid_x+width || x<mid_x-width || y>=mid_y+height || y<mid_y-height) {
            return false;
        }
        return true;
    }

    public void insert(Point newPoint){
        if(!contain(newPoint)) {return ;}
        if(points.size()<capacity) {
            points.add(newPoint);
        }
        else {
            if(!isdivided) {
                double x1=(mid_x+width)/2;
                double x2=(mid_x-width)/2;
                double y1=(mid_y+height)/2;
                double y2=(mid_y-height)/2;
                QuadTree NW=new QuadTree(x2,y1,width/2,height/2,capacity);
                QuadTree NE=new QuadTree(x1,y1,width/2,height/2,capacity);
                QuadTree SW=new QuadTree(x2,y2,width/2,height/2,capacity);
                QuadTree SE=new QuadTree(x1,y2,width/2,height/2,capacity);
                childs.add(NW);
                childs.add(NE);
                childs.add(SW);
                childs.add(SE);
                isdivided=true;
            }
            for(QuadTree child:childs) {
                child.insert(newPoint);
            }
        }
    }


}
