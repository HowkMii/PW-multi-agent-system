import jade.core.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import jade.core.behaviours.*;
import jade.lang.acl.*;



public class ComAgent extends Agent {

class MaFenetre extends JFrame implements ActionListener
{ public MaFenetre ()
 { setTitle("Agent Expéditeur");
   setSize(250,100);
   Container contenu = getContentPane();
   contenu.setLayout(new FlowLayout() );

   Etiq= new JLabel ("Hallo World! My name is "+getLocalName());
   contenu.add(Etiq);	 
   boutonCalcul= new JButton("Kill");
   contenu.add(boutonCalcul);
   boutonCalcul.addActionListener(this);
   Etiq2= new JLabel ("je vais envoyer un message dans 5 s.");
   contenu.add(Etiq2);	 

   
 }

public void actionPerformed (ActionEvent e)
{ if (e.getSource()==boutonCalcul) {
  doDelete();
  this.dispose();
}//fin du if
 
} 

private JButton boutonCalcul;


} // fin de la classe MaFenetre


protected void setup() {
  	MaFenetre fen= new MaFenetre();
	fen.setVisible(true);
            doWait(5000);
  	addBehaviour(new EnvoiMessage());

  } // fin de setup 

public class EnvoiMessage extends Behaviour {
private boolean finished=false;

public void action() {
ACLMessage msg = new ACLMessage (ACLMessage.INFORM);
msg.setContent("Bonjour je suis l'agent"+getLocalName());
AID receiver = new AID("Bettaj", AID.ISLOCALNAME);
msg.addReceiver(receiver);
send(msg);
finished= true;
}

public boolean done() {
if (finished) {
	Etiq.setText("Ca y est, le Message est envoyé !!");
	Etiq2.setText("");
	}
return finished;
}

} // fin de EnvoiMessage

public JLabel Etiq,Etiq2;
}


