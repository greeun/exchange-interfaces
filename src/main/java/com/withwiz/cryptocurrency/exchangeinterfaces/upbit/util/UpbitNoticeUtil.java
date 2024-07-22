package com.withwiz.cryptocurrency.exchangeinterfaces.upbit.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.withwiz.cryptocurrency.exchangeinterfaces.upbit.UpbitConsts;
import com.withwiz.cryptocurrency.exchangeinterfaces.upbit.dto.notice.Notice;
import com.withwiz.cryptocurrency.exchangeinterfaces.upbit.dto.notice.NoticePost;
import com.withwiz.util.RandomUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class UpbitNoticeUtil {
    /**
     * logger
     */
    private static Logger log = LoggerFactory.getLogger(UpbitNoticeUtil.class);

    /**
     * object mapper
     */
    private static ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    /**
     * previous NoticePost
     */
    public static NoticePost previousNoticePost = null;

    /**
     * check new notice post and set
     *
     * @return new notice post
     */
    public static NoticePost checkNewPost() throws IOException {
        NoticePost newNoticePost = UpbitNoticeUtil.getNewNotice();
        if (newNoticePost == null) {
            log.debug("New post NOT exist.");
        }
        //set from new disclosure post
        else {
            previousNoticePost = newNoticePost;
            log.info("Set a previous post: {}", newNoticePost);
        }
        return newNoticePost;
    }

    /**
     * get new notice
     *
     * @return new NoticePost instance
     * @throws IOException
     */
    public static NoticePost getNewNotice() throws IOException {
        NoticePost lastNoticePost = UpbitNoticeUtil.getLastNoticePost(UpbitConsts.URL_NOTICE_API);
        log.debug("last post: {}", lastNoticePost);
        // if a post NOT exist
        if (lastNoticePost == null) {
            return null;
        }
        //check new notice
        if (isNewNoticePost(lastNoticePost)) {
            log.debug("new post: {}", lastNoticePost);
            return lastNoticePost;
        } else
            return null;
    }

    /**
     * get notice post
     *
     * @param url URL
     * @return NoticePost
     * @throws IOException
     */
    public static NoticePost getLastNoticePost(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", UpbitConsts.HTTP_USER_AGENT)
                .build();
        Response response = client.newCall(request).execute();
        if (response.body() == null) {
            return null;
        } else {
            NoticePost noticePost = null;
            byte[] bytes = null;
            try {
                bytes = response.body().bytes();
//                log.info("response body:\n{}", new String(bytes));
                noticePost = getLastNoticePost(bytes);
            } catch (IOException e) {
                log.error("response body:\n{}", new String(bytes));
                throw e;
            }
            return noticePost;
        }
    }

    /**
     * get last notice post
     *
     * @param inputStream InputStream
     * @return NoticePost
     * @throws IOException
     */
    public static NoticePost getLastNoticePost(InputStream inputStream) throws IOException {
        Notice coinListing = getCoinListing(inputStream);
        return coinListing.getData().getNoticePosts().get(0);
    }

    /**
     * get last notice post
     *
     * @param bytes message byte array
     * @return NoticePost
     * @throws IOException
     */
    public static NoticePost getLastNoticePost(byte[] bytes) throws IOException {
        Notice coinListing = getCoinListing(bytes);
        return coinListing.getData().getNoticePosts().size() > 0 ? coinListing.getData().getNoticePosts().get(0) : null;
    }

    /**
     * get a CoinListing
     *
     * @param inputStream InputStream
     * @return CoinListing
     */
    public static Notice getCoinListing(InputStream inputStream) throws IOException {
        Notice result = null;
        JsonNode rootNode = objectMapper.readValue(inputStream, JsonNode.class);
        log.debug("received from remote: {}", rootNode.toPrettyString());
        return objectMapper.convertValue(rootNode, Notice.class);
    }

    /**
     * get a CoinListing
     *
     * @param bytes message byte array
     * @return CoinListing
     */
    public static Notice getCoinListing(byte[] bytes) throws IOException {
        Notice result = null;
        JsonNode rootNode = objectMapper.readValue(bytes, JsonNode.class);
        log.debug("received from remote: {}", rootNode.toPrettyString());
        return objectMapper.convertValue(rootNode, Notice.class);
    }

    /**
     * is new notice post
     *
     * @param noticePost notice post
     * @return true / false
     */
    public static boolean isNewNoticePost(NoticePost noticePost) {
        //if previouse NOT exist
        if (previousNoticePost == null) {
            //set previous
            previousNoticePost = noticePost;
            log.info("Set a previous post: {}", noticePost);
            return false;
        }
        if (previousNoticePost.getId() != noticePost.getId()) {
            return true;
        }
        return false;
    }

    /**
     * test main
     *
     * @param args
     */
    public static void main(String[] args) {
        int counter = 100;
        try {
            for (int i = 0; i < counter; i++) {
                log.info("--- counter: {}", i);

                checkNewPost();

                int seconds = RandomUtil.randomInt(UpbitConsts.RANDOM_MAX_BOUND);
                log.info("delay randomized: {}", seconds);
                Thread.sleep(seconds * 1000);
            }
        } catch (InterruptedException | IOException e) {
            log.error("{}", e);
        }
    }
}
