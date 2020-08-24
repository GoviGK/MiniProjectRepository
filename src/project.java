import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;

public class project extends JFrame {

    static JLabel l;
    static JPanel p;
    static JFrame f,admin,user;
    static JTable t;
    static JButton bt,logIN,insert,submit;
    static JScrollPane pane;
    static DefaultTableModel m;
    static JFrame f2;
    static JScrollBar bar;
    static JTextField tf;
    static JDialog d,ar,log;
    static JLabel u_label,p_label;
    static JTextField u_tf,p_tf;
    static int flag,log_in;

    public project(){

        //initializing the variables
        f= new JFrame();
        bt = new JButton("Display");
        logIN = new JButton("LogIn");
        insert = new JButton("Register");

        ///-->>>frame details
        f.getContentPane().setBackground(Color.WHITE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        f.setSize(screenSize.width, screenSize.height);

        ///--->>>labels
        l = new JLabel("MENU");
        l.setFont(new Font("Serif", Font.BOLD, 20));
        l.setBounds(110,50,130,30) ;


        /////Panel

        p = new JPanel();
        p.setBounds(300, 100, 1200,680);
        p.setBackground(Color.LIGHT_GRAY);


        //-->>Buttons
        insert.setHorizontalAlignment(SwingConstants.CENTER);
        logIN.setHorizontalAlignment(SwingConstants.CENTER);
        bt.setHorizontalAlignment(SwingConstants.CENTER);
        insert.setBackground(Color.white);
        bt.setBackground(Color.white);
        logIN.setBackground(Color.white);
        insert.setBounds(70,150,130,30);
       //
        logIN.setBounds(70,100,130,30);
        bt.setBounds(70,200,130,30);

        //-->>frame components
        f.add(l);
        f.add(p);
        f.add(bt);
        f.add(logIN);
        f.add(insert);

    }
    void main_frame()
    {
    }


    void logIN(){

        d = new JDialog(f, "Log IN");
        d.setBounds(250, 250, 400, 500);


        submit = new JButton("SUBMIT");

        ////-------LABELS
        u_label = new JLabel("Username");
        p_label = new JLabel("Password");




        ///---------TEXTFIELDS
        u_tf = new JTextField();
        p_tf = new JTextField();


        /////------SIZE AND POSITIONS
        u_label.setBounds(100, 50, 100, 20);
        p_label.setBounds(100, 100, 100, 20);
        u_tf.setBounds(180,50, 150, 20);
        p_tf.setBounds(180,100, 150, 20);
        submit.setBounds(200,150, 100, 30);


        ///-----DialogBox Components
        d.add(submit);
        d.add(p_tf);
        d.add(p_label);
        d.add(u_tf);
        d.add(u_label);


        /////-------SUBMIT Button event----->>>>>>
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(e.getSource()==submit) {

                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useSSL = true", "root", "Govinda@20");

                        PreparedStatement st = con.prepareStatement("insert into login(U_Name, Password) values(?, ?)");
                        PreparedStatement st1 = con.prepareStatement("select * from login");
                        ResultSet rs = st1.executeQuery();
                        String nm, pass;
                        nm = u_tf.getText();
                        pass = p_tf.getText();

                        while (rs.next()) {
                            if ((nm.compareTo(rs.getString(1)) == 0) && (pass.compareTo(rs.getString(2)) == 0)) {
                                System.out.println("equal");
                                flag = 1;
                            } else if ((nm.compareTo(rs.getString(1)) == 0) && (pass.compareTo(rs.getString(2)) != 0)) {
                                System.out.println("Wrong Password");

                            } else if ((nm.compareTo(rs.getString(1)) != 0) && (pass.compareTo(rs.getString(2)) == 0)) {
                                System.out.println("Wrong Username");

                            } else if ((nm.compareTo(rs.getString(1)) != 0) && (pass.compareTo(rs.getString(2)) != 0)) {
                                System.out.println("Wrong Username and Password");
                                //System.out.println(rs.getString(1) + rs.getString(2));
                            }


                        }
                        System.out.println(flag);


                        if (flag == 1) {

                            ar = new JDialog(f, "Messege");

                            ar.setBounds(250, 350, 400, 150);
                            JLabel k = new JLabel("Logged In Successfully.");
                            k.setFont(new Font("Serif", Font.PLAIN, 17));
                            k.setHorizontalAlignment(0);
                            k.setBounds(300, 100, 150, 30);
                            ar.add(k);
                            ar.setVisible(true);
                            ar.setLayout(null);
                            System.out.println("logIN");
                            flag=0;
                            log_in = 1;
                        }
                        else {
                            JDialog d1 = new JDialog(f, "Interrupt Message");

                            d1.setBounds(250, 350, 400, 150);
                            JLabel k1 = new JLabel("Invalid Credentials..!!!");
                            k1.setFont(new Font("Serif", Font.PLAIN, 17));
                            k1.setHorizontalAlignment(0);
                            k1.setBounds(300, 100, 150, 30);
                            d1.add(k1);
                            d1.setVisible(true);
                            d1.setLayout(null);
                        }


                    } catch(Exception e1){

                        System.out.println( e1);


                    }


                }
            }

        });
////-----------------------------------------------------------

        d.setLayout(null);
        d.setVisible(true);

    }



    void disp_data(){
        p.setLayout(null);
        pane = new JScrollPane();
        p.add(l);

        String []col={"No.","Name","People","Date And Time"};
        m = new DefaultTableModel();
        m.setColumnIdentifiers(col);
        t = new JTable();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)t.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);


        try{

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useSSL=true", "root", "Govinda@20");
            PreparedStatement st1 = con.prepareStatement("Select * from bookin");
            ResultSet rs = st1.executeQuery();

            while(rs.next()){
                m.addRow(new Object[]{

                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                });
            }


        }
        catch(Exception e){
            System.out.println(e);
        }

        t.setBackground(Color.white);

        t.setModel(m);
        pane = new JScrollPane(t);

        pane.setBounds(15, 100,1000, 550);
        p.add(l);
        p.add(pane);
        p.setLayout(null);


    }


    public static void main(String args[]){

        final project p = new project();

        ////---->>>>Buttons Design
        logIN.addMouseListener(new java.awt.event.MouseAdapter(){


            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logIN.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                logIN.setBackground(UIManager.getColor("control"));
            }
        });

        bt.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt.setBackground(UIManager.getColor("control"));
            }
        });

        insert.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                insert.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                insert.setBackground(UIManager.getColor("control"));
            }
        });

//--------------------------------------------------------------------------






///------------>>>Display Button Event
        bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                p.disp_data();
            }

        });
//----------------------------------------------------------------


//------------->>LogIN Button Event--------------------------
        logIN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                p.logIN();
            }

        });
//------------------------------------------------------------------



//---------------------------------------------------------

        f.setLayout(null);
        f.setVisible(true);

    }
}

