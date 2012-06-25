import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EditorWindow extends JFrame {
    private JTabbedPane workArea;

    private class OpenFileAction extends AbstractAction {
        OpenFileAction() {
            putValue(Action.NAME, "Open file");
            putValue(Action.SHORT_DESCRIPTION, "Open file in editor");
        }

        public void actionPerformed(ActionEvent event) {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(EditorWindow.this);
            File chosen = chooser.getSelectedFile();


            String text = new String();
            String line;
            try (BufferedReader reader = new BufferedReader(new FileReader(chosen))) {
                while ((line = reader.readLine()) != null) {
                    text += line;
                }
            } catch (IOException e) {
            }

            TextField textField = new TextField(text);
            workArea.addTab(chosen.getName(), textField);
        }
    }

    private class CloseFileAction extends AbstractAction {
        CloseFileAction() {
            putValue(Action.NAME, "Close");
            putValue(Action.SHORT_DESCRIPTION, "Close file in editor");
        }

        public void actionPerformed(ActionEvent event) {
            workArea.remove(workArea.getSelectedIndex());
        }
    }

    private class ExitAction extends AbstractAction {
        ExitAction() {
            putValue(Action.NAME, "Exit");
            putValue(Action.SHORT_DESCRIPTION, "Exits the application");
        }

        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }

    public EditorWindow() {
        super("Hello, user");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setResizable(true);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new OpenFileAction());
        fileMenu.add(new CloseFileAction());
        fileMenu.addSeparator();
        fileMenu.add(new ExitAction());
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        Box leftPanel = Box.createVerticalBox();
        leftPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Button open = new Button("Open");
        open.setMaximumSize(new Dimension(100, 50));
        open.addActionListener(new OpenFileAction());

        Button close = new Button("Close");
        close.addActionListener(new CloseFileAction());
        close.setMaximumSize(new Dimension(100, 50));

        Button about = new Button("About");
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Hello, information about developers.");
            }
        });
        about.setMaximumSize(new Dimension(100, 50));

        Button exit = new Button("Exit");
        exit.addActionListener(new ExitAction());
        exit.setMaximumSize(new Dimension(100, 50));

        leftPanel.add(open);
        leftPanel.add(close);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(about);
        leftPanel.add(exit);
        getContentPane().add(leftPanel, BorderLayout.WEST);

        workArea = new JTabbedPane();
        workArea.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(workArea);
    }
}
