import java.util.Random;

public class stalker {
    private int Immersion;
    private final Random sRandom;
    private boolean justEnhanceImmersion=true;
    private int countPoliteness;
    private int sumActionsOfRecentTwoDay=0;


    public stalker(){
        Immersion=60;
        countPoliteness=0;
        sRandom=new Random();
    }

    public void setJustEnhanceImmersion(boolean val){justEnhanceImmersion=val;}

    public boolean getJustEnhanceImmersion(){return justEnhanceImmersion;}

    public int getCountPoliteness(){return countPoliteness;}

    public int getSumActionsOfRecentTwoDay(){return sumActionsOfRecentTwoDay;}

    public int getImmersion(){return Immersion;}
    //随机单次行动  0(不作为)  1(有效事务)  2(无效事务)
    public int singleAction(){
        int result=0;
        //若前两天活动数少于2时，再随机第三天的活动，否则第三天认定为不活动
        if(sumActionsOfRecentTwoDay!=2){
            result=sRandom.nextInt(10);
        }
        if(result<3){
            if(justEnhanceImmersion){
                Immersion+=1;
            }
            else
                Immersion-=1;
            return 0;
        }
        else if(result<5)
            return 1;
        else
            return 2;
    }
    //接收到回应
    public void receivePoliteness(){
        if(countPoliteness<10) {
            Immersion += 5;
            justEnhanceImmersion=true;
        }
        else{
            Immersion-=1;
            justEnhanceImmersion=false;
        }
        countPoliteness++;
    }
    //接收到负面反馈
    public void receiveNegativeFeedback(){
        Immersion-=7;
        justEnhanceImmersion=false;
    }
    //接收到正面反馈
    public void receivePositiveFeedback(){
        Immersion+=7;
        justEnhanceImmersion=true;
    }
    //接收到无响应
    public void receiveNoResponse(){
        if(countPoliteness>14)
            Immersion-=3;
        justEnhanceImmersion=false;
    }
    //更新最近三天活动数
    public void updateSAORTD(int change){
        sumActionsOfRecentTwoDay+=change;
    }
}
