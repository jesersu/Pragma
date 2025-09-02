package shared.util.CustomLogger;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class AppLogger {
    private final Logger logger;
    private final String className;
    private static final ObjectMapper mapper = new ObjectMapper();

    private AppLogger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
        this.className = clazz.getSimpleName();
    }

    public static AppLogger getLogger(Class<?> clazz) {
        return new AppLogger(clazz);
    }

    private void log(String level, String message, Throwable t) {
        try {
            Map<String, Object> logMap = new HashMap<>();
            logMap.put("timestamp", Instant.now().toString());
            logMap.put("level", level);
            logMap.put("logger", className);
            logMap.put("message", message);
            logMap.put("thread", Thread.currentThread().getName());
            if (t != null) {
                logMap.put("error", t.getMessage());
            }

            String json = mapper.writeValueAsString(logMap);

            switch (level) {
                case "INFO" -> logger.info(json);
                case "DEBUG" -> logger.debug(json);
                case "WARN" -> logger.warn(json);
                case "ERROR" -> logger.error(json, t);
            }
        } catch (Exception e) {
            logger.error("Failed to log JSON message", e);
        }
    }

    public void info(String message) {
        log("INFO", message, null);
    }

    public void debug(String message) {
        log("DEBUG", message, null);
    }

    public void warn(String message) {
        log("WARN", message, null);
    }

    public void error(String message, Throwable t) {
        log("ERROR", message, t);
    }
}
