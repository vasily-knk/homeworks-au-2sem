import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame {
    private TextField loginText;
    private TextField passwordText;
    private JProgressBar pBar;


    public LoginWindow() {
        super("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Box box = Box.createHorizontalBox();
        JLabel label = new JLabel("", new ImageIcon("log.png"), SwingConstants.RIGHT);
        box.add(label);
        box.add(Box.createHorizontalStrut(4));
        JPanel inputs = new JPanel();

        inputs.setLayout(new GridLayout(2, 2, 4, 4));
        inputs.add(new Label("Login"));
        loginText = new TextField();
        loginText.setColumns(15);
        inputs.add(loginText);
        inputs.add(new Label("Password"));
        passwordText = new TextField();
        inputs.add(passwordText);
        box.add(inputs);
        box.setBorder(new EmptyBorder(4, 4, 4, 4));

        getContentPane().add(box);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        JPanel flowPanel = new JPanel(new FlowLayout());
        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Clear");
        pBar = new JProgressBar();

        flowPanel.add(registerButton);
        flowPanel.add(loginButton);
        flowPanel.add(cancelButton);
        panel.add(flowPanel);
        panel.add(pBar);

        getContentPane().add(panel, BorderLayout.SOUTH);
        //getContentPane().add(panel);
        pack();
        /*registerButton.addActionListener(new LoginAction());
        loginButton.addActionListener(new LoginAction());
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                loginText.setText("");
                passwordText.setText("");
            }
        });*/


    }

}