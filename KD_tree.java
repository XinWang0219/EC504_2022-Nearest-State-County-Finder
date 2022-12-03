import java.util.*;

public class KD_tree {
    private ArrayList<Point> points;
    Point root;

    private int size;

    public KD_tree(){
        points=new ArrayList<>();
        size=0;
    }
    public int size(){
        return this.size;
    }
    public void insert(Point p) {
        p.judge=false;
        points.add(p);
        size++;
    }
    public void init(){
        Collections.sort(points);
        this.root=points.get(points.size()/2);
        for( Point i:points) {
            if(i!=root) {
                i.father=root;
                i.depth++;
                if(i.compareTo(root)<0) {root.leftChilds.add(i);}
                else {root.rightChilds.add(i);}
                i.judge=!root.judge;
            }
        }
        buildTree(root);
    }
    //root leftchild rightchild needs to be done
    // to find the leftchild and rightchild
    private void buildTree(Point root){
        if(root.leftChilds.size()>0) {
            Collections.sort(root.leftChilds);
            root.left=root.leftChilds.get(root.leftChilds.size()/2);
            getPointChilds(root.left,root.leftChilds);
            buildTree(root.left);
        }
        if(root.rightChilds.size()>0) {
            Collections.sort(root.rightChilds);
            root.right=root.rightChilds.get(root.rightChilds.size()/2);
            getPointChilds(root.right,root.rightChilds);
            buildTree(root.right);
        }
        root.leftChilds.clear();
        root.rightChilds.clear();
        return;
    }

    public void layerPrint(){
        Queue<Point> base=new LinkedList<>();
        Point curend=root;
        Point nextend=null;
        base.add(root);
        while(!base.isEmpty()) {
            Point tmp=base.poll();
            System.out.printf("(%.2f,%.2f) ",tmp.getX(),tmp.getY());
            if(tmp.left!=null) {
                base.add(tmp.left);
                nextend=tmp.left;
            }
            if(tmp.right!=null) {
                base.add(tmp.right);
                nextend=tmp.right;
            }
            if(tmp==curend) {
                System.out.println();
                curend=nextend;
                nextend=null;
            }
        }
    }

    // base needs to be sorted, root needs to be in base
    private void getPointChilds(Point root,ArrayList<Point> base){
        for(Point i:base) {
            if(i!=root) {
                i.father=root;
                i.depth++;
                if(i.compareTo(root)<0) {root.leftChilds.add(i);}
                else {root.rightChilds.add(i);}
                i.judge=!root.judge;
            }
        }
    }

    public ArrayList<Point> KNearestNeighbor(int k,Point p) {
        ArrayList<Point> res=new ArrayList<>();
        PriorityQueue<Point> maxHeap=new PriorityQueue<>((a,b)->{
            double d1=a.distance(p);
            double d2=b.distance(p);
            if(d1<d2) {return 1;}
            else if(d1==d2) {return 0;}
            else {return -1;}
//            return (int)(d2-d1);
        });
        Point tmp=this.root;

        recursionSearch(p,tmp,maxHeap,k);
        while(!maxHeap.isEmpty()){
            res.add(maxHeap.poll());
        }
        return res;
    }

    private void recursionSearch(Point target,Point cur,PriorityQueue<Point> maxHeap,int k) {
        if(cur==null) {return ;}

        if(cur.compareTo(target)>=0) {
            recursionSearch(target,cur.left,maxHeap,k);

            if(cur.right!=null) {
                if(maxHeap.isEmpty()||maxHeap.size()<k) {recursionSearch(target, cur.right, maxHeap,k);}
                else {
                    double maxdistance = maxHeap.peek().distance(target);
                    double targetToRight;
                    if (!cur.right.judge) {
                        targetToRight = Math.abs(target.getX() - cur.right.getX());
                    } else {
                        targetToRight = Math.abs(target.getY() - cur.right.getY());
                    }
                    if (targetToRight < maxdistance || maxHeap.size() < k) {
                        recursionSearch(target, cur.right, maxHeap, k);
                    }
                }
            }
        }
        else{
            recursionSearch(target,cur.right,maxHeap,k);
            if(cur.left!=null) {
                if(maxHeap.isEmpty()||maxHeap.size()<k) {recursionSearch(target, cur.left, maxHeap,k);}
                else {
                    double maxdistance = maxHeap.peek().distance(target);
                    double targetToLeft;
                    if (!cur.left.judge) {
                        targetToLeft = Math.abs(target.getX() - cur.left.getX());
                    } else {
                        targetToLeft = Math.abs(target.getY() - cur.left.getY());
                    }
                    if (targetToLeft < maxdistance || maxHeap.size() < k) {
                        recursionSearch(target, cur.left, maxHeap, k);
                    }
                }
            }
        }
        if(maxHeap.size()<k) {maxHeap.add(cur);}
        else if(cur.distance(target)<maxHeap.peek().distance(target)) {
            maxHeap.poll();
            maxHeap.add(cur);
        }

    }
}
