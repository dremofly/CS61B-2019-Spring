public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        double r = Math.sqrt(Math.pow(xxPos-b.xxPos,2) + Math.pow(yyPos-b.yyPos,2));
        return r;
    }

    public double calcForceExertedBy(Body b){

        double r = calcDistance(b);
        double G = 6.67e-11;
        return G*mass*b.mass/Math.pow(r,2);
    }

    public double calcForceExertedByX(Body b){
        double force = calcForceExertedBy(b);
        double r = calcDistance(b);
        return force*(b.xxPos - xxPos)/r;
    }

    public double calcForceExertedByY(Body b){
        double force = calcForceExertedBy(b);
        double r = calcDistance(b);
        return force*(b.yyPos - yyPos)/r;
    }

    public double calcNetForceExertedByX(Body[] bs){
        double xForce = 0;
        for(int i=0; i<bs.length; i++){
            if(!this.equals(bs[i]))
                xForce += this.calcForceExertedByX(bs[i]);
        }
        return xForce;
    }

    public double calcNetForceExertedByY(Body[] bs){
        double yForce = 0;
        for(int i=0; i<bs.length; i++){
            if(!this.equals(bs[i]))
                yForce += this.calcForceExertedByY(bs[i]);
        }
        return yForce;
    }

    public void update(double dt, double fX, double fY){
        // 计算加速度
        double aX = fX/this.mass;
        double aY = fY/this.mass;
        // 计算速度
        this.xxVel = this.xxVel + dt * aX;
        this.yyVel = this.yyVel + dt * aY;
        // 计算位置
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }
}
