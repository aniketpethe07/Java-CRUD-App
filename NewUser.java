import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class NewUser extends JFrame {
    NewUser(){
        JLabel jl1 = new JLabel("Registration Form:");
        jl1.setBounds(270, 0, 500, 100);
        add(jl1);
        jl1.setFont(new Font("Arial", Font.PLAIN, 34)); // Corrected "Aerial" to "Arial"

        JLabel jl2 = new JLabel("Name:");
        jl2.setBounds(50, 120, 200, 50);
        add(jl2);

        JTextField name = new JTextField();
        name.setBounds(150, 120, 200, 50);
        add(name);

        JLabel jl3 = new JLabel("Gender:");
        jl3.setBounds(420, 120, 200, 50);
        add(jl3);

        JRadioButton male = new JRadioButton("Male");
        male.setBounds(530, 120, 200, 50);
        add(male);
        JRadioButton female = new JRadioButton("Female");
        female.setBounds(530, 160, 200, 50);
        add(female);
        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);

        JLabel jl4 = new JLabel("Username:");
        jl4.setBounds(50, 220, 200, 50);
        add(jl4);

        JTextField username = new JTextField();
        username.setBounds(150, 220, 200, 50);
        add(username);

        JLabel jl5 = new JLabel("Password:");
        jl5.setBounds(420, 220, 200, 50);
        add(jl5);

        JPasswordField password = new JPasswordField();
        password.setBounds(530, 220, 200, 50);
        add(password);

        JLabel jl6 = new JLabel("Contact No:");
        jl6.setBounds(50, 320, 200, 50);
        add(jl6);

        JTextField contact = new JTextField();
        contact.setBounds(150, 320, 200, 50);
        add(contact);

        JLabel jl7 = new JLabel("PRN:");
        jl7.setBounds(420, 320, 200, 50);
        add(jl7);

        JTextField prn = new JTextField();
        prn.setBounds(530, 320, 200, 50);
        add(prn);

        JButton jb = new JButton("Submit");
        jb.setBounds(320, 420, 150, 50);
        add(jb);

        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/aliens?autoReconnect=true&useSSL=false", "root", "peace")) {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String sql = "INSERT INTO db (name, gender, usrname, passwrd, contact, prn) VALUES (?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement ps = con.prepareStatement(sql)) {
                        ps.setString(1, name.getText());
                        ps.setString(2, male.isSelected() ? male.getText() : female.getText());
                        ps.setString(3, username.getText());
                        ps.setString(4, new String(password.getPassword()));
                        ps.setString(5, contact.getText());
                        ps.setString(6, prn.getText());

                        int i = ps.executeUpdate();
                        JOptionPane.showMessageDialog(jb, i + " record(s) added successfully");
                        dispose();
                        ExistingUser existingUser = new ExistingUser();
                        existingUser.setVisible(true);
                    }
                } catch (Exception ae) {
                    System.out.println(ae.getMessage());
                }
            }
        });

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Registration Form");
        setVisible(true);
    }
}
