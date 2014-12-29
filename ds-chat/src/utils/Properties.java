package utils;

import java.awt.Color;
import java.awt.Font;

/**
 * Properties class, permit to customize the chat and the gui.
 *
 */
public class Properties {
    /**
     * Name of the server
     */
    public static final String SERVER_NAME = "ChatServer";

    /**
     * Name of the history file (server side)
     */
    public static final String HISTORY_FILE = "history.xml";

    /**
     * Black color used by the gui
     */
    public final static Color CL_BLACK = new Color(0, 0, 0);

    /**
     * White color used by the gui
     */
    public final static Color CL_WHITE = new Color(255, 255, 255);

    /**
     * Font used by the gui
     */
    public final static Font FONT = new Font("Times New Roman", Font.PLAIN, 14);
}
