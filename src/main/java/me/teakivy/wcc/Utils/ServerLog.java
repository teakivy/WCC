package me.teakivy.wcc.Utils;

import org.bukkit.Bukkit;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerLog {

    private static final Logger logger = Bukkit.getServer().getLogger();

    /**
     * Logs a message to the console.
     * @param message The message to log.
     */
    public static void log(String message) {
        logger.log(Level.INFO, message);
    }

    /**
     * Logs a message to the console using Log Level INFO.
     * @param message The message to log.
     */
    public static void info(String message) {
        logger.log(Level.INFO, message);
    }

    /**
     * Logs a message to the console using Log Level WARNING.
     * @param message The message to log.
     */
    public static void warning(String message) {
        logger.log(Level.WARNING, message);
    }

    /**
     * Logs a message to the console using Log Level SEVERE.
     * @param message The message to log.
     */
    public static void error(String message) {
        logger.log(Level.SEVERE, message);
    }

    /**
     * Logs a message to the console using Log Level SEVERE.
     * @param message The message to log.
     */
    public static void severe(String message) {
        logger.log(Level.SEVERE, message);
    }

    /**
     * Logs a message to the console using Log Level FINE.
     * @param message The message to log.
     */
    public static void debug(String message) {
        logger.log(Level.FINE, message);
    }

    /**
     * Gets the Logger for the server.
     * @return The server's Logger.
     */
    public static Logger getLogger() {
        return logger;
    }
}
