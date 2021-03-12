import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ReceivAgent extends Agent {

class MaFenetre extends JFrame implements ActionListener
{ public MaFenetre ()
 { setTitle("Agent Bettaj");
   setBounds(100,100,250,100);
   Container contenu = getContentPane();
   contenu.setLayout(new FlowLayout() );
   	
   Etiq= new JLabel ("Hallo World! My name is "+getLocalName());
   Etiq2= new JLabel ("j'attends un message de l'agent ALI");
   contenu.add(Etiq);	
   contenu.add(Etiq2); 
   boutonCalcul= new JButton("Kill");
   contenu.add(boutonCalcul);
   boutonCalcul.addActionListener(this);
 
 }

public void actionPerformed (ActionEvent e)
{ if (e.getSource()==boutonCalcul) {
  doDelete();
  this.dispose();
}
 } // fin du if

private JButton boutonCalcul;

} // fin de la classe MaFenetre

protected void setup() {
  	MaFenetre fen= new MaFenetre();
	fen.setVisible(true);
        doWait(10000);
	addBehaviour(new RecoiMessage());
  } 

public class RecoiMessage extends CyclicBehaviour {

public void action() {
ACLMessage msg ;
msg=receive();
if (msg != null){
		Etiq.setText ("j'ai reçu un message:");
		Etiq2.setText (msg.getContent());
		flag=true;
						
	        }

else {             if(flag==false) {
		   Etiq2.setText("je n'ai pas encore reçu de message");
	           block();
				   }
       }

}


} // fin de RecoiMessage
public class RespondMessage extends Behaviour {
    private boolean finished =false;
    public void action(){
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent("bonjour je suis "+getLocalName() );
        AID receiver = new AID("ali", AID.ISLOCALNAME);
        msg.addReceiver(receiver);
        send(msg);
        finished=true;



    }
    public boolean done(){
        if (finished){
            Etiq.setText("Ca y est , la reponse est envoyer !");
            Etiq2.setText("");
            Etiq3.setText("");
        }
        return finished;
    }
}


public JLabel Etiq,Etiq2,Etiq3;
Boolean flag=false;

}

