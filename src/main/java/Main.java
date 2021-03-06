import GUI.JFrameWindow;
import Utility.Config;
import org.tinylog.Logger;

final public class Main {

    public static void main(String[] args) {
        final String VERSION = "v1.0.3";
        final String TITLE = "People Data Project " + VERSION;

        Logger.info(TITLE);

        final var config = new Config();
        config.readConfig();

        new JFrameWindow(TITLE, config);
    }

}
