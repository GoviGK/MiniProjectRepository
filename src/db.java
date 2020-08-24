
import javax.imageio.ImageIO;
import javax.swing.*;

public class db {
   void book(){
       JFrame frame = new JFrame("picture");
       frame.setBounds(0,0,1200,700);
       frame.setLayout(null);

       ImageIcon img = new ImageIcon("C:\\Users\\Govinda\\Desktop\\bg\\goku.jpg");
       JLabel bg = new JLabel("",img,JLabel.CENTER);

       bg.setBounds(0,0,200,200);
       frame.add(bg);
       frame.setVisible(true);
   }


    public static void main(String args[]){

        db d = new db();
d.book();

    }

}
