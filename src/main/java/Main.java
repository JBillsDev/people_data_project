import GUI.JFrameWindow;
import org.tinylog.Logger;

public class Main {

    public static void main(String[] args) {
        final String VERSION = "v0.0.0a";
        final String TITLE = "People Data Project " + VERSION;

        Logger.info(TITLE);

        new JFrameWindow(TITLE, 480, 640);
    }

}
