import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

class ExistingUser extends JFrame{

    public String name;
    public String gender;
    public String username;
    public String password;
    public String contact;
    public String prn;
    ExistingUser(){
JLabel jl1 = new JLabel("Login Form");
        jl1.setBounds(300, 50, 200, 50);
        jl1.setFont(new Font("Arial", Font.PLAIN, 34)); // Corrected "Aerial" to "Arial"
        add(jl1);

        JLabel jl2 = new JLabel("Username:");
        jl2.setBounds(200, 150, 200, 50);
        add(jl2);

        JTextField jt1 = new JTextField("");
        jt1.setBounds(350, 150, 200, 50);
        add(jt1);

        JLabel jl3 = new JLabel("Password:");
        jl3.setBounds(200, 250, 200, 50);
        add(jl3);

        JPasswordField jt2 = new JPasswordField();
        jt2.setBounds(350, 250, 200, 50);
        add(jt2);

        JButton jb1 = new JButton("Submit");
        jb1.setBounds(250, 400, 100, 50);
        add(jb1);

        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname = jt1.getText();
                String pass = new String(jt2.getPassword());

                try (Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/aliens?autoReconnect=true&useSSL=false", "root", "peace");
                     Statement statement = con.createStatement()) {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String sql = "SELECT * FROM db WHERE usrname = ? AND passwrd = ?";
                    try (PreparedStatement ps = con.prepareStatement(sql)) {
                        ps.setString(1, uname);
                        ps.setString(2, pass);
                        ResultSet rs = ps.executeQuery();

                        if (rs.next()) {
                            dispose();
                            name = rs.getString("name");
                            gender = rs.getString("gender");
                            username = rs.getString("usrname");
                            password = rs.getString("passwrd");
                            contact = rs.getString("contact");
                            prn = rs.getString("prn");
                            UserDetails userDetails = new UserDetails(name, gender, username, password, contact, prn);
                            userDetails.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(jb1, "Invalid Username or Password");
                        }
                    }
                } catch (Exception ae) {
                    System.out.println(ae.getMessage());
                }
            }
        });

        JButton jb2 = new JButton("Reset");
        jb2.setBounds(400, 400, 100, 50);
        add(jb2);

        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jt1.setText("");
                jt2.setText("");
            }
        });

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Login Form");
        setVisible(true);
    }
}
