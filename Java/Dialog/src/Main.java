import java.awt.*;

public class Main {
    public static void main(String[] args) {
        LoginWindow loginWindow = new LoginWindow();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        loginWindow.setLocation(dim.width / 2, dim.height / 2);
        loginWindow.setVisible(true);
    }
}
