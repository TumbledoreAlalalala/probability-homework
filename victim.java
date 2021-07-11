import java.util.HashMap;
import java.util.Random;

public class victim {
    private HashMap<Integer,Integer> effectiveTouchMap;
    private int countPoliteResponse=0;
    private final Random vRandom;

    public int getCountPoliteResponse(){return  countPoliteResponse;}

    public int getEffectiveTouchNumber(){return effectiveTouchMap.size();}

    public victim(){
        vRandom=new Random();
        effectiveTouchMap =new HashMap<>();
        effectiveTouchMap.put(0,3);
        effectiveTouchMap.put(1,3);
        effectiveTouchMap.put(2,3);
        effectiveTouchMap.put(3,3);
    }
    //触发无效事务，根据无效事务已触发的次数决定给予回应(1) 或者 无响应(0) 或者 负反馈 (2)
    public int invalidTouch(){
        int result;
        result=vRandom.nextInt(2);
        if(result==0)
            return 0;
        if(countPoliteResponse<20){
            countPoliteResponse++;
            return 1;
        }
        else
            return 2;
    }
    public int effectiveTouch(){
        int result;
        if(effectiveTouchMap.size()==0)
            return invalidTouch();
        do{
            result=vRandom.nextInt(4);
        }while(!effectiveTouchMap.containsKey(result));
        int temp=effectiveTouchMap.get(result);
        if(temp>0){
            effectiveTouchMap.put(result,--temp);
            return 3;
        }
        else{
            effectiveTouchMap.remove(result);
            return invalidTouch();
        }
    }
}
