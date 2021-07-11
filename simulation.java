import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class simulation {
    public void start(FileWriter wf) throws IOException {
        stalker aStalker=new stalker();
        victim aVictim=new victim();
        int days=0;
        int happyDays=0;
        Vector<Boolean> actionLog=new Vector<>();
        actionLog.add(false);
        actionLog.add(false);
        while(aStalker.getImmersion()>24){
            days++;
            int singleAction= aStalker.singleAction();
            actionLog.add(singleAction>0?true:false);
            int tempChangeVal=actionLog.get(2)?actionLog.get(0)?0:1:actionLog.get(0)?-1:0;
            aStalker.updateSAORTD(tempChangeVal);
            actionLog.remove(0);
            //System.out.println("第"+days+"天，追求者行动号:"+singleAction);
            if(singleAction!=0){
                int feedback=0;
                //stalker尝试采取有效行动
                if(singleAction==1){
                    feedback=aVictim.effectiveTouch();
                }
                //stalker尝试采取无效行动
                else if(singleAction==2){
                    feedback=aVictim.invalidTouch();
                }


                //System.out.println("第"+days+"天，追求者收到的反馈:"+feedback);
                //收到无响应反馈
                if(feedback==0){
                    aStalker.receiveNoResponse();
                }
                //收到礼貌社交回应
                else if(feedback==1){
                    aStalker.receivePoliteness();
                }
                //收到负面反馈
                else if(feedback==2){
                    aStalker.receiveNegativeFeedback();
                }
                //收到正面反馈
                else if(feedback==3){
                    aStalker.receivePositiveFeedback();
                }
            }
            if(aStalker.getJustEnhanceImmersion())
                happyDays++;
            if(singleAction==0){
                aStalker.setJustEnhanceImmersion(false);
            }
            /*System.out.println("第"+days+"天结束, immersion:"+aStalker.getImmersion()+
                    "  immersion"+(aStalker.getJustEnhanceImmersion()?"上升":"下降")+
                    "   追求者状态: countPoliteness："+aStalker.getCountPoliteness() +
                    " SAORTD:"+aStalker.getSumActionsOfRecentTwoDay()+
                    "  受害者状态: countPoliteResponse:"+aVictim.getCountPoliteResponse()+
                    "  有效事务剩余次数: "+aVictim.getEffectiveTouchNumber());*/
        }
        wf.write(days+"\n"+((double)happyDays)/days+"\n");
        wf.flush();
    }
}
