import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EditorWindow editorWindow = new EditorWindow();
        LoginWindow loginWindow = new LoginWindow(editorWindow);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        loginWindow.setLocation(dim.width / 2, dim.height / 2);
        loginWindow.setVisible(true);
    }
}
