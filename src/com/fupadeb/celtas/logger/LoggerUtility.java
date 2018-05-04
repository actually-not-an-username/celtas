package com.fupadeb.celtas.logger;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @see: this class is used to manage the logs of the celtas project you will
 *       need to pass the log level and the message to use the log
 */
public class LoggerUtility {
	private final static Logger celtasLogger = Logger.getLogger("com.fupadeb.celtas");

	public LoggerUtility(int maxAllowedLevel) {
		switch (maxAllowedLevel) {
		case 1:
			// logging informative and beyond
			celtasLogger.setLevel(Level.INFO);
			break;
		case 2:
			// logging warning and beyond
			celtasLogger.setLevel(Level.WARNING);
			break;
		case 3:
			// logging error and beyond
			celtasLogger.setLevel(Level.SEVERE);
			break;
		case 4:
			// logging debug messages and beyond
			celtasLogger.setLevel(Level.FINE);
			break;
		default:
			celtasLogger.setLevel(Level.ALL);
			break;
		}
		ConsoleHandler console = new ConsoleHandler();
		celtasLogger.addHandler(console);
		try {
			FileHandler logFile = new FileHandler("logs/celtas-log-%g.log", 10, (1024 * 1024), true);
			logFile.setEncoding("UTF-8");
			logFile.setFormatter(new SimpleFormatter());
			celtasLogger.addHandler(logFile);		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public void log(int level, String callerClass, String callerMethod, String message) {
		switch (level) {
		case 1:
			// logging an informative message
			celtasLogger.logp(Level.INFO, callerClass, callerMethod, message);
			break;
		case 2:
			// logging a warning
			celtasLogger.logp(Level.WARNING, callerClass, callerMethod, message);
			break;
		case 3:
			// logging an error
			celtasLogger.logp(Level.SEVERE, callerClass, callerMethod, message);
			break;
		case 4:
			// logging a debug message
			celtasLogger.logp(Level.FINE, callerClass, callerMethod, message);
			break;
		default:
			break;
		}
	}
}
