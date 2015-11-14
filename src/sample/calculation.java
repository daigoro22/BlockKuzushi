package sample;

/**
 * Created by abedaigorou on 15/09/19.
 */
public class calculation
{
    public static double min(double up,double down,double left,double right)
    {
        double[] num={up,down,left,right};
        double min=num[0];

        for(double d:num)
            min=(d<min)?(d):(min);

        return min;
    }

    //点と直線の距離を返す
    public static double distance(int a,int b,int c,double xo,double yo)
    {
        return Math.abs(a*xo+b*yo+c)/Math.sqrt(a^2+b^2);
    }

    //点と点の距離を返す
    public static double Pdistance(double x0,double y0,double x1,double y1)
    {
        return (Math.sqrt(Math.abs((int)x0-(int)x1)^2+Math.abs((int)y0-(int)y1)^2));
    }
}
