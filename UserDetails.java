import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class UserDetails extends JFrame {

    UserDetails(String n, String g, String u, String pass, String c, String p){
        JLabel jl1 = new JLabel("User Details:");
        jl1.setBounds(300, 0, 500, 100);
        add(jl1);
        jl1.setFont(new Font("Arial", Font.PLAIN, 34)); // Corrected "Aerial" to "Arial"

        JLabel jl2 = new JLabel("Name:");
        jl2.setBounds(50, 120, 200, 50);
        add(jl2);

        JTextField name = new JTextField();
        name.setText(n);
        name.setBounds(150, 120, 200, 50);
        add(name);

        JLabel jl3 = new JLabel("Gender:");
        jl3.setBounds(420, 120, 200, 50);
        add(jl3);

        JTextField gender = new JTextField();
        gender.setText(g);
        gender.setBounds(530, 120, 200, 50);
        add(gender);

        JLabel jl4 = new JLabel("Username:");
        jl4.setBounds(50, 220, 200, 50);
        add(jl4);

        JTextField username = new JTextField();
        username.setText(u);
        username.setBounds(150, 220, 200, 50);
        add(username);

        JLabel jl5 = new JLabel("Password:");
        jl5.setBounds(420, 220, 200, 50);
        add(jl5);

        JPasswordField password = new JPasswordField();
        password.setText(pass);
        password.setBounds(530, 220, 200, 50);
        add(password);

        JLabel jl6 = new JLabel("Contact No:");
        jl6.setBounds(50, 320, 200, 50);
        add(jl6);

        JTextField contact = new JTextField();
        contact.setText(c);
        contact.setBounds(150, 320, 200, 50);
        add(contact);

        JLabel jl7 = new JLabel("PRN:");
        jl7.setBounds(420, 320, 200, 50);
        add(jl7);

        JTextField prn = new JTextField();
        prn.setText(p);
        prn.setBounds(530, 320, 200, 50);
        add(prn);

        JButton jb1 = new JButton("Update");
        jb1.setBounds(150, 420, 150, 50);
        add(jb1);

        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/aliens?autoReconnect=true&useSSL=false", "root", "peace")) {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String sql = "UPDATE db SET name = ?, gender = ?, usrname = ?, passwrd = ?, contact = ?, prn = ? WHERE usrname = ?";
                    try (PreparedStatement ps = con.prepareStatement(sql)) {
                        ps.setString(1, name.getText());
                        ps.setString(2, gender.getText());
                        ps.setString(3, username.getText());
                        ps.setString(4, new String(password.getPassword()));
                        ps.setString(5, contact.getText());
                        ps.setString(6, prn.getText());
                        ps.setString(7, username.getText());

                        int i = ps.executeUpdate();
                        JOptionPane.showMessageDialog(jb1, i + " record(s) updated successfully");
                        dispose();
                        Home homePage = new Home();
                        homePage.setVisible(true);
                    }
                } catch (Exception ae) {
                    System.out.println(ae.getMessage());
                }
            }
        });

        JButton jb2 = new JButton("Delete");
        jb2.setBounds(325, 420, 150, 50);
        add(jb2);

        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/aliens?autoReconnect=true&useSSL=false", "root", "peace")) {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String sql = "DELETE FROM db WHERE usrname = ?";
                    try (PreparedStatement ps = con.prepareStatement(sql)) {
                        ps.setString(1, username.getText());
                        int i = ps.executeUpdate();
                        JOptionPane.showMessageDialog(jb2, i + " record(s) deleted successfully");
                        dispose();
                        Home homePage = new Home();
                        homePage.setVisible(true);
                    }
                } catch (Exception ae) {
                    System.out.println(ae.getMessage());
                }
            }
        });

        JButton jb3 = new JButton("Home");
        jb3.setBounds(500, 420, 150, 50);
        add(jb3);

        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Home homePage = new Home();
                homePage.setVisible(true);
            }
        });

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("User Details");
        setVisible(true);
    }
}
