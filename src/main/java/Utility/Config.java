package Utility;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.tinylog.Logger;

import java.awt.*;
import java.io.*;

final public class Config {

    private final String CONFIG_FILE_NAME = "config.txt";

    private final String JSON_OBJECT_KEY_WINDOW_DIMENSIONS = "WINDOW_DIMENSIONS";
    private final String JSON_OBJECT_KEY_WINDOW_DIMENSIONS_WIDTH = "WINDOW_WIDTH";
    private final String JSON_OBJECT_KEY_WINDOW_DIMENSIONS_HEIGHT = "WINDOW_HEIGHT";
    private final String JSON_OBJECT_KEY_WINDOW_POSITION = "WINDOW_POSITION";
    private final String JSON_OBJECT_KEY_WINDOW_POSITION_X = "WINDOW_POSITION_X";
    private final String JSON_OBJECT_KEY_WINDOW_POSITION_Y = "WINDOW_POSITION_Y";

    private final JSONObject JSONObjectConfig;
    private final JSONObject JSONObjectConfigWindowDimensions;
    private final JSONObject JSONObjectConfigWindowPosition;

    public Config() {
        final int WINDOW_WIDTH_DEFAULT = 480;
        final int WINDOW_HEIGHT_DEFAULT = 358;

        // Instantiate JSON Objects
        this.JSONObjectConfig = new JSONObject();
        final var JSONObjectConfigWindow = new JSONObject();
        this.JSONObjectConfigWindowDimensions = new JSONObject();
        this.JSONObjectConfigWindowPosition = new JSONObject();

        // Set default window dimensions
        this.updateWindowDimensions(WINDOW_WIDTH_DEFAULT, WINDOW_HEIGHT_DEFAULT);

        // Set default window position to center of the screen
        Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
        final var windowPositionX = (screenDimensions.width / 2)  - (WINDOW_WIDTH_DEFAULT / 2);
        final var windowPositionY = (screenDimensions.height / 2) - (WINDOW_HEIGHT_DEFAULT / 2);
        this.updateWindowPosition(windowPositionX, windowPositionY);

        // Build JSON Object
        JSONObjectConfigWindow.put(this.JSON_OBJECT_KEY_WINDOW_DIMENSIONS, this.JSONObjectConfigWindowDimensions);
        JSONObjectConfigWindow.put(this.JSON_OBJECT_KEY_WINDOW_POSITION, this.JSONObjectConfigWindowPosition);
        this.JSONObjectConfig.put("WINDOW", JSONObjectConfigWindow);
    }

    public Rectangle getWindowRect() {
        return new Rectangle(this.JSONObjectConfigWindowPosition.getInt(this.JSON_OBJECT_KEY_WINDOW_POSITION_X),
                             this.JSONObjectConfigWindowPosition.getInt(this.JSON_OBJECT_KEY_WINDOW_POSITION_Y),
                             this.JSONObjectConfigWindowDimensions.getInt(this.JSON_OBJECT_KEY_WINDOW_DIMENSIONS_WIDTH),
                             this.JSONObjectConfigWindowDimensions.getInt(this.JSON_OBJECT_KEY_WINDOW_DIMENSIONS_HEIGHT));
    }

    public void readConfig() {
        final File configFile = new File(this.CONFIG_FILE_NAME);
        // Create the default config file if one does not exist, and maintain default config values
        if (!configFile.exists()) {
            this.writeConfig();
            return;
        }

        // Read JSON file and convert it to a single string to convert into JSON object
        final JSONTokener jsonTokenizer;
        try {
            final var bufferedReader = new BufferedReader(new FileReader(this.CONFIG_FILE_NAME));
            String bufferedReaderLineNext;
            StringBuilder builder = new StringBuilder();
            while ((bufferedReaderLineNext = bufferedReader.readLine()) != null) {
                builder.append(bufferedReaderLineNext);
            }

            jsonTokenizer = new JSONTokener(builder.toString());
            bufferedReader.close();
        } catch (IOException exception) {
            Logger.error(exception);

            // If reading or parsing the config file fails, overwrite with default configuration file
            this.writeConfig();
            return;
        }

        // Attempt to parse config file JSON
        try {
            final String JSON_OBJECT_KEY_WINDOW = "WINDOW";

            final var jsonObjectConfigFile = new JSONObject(jsonTokenizer);

            final var jsonObjectConfigFileWindow = (JSONObject) jsonObjectConfigFile.get(JSON_OBJECT_KEY_WINDOW);
            final var jsonObjectConfigFileWindowDimensions = (JSONObject) jsonObjectConfigFileWindow.get(this.JSON_OBJECT_KEY_WINDOW_DIMENSIONS);
            this.updateWindowDimensions(jsonObjectConfigFileWindowDimensions.getInt(this.JSON_OBJECT_KEY_WINDOW_DIMENSIONS_WIDTH),
                                        jsonObjectConfigFileWindowDimensions.getInt(this.JSON_OBJECT_KEY_WINDOW_DIMENSIONS_HEIGHT));

            final var jsonObjectConfigFileWindowPosition = (JSONObject) jsonObjectConfigFileWindow.get(this.JSON_OBJECT_KEY_WINDOW_POSITION);
            this.updateWindowPosition(jsonObjectConfigFileWindowPosition.getInt(this.JSON_OBJECT_KEY_WINDOW_POSITION_X),
                                      jsonObjectConfigFileWindowPosition.getInt(this.JSON_OBJECT_KEY_WINDOW_POSITION_Y));
        } catch (JSONException exception) {
            Logger.warn("failed to parse config file");
            Logger.error(exception);

            // Overwrite bad config file with new default config file
            this.writeConfig();
        }
    }

    public void updateWindowDimensions(int windowWidth, int windowHeight) {
        this.JSONObjectConfigWindowDimensions.put(this.JSON_OBJECT_KEY_WINDOW_DIMENSIONS_WIDTH, windowWidth);
        this.JSONObjectConfigWindowDimensions.put(this.JSON_OBJECT_KEY_WINDOW_DIMENSIONS_HEIGHT, windowHeight);
    }

    public void updateWindowPosition(int windowPositionX, int windowPositionY) {
        this.JSONObjectConfigWindowPosition.put(this.JSON_OBJECT_KEY_WINDOW_POSITION_X, windowPositionX);
        this.JSONObjectConfigWindowPosition.put(this.JSON_OBJECT_KEY_WINDOW_POSITION_Y, windowPositionY);
    }

    public void writeConfig() {
        try {
            final var configFile = new File(this.CONFIG_FILE_NAME);
            if (configFile.exists()) {
                if (!configFile.delete()) {
                    Logger.warn("failed to delete config");
                }
            }

            if (configFile.createNewFile()) {
                final var fileWriter = new FileWriter(configFile);
                fileWriter.write(JSONObjectConfig.toString(3));
                fileWriter.close();
            }
            else {
                Logger.error("failed to create config file");
            }
        } catch (IOException exception) {
            Logger.error(exception);
        }
    }
}
