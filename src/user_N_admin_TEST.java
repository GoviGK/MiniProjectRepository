import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

class user_N_admin_TEST {
    hotel h = new hotel();

    @Test
    void insert_admin(JTextField Name_tf, JTextField u_tfr, JTextField p_tfr,int flag_u,int rowinserted) {

        int flag_a=0;
        System.out.println(Name_tf.getText());
        System.out.println(u_tfr.getText());
        System.out.println(p_tfr.getText());
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useSSL=true", "root", "root123");
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
                h.dilog(6);

            }

            else if(flag_a==1){
                ///-----empty msg
                h.dilog(1);

            }

            else {
                ///-----invalid
                h.dilog(7);

            }

        }catch (Exception e){
            System.out.println(e);
        }

    }

    @Test
    void insert_user(JTextField Name_tf,JTextField u_tfr,JTextField p_tfr,int rowinserted) {
        int flag_u=0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useSSL = true", "root", "root123");
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
                h.dilog(3);
            }

            else if(flag_u==1)
            {
                ///empty text
                h.dilog(1);
            }
            else {
                ///invalid message
                h.dilog(4);

            }



        }catch (Exception e1){
            System.out.println(e1);
        }
    }
}