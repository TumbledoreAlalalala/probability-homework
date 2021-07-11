import java.io.*;

import static java.lang.Math.pow;

public class Main {
    public static void main(String[] args)
    {
        FileWriter fw;
        try{
            fw=new FileWriter("output.txt");
            BufferedReader br=new BufferedReader(new FileReader("output.txt"));

            for(int i=0;i<100000;++i){
                //fw.write(i+" ");
                simulation aSimulation=new simulation();
                aSimulation.start(fw);
            }
            fw.close();
            double xForDays=0;
            double xForHappyDaysRate=0;
            String text;
            while((text=br.readLine())!=null){
                xForDays+=Double.parseDouble(text);
                text=br.readLine();
                xForHappyDaysRate+=Double.parseDouble(text);
            }
            xForDays/=100000;
            xForHappyDaysRate/=100000;
            br.close();
            br=new BufferedReader(new FileReader("output.txt"));
            double sForDays=0;
            double sForHappyDaysRate=0;
            while((text=br.readLine())!=null){
                sForDays+=pow((Double.parseDouble(text)-xForDays),2);
                text=br.readLine();
                sForHappyDaysRate+=pow((Double.parseDouble(text)-xForHappyDaysRate),2);
            }
            sForDays/=99999;
            sForHappyDaysRate/=99999;
            double daysError=sForDays*2.575878/(pow(100000,0.5));
            System.out.println("关系维系天数置信区间:("+(xForDays-daysError)+
                    ","+(xForDays+daysError)+")");
            double HDRateError=sForHappyDaysRate*2.575878/pow(100000,0.5);
            System.out.println("心情愉悦天数占比置信区间:("+(xForHappyDaysRate-HDRateError)+
                    ","+(xForHappyDaysRate+HDRateError)+")");
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }

    }

}
