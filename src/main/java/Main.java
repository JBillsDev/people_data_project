import GUI.JFrameWindow;
import Utility.Config;
import org.tinylog.Logger;

final public class Main {

    public static void main(String[] args) {
        final String VERSION = "v0.0.3";
        final String TITLE = "People Data Project " + VERSION;

        Logger.info(TITLE);

        var config = new Config();
        config.readConfig();

        new JFrameWindow(TITLE, config);
    }

}
