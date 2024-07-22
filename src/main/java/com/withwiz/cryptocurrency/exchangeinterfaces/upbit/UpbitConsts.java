package com.withwiz.cryptocurrency.exchangeinterfaces.upbit;

/**
 * consts
 */
public class UpbitConsts {

    /**
     * random round max int value
     */
    public static int RANDOM_MAX_BOUND = 8;

    /**
     * URL: notice for coin listing
     */
    public static String URL_NOTICE_API = "https://api-manager.upbit.com/api/v1/notices?page=1&per_page=20&thread_name=general";

    /**
     * URL: notice detail page
     */
    public static String URL_NOTICE_DETAIL_PAGE = "https://upbit.com/service_center/notice?id=";

    /**
     * URL: disclosure API
     */
    public static String URL_DISCLOSURE_API = "https://project-team.upbit.com/api/v1/disclosure?region=kr&per_page=20";
//    public static String URL_DISCLOSURE_API = "http://localhost:8080/test/disclosure.html";

    /**
     * URL: disclosure web page
     */
    public static String URL_DISCLOSURE_WEB_PAGE = "https://upbit.com/service_center/disclosure";

    /**
     * message text: new disclosure
     */
    public static String MSG_NEW_DISCLOSURE = "News: A \"New project disclosure\" has been discovered!\n";

    /**
     * API URL: market all
     */
    public static String API_URL_MARKET_ALL = "https://api.upbit.com/v1/market/all";

    /**
     * http user agent: Chrome 41.0.2228.0
     */
    public static String HTTP_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";
}
