package com.withwiz.cryptocurrency.exchangeinterfaces.upbit.util;

import ch.qos.logback.classic.Logger;
import com.withwiz.cryptocurrency.util.BaseCommandUtil;
import com.withwiz.cryptocurrency.util.ICommandOptions;
import org.apache.commons.cli.*;
import org.slf4j.LoggerFactory;

/**
 * Upbit command utility
 */
public class UpbitCommandUtil extends BaseCommandUtil implements ICommandOptions {
    /**
     * logger
     */
    protected static Logger log = (Logger) LoggerFactory.getLogger(UpbitCommandUtil.class);

    /**
     * utility title
     */
    protected static String title = "___ Upbit module command utility ___";

    /**
     * test utility
     *
     * @param args arguments
     */
    public void test(String[] args) throws Exception {
        System.out.println(title);
        HelpFormatter helpFormatter = new HelpFormatter();
        //options
        Options options = getOptions();
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine = null;
        try {
            commandLine = commandLineParser.parse(options, args, true);
            //check options
            checkOptions(commandLine);
            initLogging();
            //to do..
//            test(intervalApis, intervalResponse, commandLine);
        } catch (Exception e) {
            //help formatter
            helpFormatter.printHelp("java -jar exchange-interfaces.jar [options]", null,
                    options, "\nCause: " + e.getMessage());
        }
    }

    public void exit() {

    }

    /**
     * get option list
     *
     * @return Options
     */
    @Override
    public Options getOptions() {
        Options options = super.getOptions();
        return options;
    }
}
