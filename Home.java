import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Home extends JFrame {
    Home(){
        JLabel jl = new JLabel("Homepage");
        jl.setBounds(300, 50, 200, 50);
        jl.setFont(new Font("Arial", Font.PLAIN, 34)); // Corrected "Aerial" to "Arial"
        add(jl);

        JButton jb1 = new JButton("New user");
        jb1.setBounds(180, 200, 150, 50);
        add(jb1);

        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                NewUser newUser = new NewUser();
                newUser.setVisible(true);
            }
        });

        JButton jb2 = new JButton("Existing user");
        jb2.setBounds(450, 200, 150, 50);
        add(jb2);

        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ExistingUser existingUser = new ExistingUser();
                existingUser.setVisible(true);
            }
        });

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Home Page");
        setVisible(true);
    }
}
