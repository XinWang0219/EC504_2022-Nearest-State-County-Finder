public class Point {
    private double x;
    private double y;
    public Point(double x,double y){
        this.x=x;
        this.y=y;
    }
    public Point(){
        this.x=0;
        this.y=0;
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
        double delta_phi=(phi2-phi1)*Math.PI/180;
        double delta_lamda=(y_other-y)*Math.PI/180;
        double a=Math.sin(delta_phi/2)*Math.sin(delta_phi/2)+Math.cos(phi1)*Math.cos(phi2)*Math.sin(delta_lamda)*Math.sin(delta_lamda);
        double c=2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));

        return R*c;
    }
}

//const R = 6371e3; // metres
//        const φ1 = lat1 * Math.PI/180; // φ, λ in radians
//        const φ2 = lat2 * Math.PI/180;
//        const Δφ = (lat2-lat1) * Math.PI/180;
//        const Δλ = (lon2-lon1) * Math.PI/180;
//
//        const a = Math.sin(Δφ/2) * Math.sin(Δφ/2) +
//        Math.cos(φ1) * Math.cos(φ2) *
//        Math.sin(Δλ/2) * Math.sin(Δλ/2);
//        const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
//
//        const d = R * c; // in metres