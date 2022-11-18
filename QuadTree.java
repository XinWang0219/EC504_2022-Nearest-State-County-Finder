import java.util.ArrayList;
import java.util.List;

public class QuadTree {
    private double mid_x;
    private double mid_y;
    private double width;
    private double height;
    private int capacity; //该点可以容纳多少个节点
    public QuadTree father;
    public List<Point> points;
    public List<QuadTree> childs;
    public List<QuadTree> neighbors;
    public int quad;
    public int depth;
    private boolean isdivided;
    public QuadTree(double x,double y,double width,double height,int capacity){
        this.width=width;
        this.height=height;
        mid_x=x;
        mid_y=y;
        this.capacity=capacity;
        this.points=new ArrayList<>();
        this.childs=new ArrayList<>();
        this.neighbors=new ArrayList<>();
        this.isdivided=false;
        this.father=null;
        this.quad=0;
        this.depth=0;
    }
    public void quadInfo(){
        System.out.println("x:"+mid_x+" y:"+mid_y+" width:"+width+" height:"+height+" depth:"+depth);
    }
    public boolean contain(Point p) {
        double x=p.getX();
        double y=p.getY();
        if(x>=mid_x+width || x<mid_x-width || y>=mid_y+height || y<mid_y-height) {
            return false;
        }
        return true;
    }

    //找到点p相对与该块在哪个象限
    public int findQuad(Point p) {
        if(!contain(p)) {return -1;}
        if(!isdivided){return 0;}
        double x=p.getX();
        double y=p.getY();
        if(x>=mid_x&&y>=mid_y) {
            return 1;
        }
        else if(x<mid_x&&y>=mid_y) {
            return 2;
        }
        else if(x<mid_x&&y<mid_y) {
            return 3;
        }
        else{ // x>=mid_x, y<mid_y
            return 4;
        }
    }

    public void insert(Point newPoint){
        if(!contain(newPoint)) {return ;}
        if(points.size()<capacity) {
            points.add(newPoint);
        }
        else {
            if(!isdivided) {
                double x1=mid_x+(width)/2;
                double x2=mid_x-(width)/2;
                double y1=mid_y+(height)/2;
                double y2=mid_y-(height)/2;
                QuadTree NW=new QuadTree(x2,y1,width/2,height/2,capacity);
                QuadTree NE=new QuadTree(x1,y1,width/2,height/2,capacity);
                QuadTree SW=new QuadTree(x2,y2,width/2,height/2,capacity);
                QuadTree SE=new QuadTree(x1,y2,width/2,height/2,capacity);
                childs.add(NE);
                childs.add(NW);
                childs.add(SW);
                childs.add(SE);
                NE.depth=this.depth+1;
                NW.depth=this.depth+1;
                SW.depth=this.depth+1;
                SE.depth=this.depth+1;
                NW.father=this;
                NE.father=this;
                SW.father=this;
                SE.father=this;
                neighbors.add(NE);
                NE.quad=1;
                neighbors.add(NW);
                NW.quad=2;
                neighbors.add(SW);
                SW.quad=3;
                neighbors.add(SE);
                SE.quad=4;
                isdivided=true;
            }
            for(QuadTree child:childs) {
                child.insert(newPoint);
            }
        }
    }

    //找到目标点在哪个最小块里
    public QuadTree search(Point p) {
        if(!contain(p)) {return null;}
        double x=p.getX();
        double y=p.getY();
        int quad=findQuad(p);
        if(quad==0) {return this;}
        if(quad==-1) {return null;}
        else {
            QuadTree next=this.childs.get(quad-1);
            return next.search(p);
        }
    }

    //找到某个QuadTree的邻居们：同样大小的块，相邻最多八个一圈
    public List<QuadTree> searchNeighbor(QuadTree q) {
        
        return null;
    }

}
