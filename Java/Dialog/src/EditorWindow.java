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
    private JTabbedPane editorBox;
    private Button close;
    AbstractAction openFileAction;
    AbstractAction closeFileAction;

    private class OpenFileAction extends AbstractAction {
        OpenFileAction() {
            putValue(Action.NAME, "Open");
            putValue(Action.SHORT_DESCRIPTION, "Open file");
        }

        public void actionPerformed(ActionEvent event) {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(EditorWindow.this);
            File chosen = chooser.getSelectedFile();
            if (chosen == null)
                return;


            String text = "";
            String line;
            try (BufferedReader reader = new BufferedReader(new FileReader(chosen))) {
                while ((line = reader.readLine()) != null) {
                    text += line + "\n";
                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(EditorWindow.this, "Read error.");
            }

            JTextArea textField = new JTextArea(text);
            editorBox.addTab(chosen.getName(), textField);

            closeFileAction.setEnabled(true);
            close.setEnabled(true);
        }
    }

    private class CloseFileAction extends AbstractAction {
        CloseFileAction() {
            putValue(Action.NAME, "Close");
            putValue(Action.SHORT_DESCRIPTION, "Close file");
        }

        public void actionPerformed(ActionEvent event) {
            editorBox.remove(editorBox.getSelectedIndex());
            if (editorBox.getTabCount() == 0) {
                closeFileAction.setEnabled(false);
                close.setEnabled(false);
            }

        }
    }

    private class ExitAction extends AbstractAction {
        ExitAction() {
            putValue(Action.NAME, "Exit");
            putValue(Action.SHORT_DESCRIPTION, "Exit");
        }

        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }

    public void setUserName(String name) {
        this.setTitle("Hello " + name);
    }
    
    public EditorWindow() {
        super("Hello");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setResizable(true);

        openFileAction = new OpenFileAction();
        closeFileAction = new CloseFileAction();

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(openFileAction);
        fileMenu.add(closeFileAction);
        fileMenu.addSeparator();
        fileMenu.add(new ExitAction());
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        Dimension btnDimension = new Dimension(100, 50);

        Box leftPanel = Box.createVerticalBox();
        leftPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Button open = new Button((String)openFileAction.getValue(Action.NAME));
        open.setMaximumSize(btnDimension);
        open.addActionListener(openFileAction);

        close = new Button((String)closeFileAction.getValue(Action.NAME));
        close.addActionListener(closeFileAction);
        close.setMaximumSize(btnDimension);
        closeFileAction.setEnabled(false);
        close.setEnabled(false);

        Button about = new Button("About");
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "About.");
            }
        });
        about.setMaximumSize(btnDimension);

        Button exit = new Button("Exit");
        exit.addActionListener(new ExitAction());
        exit.setMaximumSize(btnDimension);

        leftPanel.add(open);
        leftPanel.add(close);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(about);
        leftPanel.add(exit);
        getContentPane().add(leftPanel, BorderLayout.WEST);

        editorBox = new JTabbedPane();
        editorBox.setBorder(new EmptyBorder(4, 4, 4, 4));
        getContentPane().add(editorBox);
    }
}
