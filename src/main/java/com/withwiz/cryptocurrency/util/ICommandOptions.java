package com.withwiz.cryptocurrency.util;

/**
 *
 */
public interface ICommandOptions {
    /**
     * option: get version
     */
    String OPTION_GET_VERSION = "gv";

    /**
     * long option: get version
     */
    String LONG_OPTION_GET_VERSION = "get-version";

    /**
     * option: test all
     */
    String OPTION_ALL = "a";

    /**
     * long option: test all
     */
    String LONG_OPTION_ALL = "all";

    /**
     * option: manual mode
     */
    String OPTION_MANUAL_MODE = "m";

    /**
     * long option: manual mode
     */
    String LONG_OPTION_MANUAL_MODE = "manual";

    /**
     * option: interval for apis
     */
    String OPTION_INTERVAL_FOR_APIS = "ia";

    /**
     * long option: interval for apis
     */
    String LONG_OPTION_INTERVAL_FOR_APIS = "interval-api";

    /**
     * option: interval for response
     */
    String OPTION_INTERVAL_FOR_RESPONSE = "ir";

    /**
     * long option: interval for response
     */
    String LONG_OPTION_INTERVAL_FOR_RESPONSE = "interval-response";

    /**
     * option: upbit
     */
    String OPTION_UPBIT = "ub";

    /**
     * option: bitthumb
     */
    String OPTION_BITHUMB = "bt";

    /**
     * option: telegram
     */
    String OPTION_TELEGRAM = "t";
}
