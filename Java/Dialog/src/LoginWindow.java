import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame {
    private TextField loginText;
    private TextField passwordText;
    private JProgressBar pBar;

    private JButton loginButton;
    private JButton registerButton;
    private JButton clearButton;



    public LoginWindow() {
        super("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Box box = Box.createHorizontalBox();
        JLabel label = new JLabel("", new ImageIcon("log.png"), SwingConstants.RIGHT);
        box.add(label);
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
        registerButton = new JButton("Register");
        loginButton = new JButton("Login");
        clearButton = new JButton("Clear");
        pBar = new JProgressBar();

        flowPanel.add(registerButton);
        flowPanel.add(loginButton);
        flowPanel.add(clearButton);
        panel.add(flowPanel);
        panel.add(pBar);

        getContentPane().add(panel, BorderLayout.SOUTH);
        //getContentPane().add(panel);
        pack();

        ActionListener loginAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disableButtons();
                new Thread(new Progress()).start();
            }
        };

        registerButton.addActionListener(loginAction);
        loginButton.addActionListener(loginAction);

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                loginText.setText("");
                passwordText.setText("");
            }
        });
    }

    private void disableButtons() {
        loginButton.setEnabled(false);
        registerButton.setEnabled(false);
    }
    
    private void enableButtons() {
        loginButton.setEnabled(true);
        registerButton.setEnabled(true);

    }

    class Progress implements Runnable {
        public void run() {
            for (int i = 0; i <= 100; i++) {
                try {
                    pBar.setValue(i);
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            enableButtons();
            JOptionPane.showMessageDialog(LoginWindow.this, "Hello!");

            JFrame editorWindow = new EditorWindow();
            editorWindow.setVisible(true);
            //LoginWindow.this.setVisible(false);
            /*editorWindow.setLocation(400, 400);
            editorWindow.setTitle("Hello, " + loginText.getText());
            editorWindow.setVisible(true);*/
        }
    }
}