
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.Profile;
import jade.wrapper.*;

public class LaunchJade {
    public LaunchJade(){
        try{
            rt = Runtime.instance();
            rt.setCloseVM(true);
            Profile pMain = new ProfileImpl(null,8888,null);
            System.out.println("Launching..."+ pMain);
            mc = rt.createMainContainer(pMain);
        }
        catch(Exception E){

        }
        try {
            AgentController Service = mc.createNewAgent("Expéditeur",ComAgent.class.getName(),new Object[]{});
            Service.start();
            AgentController Reciever = mc.createNewAgent("Bettaj",ReceivAgent.class.getName(),new Object[]{});
            Reciever.start();
        }
        catch(Exception E){

        }





    }
    Runtime rt;
    static AgentContainer mc;
}
