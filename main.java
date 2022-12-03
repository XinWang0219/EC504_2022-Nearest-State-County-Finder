import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
//        System.out.println("helloworld");
        String cmd = args[0];
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        kd.init();

        double la=Double.parseDouble(args[1]);
        double lo=Double.parseDouble(args[2]);
        int k=Integer.parseInt(args[3]);
        long start = System.currentTimeMillis();
        ArrayList<Point> res=kd.KNearestNeighbor(k,new Point(la,lo));
        long end=System.currentTimeMillis();

        for (Point i:res) {
            System.out.println("name:"+i.name+" x:"+i.getX()+" y:"+i.getY());
        }
        System.out.println("search time:"+(end-start));

    }
}
