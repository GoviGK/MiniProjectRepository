import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    @org.junit.jupiter.api.Test
    void aprBookings(JFrame adFrame) {
        String []col={"Name","Person(s)","Tables","Food","Quantity","Date And Time","Status"};
        DefaultTableModel m1 = new DefaultTableModel();
        m1.setColumnIdentifiers(col);

        JTable t = new JTable();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)t.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        try{

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?useSSL=true", "root", "root123");
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
}