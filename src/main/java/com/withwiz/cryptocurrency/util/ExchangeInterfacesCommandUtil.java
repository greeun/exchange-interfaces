package com.withwiz.cryptocurrency.util;

import com.withwiz.cryptocurrency.exchangeinterfaces.upbit.util.UpbitCommandUtil;

import org.apache.commons.cli.*;
import org.apache.commons.lang3.ArrayUtils;

/**
 * command utility class
 */
public class ExchangeInterfacesCommandUtil extends BaseCommandUtil implements ICommandOptions {
    /**
     * test mode
     */
    public static boolean isTestMode = false;

    /**
     * test options
     */
    private static String[] testOptions = new String[]{"-ub", "-bs"};

    /**
     * command utility title
     */
    private static String title = "Exchange interfaces utility";

    /**
     * help message(usage)
     */
    private static String helpMessage = "java -jar exchange-interfaces.jar [options]";

    /**
     * get option list
     *
     * @return Options
     */
    protected Options getOptions() {
        Options options = super.getOptions();

        //test bithumb function
        options.addOption(Option.builder(OPTION_BITHUMB).desc("for Bithumb").build());
        //test upbit function
        options.addOption(Option.builder(OPTION_UPBIT).desc("for Upbit").build());

        return options;
    }

    /**
     * start
     *
     * @param args arguments
     */
    public void start(String[] args) {
        System.out.println("=== " + title + " ===");
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.setOptionComparator(null);
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
        ExchangeInterfacesCommandUtil util = new ExchangeInterfacesCommandUtil();
        if(isTestMode) {
            args = testOptions;
        }
        util.start(args);

    }
}
