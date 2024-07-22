package com.withwiz.commandutil;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.OutputStreamAppender;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import com.withwiz.sandbeach.log.LoggerCreator;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

/**
 * common command utility
 */
public class CommonCommandUtil {
    /**
     * log format
     */
    public static String logFormat = "%-5level %msg%n";

    /**
     * default value: log level: info
     */
    static String DEFAULT_LOG_LEVEL = "INFO";

    /**
     * option: log level
     */
    static String OPTION_LOG_LEVEL = "ll";

    /**
     * option: log level
     */
    static String LONG_OPTION_LOG_LEVEL = "log-level";

    /**
     * log level
     */
    public static String logLevel = DEFAULT_LOG_LEVEL;

    /**
     * logger
     */
    protected Logger log = (Logger) LoggerFactory.getLogger(getClass());

    /**
     * get Logger
     *
     * @param name logger name
     * @return Logger
     */
    public Logger getLogger(String name) {
        return LoggerCreator.createConsoleLogger(name, null, LoggerCreator.LEVEL_ALL);
    }

    /**
     * get ROOT logger
     *
     * @return ROOT logger
     */
    protected Logger getRootLogger() {
        return (Logger) LoggerCreator.getRootLogger();
    }

    /**
     * set logger level
     *
     * @param logger Logger
     * @param level  level
     */
    protected void setLoggerLevel(Logger logger, String level) {
        LoggerCreator.setLoggerLevel(logger, Level.toLevel(level));
    }

    /**
     * set logger format
     *
     * @param logger Logger
     * @param layout log format pattern layout
     */
    protected void setLoggerFormat(Logger logger, Layout layout) {
        OutputStreamAppender<ILoggingEvent> appender = null;
        LayoutWrappingEncoder ple = null;
        // pattern
        for (Iterator<Appender<ILoggingEvent>> it = logger.iteratorForAppenders(); it.hasNext(); ) {
            appender = (OutputStreamAppender<ILoggingEvent>) it.next();
            ple = (LayoutWrappingEncoder) appender.getEncoder();
            ple.setLayout(layout);
        }
    }

    /**
     * set logger format
     *
     * @param logger  Logger
     * @param pattern log format pattern
     */
    protected void setLoggerFormat(Logger logger, String pattern) {
        Layout layout = LoggerCreator.createPatternLayout(logger.getLoggerContext(), pattern, true);
        setLoggerFormat(logger, layout);
    }

    /**
     * set ROOT logger
     */
    protected void setRootLogger(String pattern, String level) {
        Logger logger = getRootLogger();
        setLoggerFormat(logger, pattern);
        setLoggerLevel(logger, level);
    }

    /**
     * initialize for logging
     */
    protected void initLogging() {
        setRootLogger(logFormat, logLevel);
    }

    /**
     * get option list
     *
     * @return Options
     */
    protected Options getOptions() {
        Options options = new Options();
        //logging level
        options.addOption(Option.builder(OPTION_LOG_LEVEL).longOpt(LONG_OPTION_LOG_LEVEL)
                .hasArg().desc("log level(TRACE, INFO, WARN, DEBUG, ERROR)").build());

        return options;
    }

    /**
     * check options
     *
     * @param commandLine CommandLine
     * @throws ParseException
     */
    protected void checkOptions(CommandLine commandLine) throws ParseException, Exception {
        //check log level
        if (commandLine.hasOption(OPTION_LOG_LEVEL)) {
            String level = commandLine.getOptionValue(OPTION_LOG_LEVEL);
            if (level == null) {
                throw new ParseException(new StringBuilder("[required: -").append(OPTION_LOG_LEVEL)
                        .append(" [option value]")
                        .append("]").toString());
            } else {
                logLevel = level;
            }
        } else {
            logLevel = DEFAULT_LOG_LEVEL;
        }
    }
}
