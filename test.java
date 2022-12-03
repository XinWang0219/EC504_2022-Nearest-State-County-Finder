import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class test {
    public static void main(String[] args) {
//        Random random=new Random();
//        random.setSeed(10);
//        int[] arr=new int[1000];
//        for(int i=0;i<arr.length;i++) {
//            arr[i]= random.nextInt(100);
//            System.out.printf("%d\t",arr[i]);
//        }
//        System.out.println();
//        QuadTree qt=new QuadTree(50,50,100,100,4);
//        for(int i=0;i<arr.length;i++) {
//            Point tmp=new Point(arr[i],arr[i]-2);
//            qt.insert(tmp);
//        }
//        QuadTree res=qt.search(new Point(65,61));
//        res.quadInfo();
//        Point p1=new Point(34.3209,-118.2247);
//        Point p2=new Point(41.8401,-87.8168);
//        double res=p1.distance(p2);
//        System.out.println(res);
//        double[] xs= {1,2,3,4};
//        double[] ys={1,2,3,4};
//        KD_tree kt=new KD_tree();
//        for(int i=0;i<xs.length;i++) {
//            for(int j=0;j<ys.length;j++) {
//                kt.insert(new Point(xs[i],ys[j]));
//            }
//        }
//        kt.init();
//        kt.layerPrint();
//
//        Point target=new Point(2.1,2.5);
//
//        ArrayList<Point> res=kt.KNearestNeighbor(5,target);
//        System.out.println();
        KD_tree kd=new KD_tree();
        String filePath="/Users/jianxiaoyang/Documents/EC504 Algorithm/project_pythonversion/EC504_2022-Nearest-State-County-Finder/data/uscounties.csv";
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            reader.readLine();
            String line = null;
            while((line=reader.readLine())!=null){
                String item[] = line.split(",");
                String name=item[0];
                double la=Double.parseDouble(item[6].substring(1, item[6].length()-2));
                double lo=Double.parseDouble(item[7].substring(1,item[7].length()-2));
                Point p=new Point(la,lo,name);
                kd.insert(p);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        kd.init();
//        kd.layerPrint();

        ArrayList<Point> res=kd.KNearestNeighbor(10,new Point(31.84,-103.58));

        for(Point p:res) {
            System.out.println("name:"+p.name+" x:"+p.getX()+" y:"+p.getY());
        }
    }
}
