import java.util.ArrayList;
import java.util.Collections;

public class KD_tree {
    ArrayList<Point> points;
    Point root;

    public KD_tree(){
        points=new ArrayList<>();
    }
    public void insert(Point p) {
        p.judge=false;
        points.add(p);
    }
    //找root节点
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
    public void buildTree(Point root){
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

    // base needs to be sorted, root needs to be in base
    public void getPointChilds(Point root,ArrayList<Point> base){
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
}
