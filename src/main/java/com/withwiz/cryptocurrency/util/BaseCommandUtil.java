package com.withwiz.cryptocurrency.util;

import com.withwiz.commandutil.CommonCommandUtil;
import com.withwiz.cryptocurrency.exchangeinterfaces.upbit.util.UpbitCommandUtil;

import org.apache.commons.cli.*;
import org.apache.commons.lang3.ArrayUtils;

/**
 * command utility class
 */
public class BaseCommandUtil extends CommonCommandUtil implements ICommandOptions {
    /**
     * help message(usage)
     */
    private static String helpMessage = "java -jar exchange-interfaces.jar [options]";

//    /**
//     * delay for response
//     */
//    public static long intervalResponse = DEFAULT_INTERVAL_FOR_RESPONSE;
//
//    /**
//     * delay for apis
//     */
//    public static long intervalApis = DEFAULT_INTERVAL_FOR_APIS;

    /**
     * get option list
     *
     * @return Options
     */
    protected Options getOptions() {
        Options options = super.getOptions();

        //interval
        options.addOption(Option.builder(OPTION_INTERVAL_FOR_APIS).longOpt(LONG_OPTION_INTERVAL_FOR_APIS)
                .hasArg().desc("interval(ms) for APIs").build());
        options.addOption(Option.builder(OPTION_INTERVAL_FOR_RESPONSE).longOpt(LONG_OPTION_INTERVAL_FOR_RESPONSE)
                .hasArg().desc("interval(ms) for Response").build());

        //get version
        options.addOption(Option.builder(OPTION_GET_VERSION).longOpt(LONG_OPTION_GET_VERSION)
                .desc("get version").build());

        //test all
        options.addOption(Option.builder(OPTION_ALL).longOpt(LONG_OPTION_ALL)
                .desc("test all with default values").build());

        //manual mode
        options.addOption(Option.builder(OPTION_MANUAL_MODE).longOpt(LONG_OPTION_MANUAL_MODE)
                .desc("manual mode for user's customized values").build());
        return options;
    }

    /**
     * start
     *
     * @param args arguments
     */
    public void start(String[] args) throws Exception {
//        System.out.println("=== " + title + " ===");
        HelpFormatter helpFormatter = new HelpFormatter();
        //options
        Options options = getOptions();
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine = null;

        try {
            commandLine = commandLineParser.parse(options, args, true);
            //check options
            checkOptions(commandLine);
//            System.out.println("-target module: " + options.toString());
            if (commandLine.hasOption(OPTION_UPBIT)) {
                executeTestUpbit(ArrayUtils.removeElement(args, "-" + OPTION_UPBIT));
            }
            if (commandLine.hasOption(OPTION_BITHUMB)) {
                executeTestBithumb(ArrayUtils.removeElement(args, "-" + OPTION_BITHUMB));
            }
            if (commandLine.hasOption(OPTION_TELEGRAM)) {
                executeTestTelegram(ArrayUtils.removeElement(args, "-" + OPTION_TELEGRAM));
            }

        } catch (Exception e) {
            //help formatter
            helpFormatter.printHelp(helpMessage, null, options, "\nCause: " + e.getMessage());
            throw e;
//        } catch (FileNotFoundException e) {
//            helpFormatter.printHelp(" ", "", options, "\n*The file NOT exist: " + getFilePath(commandLine));
//            throw e;
//        } catch (NoSuchFileException e) {
//            helpFormatter.printHelp(" ", "", options, "\n*The file NOT exist: " + getFilePath(commandLine));
//            throw e;
        }
    }

    /**
     * test Upbit
     *
     * @param args arguments
     */
    public void executeTestUpbit(String[] args) throws Exception {
        UpbitCommandUtil util = new UpbitCommandUtil();
        util.test(args);
        util.exit();
    }

    /**
     * test Bithumb
     *
     * @param args arguments
     */
    private void executeTestBithumb(String[] args) throws Exception {
        //to do...
    }

    /**
     * test Telegram messe
     *
     * @param args arguments
     */
    private void executeTestTelegram(String[] args) throws Exception {
        //to do...
    }

    /**
     * check options
     *
     * @param commandLine CommandLine
     * @throws ParseException
     */
    protected void checkOptions(CommandLine commandLine) throws Exception {
        ////check target module
        if ( !(commandLine.hasOption(OPTION_UPBIT)
                || commandLine.hasOption(OPTION_BITHUMB)
                || commandLine.hasOption(OPTION_BITHUMB)) ) {
            throw new Exception(new StringBuilder("[required: -").append(OPTION_UPBIT)
                    .append(" or -").append(OPTION_BITHUMB)
                    .append(" or -").append(OPTION_BITHUMB)
                    .append("]").toString());
        }
    }

    /**
     * start main
     *
     * @param args
     */
    public static void main(String[] args) {
        BaseCommandUtil util = new BaseCommandUtil();
//        if(isTestMode) {
//            args = testOptions;
//        }
        try {
            util.start(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
