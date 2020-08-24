import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;

public class hotel {
    static JLabel l,heading;
    static JPanel pan2;
    static JFrame f,admin,user;
    static JTable t,tab;
    static JButton submit1,submit,new_user;
    static JScrollPane pane;
    static DefaultTableModel m,mod;
    static JFrame adFrame,usFrame,mainF;
    static JScrollBar bar;
    static JTextField tf,Name_tf,p_tfr , u_tfr;
    static JDialog log,log1;
    static JLabel u_label,p_label,u_label1,p_label1;
    static JTextField n_tf,u_tf,p_tf,u_tf1,p_tf1;
    static int rowinserted,flag=0,flag_u=0,cnt1=0,a, flagbk, f_price;;
    static JRadioButton rb,rb2,rb1,rb21;


    hotel() {
    }

void main_dial(){
    log = new JDialog();
    log.setBounds(570, 170, 400, 500);

    ///-----BUTTONS---->>>
    ButtonGroup g = new ButtonGroup();
    submit = new JButton("LOGIN");
    JRadioButton rb2 = new JRadioButton("User");
    JRadioButton rb = new JRadioButton("Admin");
    new_user = new JButton("REGISTER");
    g.add(rb);g.add(rb2);

    ////-------LABELS---------
    JLabel heading = new JLabel("HOTEL SEA HORIZON");   heading.setFont(new Font("Serif", Font.BOLD, 20));
    JLabel n_label = new JLabel("Name");                n_label.setFont(new Font("TimesRoman", Font.BOLD, 15));
    JLabel u_label = new JLabel("Username");            u_label.setFont(new Font("Courier", Font.BOLD, 15));
    JLabel p_label = new JLabel("Password");            p_label.setFont(new Font("Courier", Font.BOLD, 15));




    ///---------TEXTFIELDS------------
     n_tf = new JTextField();
     u_tf = new JTextField();
     p_tf = new JTextField();


    /////------SIZE AND POSITIONS---------
    heading.setBounds(90, 30, 250, 20);
    heading.setBackground(Color.cyan);

    n_label.setBounds(80, 100, 100, 20);
    n_tf.setBounds(160,100, 150, 20);

    u_label.setBounds(80, 130, 100, 20);
    u_tf.setBounds(160,130, 150, 20);

    p_label.setBounds(80, 160, 100, 20);
    p_tf.setBounds(160,160, 150, 20);

    rb.setBounds(80,185, 100, 30);
    rb2.setBounds(200,185, 100, 30);
    submit.setBounds(80,235, 80, 20);
    new_user.setBounds(200,235, 100, 20);


    ///----Decoration--->>>



    ///-----New Registration Button Event-----------
    new_user.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            log.dispose();
            for_new();

        }
    });

        ///-----Log IN Button Event-----------
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (rb.isSelected()) {

                    if ((u_tf.getText().isEmpty()) || (p_tf.getText().isEmpty())|| (n_tf.getText().isEmpty())) {
                        //for empty text case
                        int flag_l = 1;
                        dilog(flag_l);
                    }
                    else {

                        try {

                            Connection con = DriverManager.getConnection("sql12.freemysqlhosting.net/sql12358156", "sql12358156", "LrfJMIu8r6");
                            PreparedStatement a_st = con.prepareStatement("Select * from admin");

                            ResultSet a_rs = a_st.executeQuery();

                            //////-------------------FOR ADMIN-------------------
                        int cnt1=0;
                        while(a_rs.next()){

                            if((u_tf.getText().equals(a_rs.getString(2))) &&( p_tf.getText().equals(a_rs.getString(3))) && n_tf.getText().equals(a_rs.getString(1))){
                           // for_admin();
                            cnt1=1;
                            break;
                            }


                        }
                            if(cnt1==1) {

                                for_admin();
                            }

                            else {
                                dilog(4);
                            }

                        } catch (SQLException p) {
                            // TODO Auto-generated catch block
                            p.printStackTrace();
                        }

                    }

                }
            }

        });

        ////--------------------FOR USER--------------------

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb2.isSelected()) {
                    if (u_tf.getText().isEmpty() || p_tf.getText().isEmpty() || (n_tf.getText().isEmpty()) ) {
                        int flag_l = 1;
                        dilog(flag_l);

                    } else {

                        try {
                            Connection con = DriverManager.getConnection("sql12.freemysqlhosting.net/sql12358156", "sql12358156", "LrfJMIu8r6");
                            PreparedStatement a_st = con.prepareStatement("Select * from customer");

                            ResultSet a_rs = a_st.executeQuery();
                            int cntu=0;
                            while (a_rs.next()) {
                                if ((u_tf.getText().equals(a_rs.getString(2))) && (p_tf.getText().equals(a_rs.getString(3))) && n_tf.getText().equals(a_rs.getString(1)) ) {
                                    cntu = 1;
                                }
                            }

                            if(cntu==1) for_user();
                            else {dilog(4);}
                        } catch (SQLException p) {
                            // TODO Auto-generated catch block
                            p.printStackTrace();
                        }
                    }
                }
            }
        });




    ///-----Main DialogBox Components----------
    log.add(n_tf);
    log.add(n_label);
    log.add(rb);
    log.add(rb2);
    log.add(heading);
    log.add(submit);
    log.add(p_tf);
    log.add(p_label);
    log.add(u_tf);
    log.add(u_label);
    log.add(new_user);
    log.setLayout(null);
    log.setVisible(true);

}


//--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    ////-------------------ADMIN'S DASHBOARD----------
//--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+


    void for_admin(){
         log.dispose();
         adFrame = new JFrame("Admin"+" "+n_tf.getText());
         adFrame.setBounds(0,0,500,500);
         adFrame.getContentPane().setBackground(Color.white);
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
         adFrame.setSize(screenSize.width, screenSize.height);

        ImageIcon img = new ImageIcon("C:\\Users\\Govinda\\Desktop\\bg\\goku.jpg");
        JLabel bg = new JLabel("",img,JLabel.CENTER);
        bg.setBounds(0,0,200,200);

        JRadioButton  ap,rej,pend;
        JPanel bag = new JPanel();

        JButton cust = new JButton("Payment"), logn = new JButton("Logout");
        JButton bok = new JButton("Bookings"),dish = new JButton("Menucard");
        ap = new JRadioButton ("Approved");
        rej = new JRadioButton ("Rejected");
        pend = new JRadioButton ("Pending");
        ButtonGroup g = new ButtonGroup();
        g.add(ap);
        g.add(rej);
        g.add(pend);

        //bag.setBackground(Color.lightGray);
        ap.setBackground(bag.getBackground());
        rej.setBackground(bag.getBackground());
        pend.setBackground(bag.getBackground());

        logn.setForeground(Color.BLUE);
        logn.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logn.setBackground(Color.LIGHT_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                logn.setBackground(UIManager.getColor("control"));
            }
        });

        bag.setBounds(100,5,1350,200);
        logn.setBounds(1200,20,80,20);
        cust.setBounds(340,20,100 ,20);
        bok.setBounds(100,20,90 ,20);
        ap.setBounds(100,60,90 ,20);
        rej.setBounds(100,80,90,20);
        pend.setBounds(100,100,90,20);
        dish.setBounds(100,130,100,20);
        dish.setBounds(220,20,100,20);

        bag.add(logn);
        bag.add(cust);
        bag.add(bok);
        bag.add(dish);
        bag.add(ap);
        bag.add(pend);
        bag.add(rej);
        bag.setLayout(null);

        bok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookdata();

            }
        });
        cust.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 custs();
            }
        });
        logn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adFrame.dispose();
                main_dial();
            }
        });

        bok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ap.isSelected()) {
                    menucard();
                }
            }
        });

        ap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ap.isSelected()) {
                    aprBookings();
                }
            }
        });


        rej.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rej.isSelected()) {
                    rejBookings();
                }

            }
        });
        pend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pend.isSelected()) {
                    pendBooking();
                }
            }
        });
        dish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menucard();
            }
        });

        //pane.setLayout(null);

        adFrame.add(bag);
        adFrame.setLayout(null);
        adFrame.setVisible(true);

    }

//----Displaying bookings---------
    void bookdata(){

        String []col={"Name","Person(s)","Tables","Food","Quantity","Date And Time","Status"};
        m = new DefaultTableModel();
        m.setColumnIdentifiers(col);
       JTable t = new JTable();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)t.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel sort_bk = new JPanel();
        JLabel status = new JLabel("Change Status");
        JLabel nm = new JLabel("Name");
        JLabel fd = new JLabel("Food");
        JTextField tf_nm = new JTextField();
        JTextField tf_fd = new JTextField();
        JRadioButton r = new JRadioButton("Reject");
        JRadioButton ap = new JRadioButton("Approve");
        JButton sub = new JButton("Submit");
        ButtonGroup g = new ButtonGroup();
        g.add(r);g.add(ap);


        sort_bk.setBounds(1220,450,220,300);
        status.setBounds(20,20,100,20);
        nm.setBounds(20,60,100,20); tf_nm.setBounds(75,60,100,20);
        fd.setBounds(20,90,100,20); tf_fd.setBounds(75,90,100,20);
        r.setBounds(20,130,100,20);
        ap.setBounds(20,150,100,20);
        sub.setBounds(20,190,100,20);

        try {
            Connection con = DriverManager.getConnection("sql12.freemysqlhosting.net/sql12358156", "sql12358156", "LrfJMIu8r6");
            PreparedStatement st1 = con.prepareStatement("Select * from booking");
            ResultSet rs = st1.executeQuery();

            while (rs.next()) {
                m.addRow(new Object[]{

                        rs.getString(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7)
                });
            }


            t.setBackground(Color.white);

            t.setModel(m);
            JScrollPane pane = new JScrollPane(t);
            pane.setBounds(100, 230, 1000, 500);
            pane.setBackground(adFrame.getBackground());
            sort_bk.add(status);
            sort_bk.add(fd);
            sort_bk.add(tf_fd);
            sort_bk.add(sub);
            sort_bk.add(ap);
            sort_bk.add(r);
            sort_bk.add(nm);
            sort_bk.add(tf_nm);
            sort_bk.setLayout(null);
            adFrame.add(sort_bk);
            adFrame.add(pane);


        }
        catch(Exception e){
            System.out.println(e);
        }

        sub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ap.isSelected()){
                    addAP(tf_nm,tf_fd);
                }

                else if(r.isSelected()){
                    addrej(tf_nm,tf_fd);
                }
            }
        });
    }

    void addAP(JTextField tf_nm,JTextField tf_fd){

        try{
            Connection con = DriverManager.getConnection("sql12.freemysqlhosting.net/sql12358156", "sql12358156", "LrfJMIu8r6");
            CallableStatement stmt = con.prepareCall("{ call upSTATUS(?,?) }");


           stmt.setString(1, tf_nm.getText());
            stmt.setString(2, tf_fd.getText());
            rowinserted = stmt.executeUpdate();
            dilog(11);


        }catch (Exception e){
        dilog(12);
            System.out.println(e);

        }
    }
    void addrej(JTextField tf_nm,JTextField tf_fd){
        try{
            Connection con = DriverManager.getConnection("sql12.freemysqlhosting.net/sql12358156", "sql12358156", "LrfJMIu8r6");
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useSSL=true", "root", "Govinda@20");
            CallableStatement stmt = con.prepareCall("{ call upSTATUS_REJ(?,?) }");


            stmt.setString(1, tf_nm.getText());
            stmt.setString(2, tf_fd.getText());
            rowinserted = stmt.executeUpdate();
            dilog(10);


        }catch (Exception e){
            dilog(12);
            System.out.println(e);

        }


    }

///-------Display Approved Bookings---
    void aprBookings(){
        /// display table where status = approved
        String []col={"Name","Person(s)","Tables","Food","Quantity","Date And Time","Status"};
       DefaultTableModel m1 = new DefaultTableModel();
        m1.setColumnIdentifiers(col);

        JTable t = new JTable();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)t.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        try{
            Connection con = DriverManager.getConnection("sql12.freemysqlhosting.net/sql12358156", "sql12358156", "LrfJMIu8r6");
            PreparedStatement st1 = con.prepareStatement("Select * from booking WHERE Status='Approved'");
            ResultSet rs = st1.executeQuery();

            while(rs.next()){
                m1.addRow(new Object[]{

                        rs.getString(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7)
                });
            }


        }
        catch(Exception e){
            System.out.println(e);
        }

        t.setBackground(Color.white);

        t.setModel(m1);
        JScrollPane pane = new JScrollPane(t);
        pane.setBounds(100, 230,1000, 500);
        pane.setBackground(adFrame.getBackground());
        adFrame.add(pane);
    }

///-------Display Rejcted Bookings---
    void rejBookings(){
        /// display table where status = rejected
        String []col={"Name","Person(s)","Tables","Food","Quantity","Date And Time","Status"};
        DefaultTableModel m1 = new DefaultTableModel();
        m1.setColumnIdentifiers(col);

        JTable t = new JTable();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)t.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        try{
            Connection con = DriverManager.getConnection("sql12.freemysqlhosting.net/sql12358156", "sql12358156", "LrfJMIu8r6");
            PreparedStatement st1 = con.prepareStatement("Select * from booking WHERE Status='Rejected' ");
            ResultSet rs = st1.executeQuery();

            while(rs.next()){
                m1.addRow(new Object[]{

                        rs.getString(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7)
                });
            }


        }
        catch(Exception e){
            System.out.println(e);
        }

        t.setBackground(Color.white);

        t.setModel(m1);
        JScrollPane pane = new JScrollPane(t);
        pane.setBounds(100, 230,1000, 500);
        pane.setBackground(adFrame.getBackground());
        adFrame.add(pane);
    }

void pendBooking(){

   /// display table where status = pending
    String []col={"Name","Person(s)","Tables","Food","Quantity","Date And Time","Status"};
    DefaultTableModel m1 = new DefaultTableModel();
    m1.setColumnIdentifiers(col);

    JTable t = new JTable();
    DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)t.getTableHeader().getDefaultRenderer();
    renderer.setHorizontalAlignment(SwingConstants.CENTER);

    try{
        Connection con = DriverManager.getConnection("sql12.freemysqlhosting.net/sql12358156", "sql12358156", "LrfJMIu8r6");
        PreparedStatement st1 = con.prepareStatement("Select * from booking WHERE Status='Pending'");
        ResultSet rs = st1.executeQuery();

        while(rs.next()){
            m1.addRow(new Object[]{

                    rs.getString(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getString(7)
            });
        }


    }
    catch(Exception e){
        System.out.println(e);
    }

    t.setBackground(Color.white);

    t.setModel(m1);
    JScrollPane pane = new JScrollPane(t);
    pane.setBounds(100, 230,1000, 500);
    pane.setBackground(adFrame.getBackground());
    adFrame.add(pane);

}

//----Displaying customers---------

    void custs(){


        String [] col = {"Name","Date","Payment"};
        DefaultTableModel mod = new DefaultTableModel();
        mod.setColumnIdentifiers(col);
        JTable tab = new JTable();
        DefaultTableCellRenderer renderer1 = (DefaultTableCellRenderer)tab.getTableHeader().getDefaultRenderer();
        renderer1.setHorizontalAlignment(SwingConstants.CENTER);

        try{
            Connection con = DriverManager.getConnection("sql12.freemysqlhosting.net/sql12358156", "sql12358156", "LrfJMIu8r6");
            PreparedStatement st1 = con.prepareStatement("Select * from billing");
            ResultSet rs = st1.executeQuery();

            while(rs.next()){
                mod.addRow(new Object[]{

                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3)

                });
            }


        }
        catch(Exception e){
            System.out.println(e);
        }

        tab.setModel(mod);
        JScrollPane pane = new JScrollPane(tab);
        pane.setBounds(100, 230,1000, 500);
        pane.setBackground(adFrame.getBackground());
        adFrame.add(pane);

    }

    void menucard(){

        String [] col = {"Food","Quantity","Price"};
       DefaultTableModel mod = new DefaultTableModel();
        mod.setColumnIdentifiers(col);
       JTable tab = new JTable();



        try{
            Connection con = DriverManager.getConnection("sql12.freemysqlhosting.net/sql12358156", "sql12358156", "LrfJMIu8r6");
            PreparedStatement st1 = con.prepareStatement("Select * from menucard");
            ResultSet rs = st1.executeQuery();

            while(rs.next()){
                mod.addRow(new Object[]{

                        rs.getString(1),
                        rs.getInt(2),
                        rs.getInt(3)

                });
            }


        }
        catch(Exception e){
            System.out.println(e);
        }

        tab.setModel(mod);
        JScrollPane  pane = new JScrollPane(tab);
        pane.setBounds(100, 230,1000, 500);
        pane.setBackground(adFrame.getBackground());

       JPanel pan2 = new JPanel();

        JLabel ndish = new JLabel("Add new Dish");
        JLabel dnm = new JLabel("Name");
        JLabel quant = new JLabel("Quantity");
        JLabel prc = new JLabel("Price");
        JButton btndish = new JButton("Add Dish");
        JTextField dnm_tf = new JTextField();
        JTextField prc_tf = new JTextField();
        JTextField quant_tf = new JTextField();



        ndish.setBounds(1200,200,100,20);
        pan2.setBounds(1220,230,220,200);
        ndish.setBounds(10,10,100,20);
        dnm.setBounds(10,40,100,20);
        quant.setBounds(10,70,100,20);
        prc.setBounds(10,100,100,20);

        dnm_tf.setBounds(80,45,100,20);
        quant_tf.setBounds(80,75,100,20);
        prc_tf.setBounds(80,105,100,20);
        btndish.setBounds(50,140,100,20);


        pan2.add(quant);
        pan2.add(quant_tf);
        pan2.add(btndish);
        pan2.add(dnm);
        pan2.add(ndish);
        pan2.add(prc);
        pan2.add(dnm_tf);
        pan2.add(prc_tf);
        pan2.setVisible(true);
        pan2.setLayout(null);

        adFrame.add(pane);
        adFrame.add(pan2);


        btndish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addish(dnm_tf,prc_tf,quant_tf);
            }
        });




    }


void addish(JTextField dnm_tf, JTextField prc_tf, JTextField quant_tf){

try{
    Connection con = DriverManager.getConnection("sql12.freemysqlhosting.net/sql12358156", "sql12358156", "LrfJMIu8r6");
    CallableStatement stmt = con.prepareCall("{ call addish(?,?,?) }");


    stmt.setString(1, dnm_tf.getText());
    stmt.setInt(2,Integer.parseInt(quant_tf.getText()));
    stmt.setInt(3, Integer.parseInt(prc_tf.getText()));

    rowinserted = stmt.executeUpdate();
    dilog(8);

}catch(Exception e){

    System.out.println(e);
    dilog(4);
}


}


//--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    ////-------------------USER'S DASHBOARD-----------------
//--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+


    void for_user(){
        log.dispose();
        JFrame usFrame = new JFrame("User"+" "+n_tf.getText());
        usFrame.getContentPane().setBackground(Color.white);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        usFrame.setSize(screenSize.width, screenSize.height);

        JPanel bookPane = new JPanel();
        bookPane.setBounds(1000,230, 400,500);

        JPanel usbackPane = new JPanel();
        JLabel name = new JLabel("Name:");
        JTextField usName= new JTextField();
        usName.setText(n_tf.getText());
        usName.setEditable(false);
        usName.setBackground(Color.WHITE);
        JButton menu = new JButton("Menucard");
        JButton logout = new JButton("Logout");
        JButton btmyBook = new JButton("Your Bookings");
        JLabel tab_count = new JLabel("Total Tables"); JTextField tf_tab_count = new JTextField("100");
        JLabel avail_count = new JLabel("Availabe");    JTextField tf_avail_count = new JTextField();

        String [] col = {"Food","Quantity","Price"};
        DefaultTableModel mod = new DefaultTableModel();
        mod.setColumnIdentifiers(col);
        JTable tab = new JTable();

        name.setFont(new Font("Courier", Font.ITALIC, 15));
        tab_count.setFont(new Font("Courier", Font.ITALIC, 15));
        avail_count.setFont(new Font("Courier", Font.ITALIC, 15));


        try{
            Connection con = DriverManager.getConnection("sql12.freemysqlhosting.net/sql12358156", "sql12358156", "LrfJMIu8r6");
            CallableStatement cstmt = con.prepareCall("{call tab(?)}");

            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.executeQuery();
            a = cstmt.getInt(1);


        }catch (Exception e){

            System.out.println(e);

        }

        try{
            Connection con = DriverManager.getConnection("sql12.freemysqlhosting.net/sql12358156", "sql12358156", "LrfJMIu8r6");
            PreparedStatement st1 = con.prepareStatement("Select * from menucard");
            ResultSet rs = st1.executeQuery();

            while(rs.next()){
                mod.addRow(new Object[]{

                        rs.getString(1),
                        rs.getInt(2),
                        rs.getInt(3)

                });
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

        tab.setModel(mod);
        JScrollPane  tabpane = new JScrollPane(tab);
        tabpane.setBounds(100, 230,800, 500);
        //tabpane.setBackground(usFrame.getBackground());

        usbackPane.setBounds(100,5,1350,200);

        tab_count.setBounds(350,40,100,20);      tf_tab_count.setBounds(450,40,50,20);
        avail_count.setBounds(350,80,100,20);    tf_avail_count.setBounds(450,80,50,20);
        tf_avail_count.setBackground(Color.WHITE);tf_tab_count.setBackground(Color.WHITE);

        name.setBounds(110,40,50,20);
        usName.setBounds(170,40,150,20);
        logout.setBounds(1200,40,80,20);

        btmyBook.setBounds(110,120,150,20); menu.setBounds(270,120,100,20);

        tf_tab_count.setEditable(false);
        tf_avail_count.setEditable(false);

        logout.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logout.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                logout.setBackground(UIManager.getColor("control"));
            }
        });


    int b=100-a;
    tf_avail_count.setText(Integer.toString(b));
    logout.setForeground(Color.RED);
        usbackPane.add(tab_count);
        usbackPane.add(avail_count);
        usbackPane.add(tf_tab_count);
        usbackPane.add(tf_avail_count);
        usbackPane.add(btmyBook);
        usbackPane.add(name);
        usbackPane.add(usName);
        usbackPane.add(logout);
        usbackPane.add(menu);

        usbackPane.setLayout(null);

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            usFrame.dispose();
            main_dial();

            }
        });

        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                usFrame.add(tabpane);
            }
        });


        JLabel heading = new JLabel("Order Booking");
        JLabel dish = new JLabel("Menu");
        JLabel quant = new JLabel("Quantity");
        JLabel ppl = new JLabel("Persons");
        JLabel Datetime = new JLabel("Date Time");
        JLabel table = new JLabel("Table");

        heading.setFont(new Font("Courier", Font.BOLD, 15));
        heading.setFont(new Font("Courier", Font.CENTER_BASELINE, 15));

        dish.setFont(new Font("Courier", Font.ITALIC, 15));
        quant.setFont(new Font("Courier", Font.ITALIC, 15));
        ppl.setFont(new Font("Courier", Font.ITALIC, 15));
        Datetime.setFont(new Font("Courier", Font.ITALIC, 15));
        table.setFont(new Font("Courier", Font.ITALIC, 15));

        JTextField tf_food = new JTextField();
        JTextField tf_quant = new JTextField();
        JTextField tf_ppl = new JTextField();
        JTextField tf_datetime = new JTextField();
        JTextField tf_tabel = new JTextField();

        JButton book = new JButton("Book");
        book.setForeground(Color.blue);


        heading.setBounds(120,20,120,50);
        dish.setBounds(60,80,40,20);
        quant.setBounds(60,120,100,20);
        ppl.setBounds(60,160,80,20);
        Datetime.setBounds(60,200,150,20);
        table.setBounds(60,240,40,20);
        book.setBounds(180,280,100,20);

        tf_food.setBounds(160,80,150,20);
        tf_quant.setBounds(160,120,150,20);
        tf_ppl.setBounds(160,160,150,20);
        tf_datetime.setBounds(160,200,150,20);
        tf_tabel.setBounds(160,240,150,20);


        bookPane.add(quant);
        bookPane.add(tf_quant);
        bookPane.add(book);
        bookPane.add(tf_food);
        bookPane.add(tf_ppl);
        bookPane.add(tf_tabel);
        bookPane.add(tf_datetime);

        bookPane.add(heading);
        bookPane.add(dish);
        bookPane.add(ppl);
        bookPane.add(Datetime);
        bookPane.add(table);
        bookPane.setLayout(null);

        usFrame.add(bookPane);
        usFrame.add(usbackPane);
        usFrame.setLayout(null);
        usFrame.setVisible(true);

        book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirm_window(tf_food,tf_quant,tf_ppl,tf_tabel,tf_datetime);
            }
        });

        btmyBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myBook(usFrame);
            }
        });

    }
    void confirm_window(JTextField tf_food, JTextField tf_quant,JTextField tf_ppl, JTextField tf_tabel,JTextField tf_datetime){

        JDialog confirm_dial = new JDialog();
        confirm_dial.setBounds(570, 170, 400, 500);

        JLabel nm = new JLabel(u_tf.getText());
        JLabel nmtext = new JLabel("Name:");
        JLabel dish = new JLabel("Food:");
        JLabel quant = new JLabel("Quantity:");
        JLabel ppl = new JLabel("People:");
        JLabel Datetime = new JLabel("Date And Time:");
        JLabel table = new JLabel("Table:");
        JButton bt_confirm = new JButton("Confirm");

        JLabel tf_food1 = new JLabel(tf_food.getText());
        JLabel tf_quant1 = new JLabel(tf_quant.getText());
        JLabel tf_ppl1 = new JLabel(tf_ppl.getText());
        JLabel tf_datetime1 = new JLabel(tf_datetime.getText());
        JLabel tf_tabel1 = new JLabel(tf_tabel.getText());
        JLabel bill = new JLabel("Bill:");
        JLabel pay = new JLabel();


        nmtext.setBounds(80,60,80,20);          nm.setBounds(150,60,80,20);
        dish.setBounds(80,100,80,20);           tf_food1.setBounds(150,100,80,20);
        quant.setBounds(80,140,80,20);          tf_quant1.setBounds(150,140,80,20);
        ppl.setBounds(80,180,80,20);            tf_ppl1.setBounds(150,180,80,20);
        table.setBounds(80,220,80,20);          tf_tabel1.setBounds(150,220,80,20);
        Datetime.setBounds(80,260,100,20);      tf_datetime1.setBounds(170,260,100,20);
        bill.setBounds(80,300,100,20);          pay.setBounds(170,300,80,20);
        bt_confirm.setBounds(80,340,100,20);


        nm.setFont(new Font("Courier", Font.ITALIC, 15));
        nmtext.setFont(new Font("Courier", Font.ITALIC, 15));
        dish.setFont(new Font("Courier", Font.ITALIC, 15));
        quant.setFont(new Font("Courier", Font.ITALIC, 15));
        ppl.setFont(new Font("Courier", Font.ITALIC, 15));
        Datetime.setFont(new Font("Courier", Font.ITALIC, 15));
        table.setFont(new Font("Courier", Font.ITALIC, 15));


        try{
            Connection con = DriverManager.getConnection("sql12.freemysqlhosting.net/sql12358156", "sql12358156", "LrfJMIu8r6");
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useSSL=true", "root", "Govinda@20");
            PreparedStatement st1 = con.prepareStatement("Select * from menucard");
            CallableStatement cstmt = con.prepareCall("{call bill(?,?)}");

            cstmt.setString(1,tf_food1.getText());

            cstmt.registerOutParameter(2, Types.INTEGER);

            cstmt.executeUpdate();

            f_price = cstmt.getInt(2);

        }catch(Exception e){

            System.out.println(e);
        }


        float bl = f_price*Integer.parseInt(tf_quant1.getText());
        pay.setText(Float.toString(bl));

        confirm_dial.add(bt_confirm);
        confirm_dial.add(pay);
        confirm_dial.add(bill);

        confirm_dial.add(Datetime);
        confirm_dial.add(tf_datetime1);

        confirm_dial.add(dish);
        confirm_dial.add(tf_food1);

        confirm_dial.add(quant);
        confirm_dial.add(tf_quant1);

        confirm_dial.add(ppl);
        confirm_dial.add(tf_ppl1);

        confirm_dial.add(table);
        confirm_dial.add(tf_tabel1);

        confirm_dial.add(nmtext);
        confirm_dial.add(nm);

        confirm_dial.setLayout(null);
        confirm_dial.setVisible(true);
        bt_confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paybill(bl,u_tf,tf_datetime1,tf_food1,tf_ppl1,tf_quant1,tf_tabel1);
            }
        });

    }



///bill payment
    void paybill(float bl, JTextField u_tf, JLabel date,JLabel tf_food1,JLabel tf_ppl1,JLabel tf_quant1,JLabel tf_tabel1){


        try{
            Connection con = DriverManager.getConnection("sql12.freemysqlhosting.net/sql12358156", "sql12358156", "LrfJMIu8r6");
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useSSL=true", "root", "Govinda@20");

            CallableStatement stmt = con.prepareCall("{ call PayBill(?,?,?) }");


            stmt.setString(1, u_tf.getText());
            stmt.setString(2, date.getText());
            stmt.setFloat(3, bl);

            rowinserted = stmt.executeUpdate();
            flagbk=1;


        }catch (Exception e){
            dilog(4);

            System.out.println("1"+e);


        }
        try {
            Connection con = DriverManager.getConnection("sql12.freemysqlhosting.net/sql12358156", "sql12358156", "LrfJMIu8r6");
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useSSL=true", "root", "Govinda@20");
            PreparedStatement stmt1 = con.prepareStatement("insert into booking(Name, People, Tables, Food, Quantity, Date_Time) values(?, ?, ?, ?, ?, ?)");

            stmt1.setString(1,u_tf.getText());
            stmt1.setInt(2,Integer.parseInt(tf_ppl1.getText()));
            stmt1.setInt(3,Integer.parseInt(tf_tabel1.getText()));
            stmt1.setString(4,tf_food1.getText());
            stmt1.setInt(5,Integer.parseInt(tf_quant1.getText()));
            stmt1.setString(6,date.getText());

            rowinserted = stmt1.executeUpdate();
            if(flagbk==1)
            {dilog(9);}


        }catch (Exception e){
            dilog(4);
            System.out.println("2"+e);


        }



        }

    void myBook(JFrame usFrame){


        String []col={"Name","People","Tables","Food","Quantity","Date And Time","Status"};
       DefaultTableModel m = new DefaultTableModel();
        m.setColumnIdentifiers(col);
        JTable t = new JTable();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)t.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        try{
            Connection con = DriverManager.getConnection("sql12.freemysqlhosting.net/sql12358156", "sql12358156", "LrfJMIu8r6");
            ///Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useSSL=true", "root", "Govinda@20");

            PreparedStatement st1 = con.prepareStatement("Select * from booking");
            ResultSet rs = st1.executeQuery();

            while(rs.next()){
                m.addRow(new Object[]{

                        rs.getString(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7)
                });
            }


        }
        catch(Exception e){
            System.out.println(e);
        }

        t.setBackground(Color.white);

        t.setModel(m);
        JScrollPane pane = new JScrollPane(t);
        pane.setBounds(100, 230,800, 500);
       // pane.setBackground(usFrame.getBackground());
        usFrame.add(pane);


    }

//--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
//--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+

    ////-------------------Main Function-----------------

    public static void main(String args[]){
  hotel h = new hotel();
  h.main_dial();

       submit.addMouseListener(new java.awt.event.MouseAdapter(){
           public void mouseEntered(java.awt.event.MouseEvent evt) {
               submit.setBackground(Color.WHITE);
           }

           public void mouseExited(java.awt.event.MouseEvent evt) {
               submit.setBackground(UIManager.getColor("control"));
           }
       });

       new_user.addMouseListener(new java.awt.event.MouseAdapter(){
           public void mouseEntered(java.awt.event.MouseEvent evt) {
               new_user.setBackground(Color.WHITE);
           }

           public void mouseExited(java.awt.event.MouseEvent evt) {
               new_user.setBackground(UIManager.getColor("control"));
           }
       });

   }

//--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    ////-------------------Registration window-----------------
//--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+

    void for_new(){
        log1 = new JDialog();
        //log1.setBackground(Color.WHITE);
        log1.setBounds(570, 170, 400, 500);


        ///-----BUTTONS----------------->>>
        ButtonGroup g = new ButtonGroup();
        JButton back = new JButton("Back");
        submit1 = new JButton("Register");
        JRadioButton  rb21 = new JRadioButton("User");
        JRadioButton rb1 = new JRadioButton("Admin");
        g.add(rb1);g.add(rb21);

        ////-------LABELS-------------------
        JLabel heading = new JLabel("New Registration");
        JLabel name_l = new JLabel("Name");
        JLabel u_labelr = new JLabel("Username");
        JLabel p_labelr = new JLabel("Password");

        name_l.setFont(new Font("TimesRoman", Font.BOLD, 15));
        u_labelr.setFont(new Font("Courier", Font.BOLD, 15));
        p_labelr.setFont(new Font("Courier", Font.BOLD, 15));
        heading.setFont(new Font("Courier", Font.BOLD, 20));

        ///---------TEXT FIELDS-------------
       JTextField Name_tf = new JTextField();
        JTextField u_tfr = new JTextField();
        JTextField p_tfr = new JTextField();

        ///-----SETTING BOUNDS-------------
        back.setBounds(20, 350, 70, 25);
        heading.setBounds(120, 30, 200, 20);

        name_l.setBounds(80, 100, 100, 20);      Name_tf.setBounds(160,100, 150, 20);
        u_labelr.setBounds(80, 130, 100, 20);   u_tfr.setBounds(160,130, 150, 20);
        p_labelr.setBounds(80, 160, 100, 20);   p_tfr.setBounds(160,160, 150, 20);
        rb1.setBounds(80,185, 100, 30);         rb21.setBounds(200,185, 100, 30);
        submit1.setBounds(80,215, 100, 20);

        submit1.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submit1.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                submit1.setBackground(UIManager.getColor("control"));
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log1.dispose();
                main_dial();
            }
        });



        submit1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(rb1.isSelected()){
                    insert_admin(Name_tf,u_tfr,p_tfr);

                }
                else if(rb21.isSelected()){
                    insert_user(Name_tf,u_tfr,p_tfr);
                }
            }
        });


        ///-----Register DialogBox Components----------
        log1.add(rb1);
        log1.add(rb21);
        log1.add(submit1);
        log1.add(p_tfr);
        log1.add(p_labelr);
        log1.add(u_tfr);
        log1.add(u_labelr);
        log1.add(Name_tf);
        log1.add(name_l);
        log1.add(heading);
        log1.add(back);
        log1.setLayout(null);
        log1.setVisible(true);

    }


//--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
                            ///Registration Function ------->>>>
//--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+

    void insert_admin(JTextField Name_tf,JTextField u_tfr,JTextField p_tfr ){
       int flag_a = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("sql12.freemysqlhosting.net/sql12358156", "sql12358156", "LrfJMIu8r6");
           // Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useSSL=true", "root", "Govinda@20");
            PreparedStatement st = con.prepareStatement("insert into admin(Name, Username, Password) values(?, ?, ?)");
            PreparedStatement st2 = con.prepareStatement("SELECT * FROM admin");
            ResultSet rs = st2.executeQuery();


            if(Name_tf.getText().isEmpty() || u_tfr.getText().isEmpty() || p_tfr.getText().isEmpty()){
                flag_a = 1;
                System.out.println("Empty");

            }



            else if(flag_u!=1) {
                while (rs.next()) {

                    if ((u_tfr.getText()).equals(rs.getString(2))  || (p_tfr.getText()).equals(rs.getString(3)) ) {
                        flag_a = 3;

                    }
                }
            }



             if(flag_a==0) {

                st.setString(1, Name_tf.getText());
                st.setString(2, u_tfr.getText());
                st.setString(3, p_tfr.getText());
                rowinserted = st.executeUpdate();
                flag_a = 2;
            }
            System.out.println(flag_a);

            if(flag_a==2){
                ///-----success msg
                dilog(6);

            }

            else if(flag_a==1){
                ///-----empty msg
                dilog(1);

            }

            else {
                ///-----invalid
               dilog(7);

            }

        }catch (Exception e){
            System.out.println(e);
        }

    }

    void insert_user(JTextField Name_tf,JTextField u_tfr,JTextField p_tfr ){


        int flag_u = 0;

        System.out.println(Name_tf.getText());
        System.out.println(u_tfr.getText());
        System.out.println(p_tfr.getText());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("sql12.freemysqlhosting.net/sql12358156", "sql12358156", "LrfJMIu8r6");
           // Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useSSL = true", "root", "Govinda@20");
            PreparedStatement st1 = con.prepareStatement("insert into customer(Name,Username, Password) values(?, ?, ?)");
            PreparedStatement st2 = con.prepareStatement("select * from customer");
            ResultSet rs = st2.executeQuery();

            if(Name_tf.getText().isEmpty() || u_tfr.getText().isEmpty() || p_tfr.getText().isEmpty()){
                flag_u = 1;
                System.out.println("Empty");
            }

            else if(flag_u!=1) {

                 System.out.println(flag_u);
                     while (rs.next()) {

                         if ((u_tfr.getText()).equals(rs.getString(2)) ||
                             (p_tfr.getText()).equals(rs.getString(3)) ||
                             (Name_tf.getText().equals(rs.getString(1)))) {
                               flag_u = 3;
                         }

                     }
            }


              if(flag_u==0) {

                 st1.setString(1,Name_tf.getText());
                 st1.setString(2,u_tfr.getText());
                 st1.setString(3,p_tfr.getText());
                 rowinserted = st1.executeUpdate();
                System.out.println("Success");
                flag_u = 2;
            }

            System.out.println(flag_u);

            //////////////Interrupts--->>>>>

            if(flag_u==2){
                //success message
                dilog(3);
            }

            else if(flag_u==1)
            {
                ///empty text
                dilog(1);
            }
            else {
                ///invalid message
                dilog(4);

            }



        }catch (Exception e1){
            System.out.println(e1);
        }


    }



//--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    ////----------All interrupt Dialog Box------------
//--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+

    void dilog(int flag_l) {
        JDialog interrupt = new JDialog();
        interrupt.setBounds(570, 170, 400, 200);


        JLabel et_msg = new JLabel("Text Is Empty..");
        JLabel regsuc = new JLabel("Registered As User Successfully !!!");
        JLabel invalid = new JLabel("Invalid Credentials...");
        JLabel a_regsuc = new JLabel("Registered As Admin Successfully !!!");
        JLabel a_invalid = new JLabel("Invalid Credentials...");
        JLabel dadded = new JLabel("Added Successfully");
        JLabel orderd = new JLabel("Booked Successfully");
        JLabel rejStatus = new JLabel("Status Changed To Rejected");
        JLabel aprStatus = new JLabel("Status Changed To Approved");
        JLabel erorStatus = new JLabel("Error Check Name or Food ");

        regsuc.setBounds(50, 30, 300, 50);
        invalid.setBounds(130, 50, 300, 50);
        et_msg.setBounds(150, 30, 300, 50);
        a_regsuc.setBounds(50, 30, 300, 50);
        a_invalid.setBounds(130, 50, 300, 50);
        dadded.setBounds(130, 50, 300, 50);
        orderd.setBounds(130, 50, 300, 50);
        rejStatus.setBounds(130, 50, 300, 50);
        aprStatus.setBounds(130, 50, 300, 50);
        erorStatus.setBounds(130, 50, 300, 50);

        regsuc.setFont(new Font("Serif", Font.BOLD, 15));
        invalid.setFont(new Font("Serif", Font.BOLD, 15));
        et_msg.setFont(new Font("Serif", Font.BOLD, 15));
        a_regsuc.setFont(new Font("Serif", Font.BOLD, 15));
        a_invalid.setFont(new Font("Serif", Font.BOLD, 15));
        dadded.setFont(new Font("Serif", Font.BOLD, 15));
        orderd.setFont(new Font("Serif", Font.BOLD, 15));
        rejStatus.setFont(new Font("Serif", Font.BOLD, 15));
        aprStatus.setFont(new Font("Serif", Font.BOLD, 15));
        erorStatus.setFont(new Font("Serif", Font.BOLD, 15));
        ////----logIN Interrupts-----
        if (flag_l == 1) {
            interrupt.add(et_msg);
        }

        ////----Register User Interrupts-----

        else if (flag_l == 3) {
            interrupt.add(regsuc);
        }

        else if (flag_l == 4) {
            interrupt.add(invalid);
        }
///---------------Register Admin Interrupts-------------
        else if (flag_l == 6) {
            interrupt.add(a_regsuc);
        }
        else if (flag_l == 7) {
            interrupt.add(a_invalid);
        }
        else if(flag_l==8){

            interrupt.add(dadded);
        }

        else if(flag_l==9){

            interrupt.add(orderd);
        }
        else if(flag_l==10){

            interrupt.add(rejStatus);
        }
        else if(flag_l==11){

            interrupt.add(aprStatus);
        }
        else if(flag_l==12){

            interrupt.add(aprStatus);
        }
            interrupt.setLayout(null);
            interrupt.setVisible(true);
    }
}
