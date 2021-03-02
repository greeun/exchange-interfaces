package com.withwiz.cryptocurrency.exchangeinterfaces.exchange.upbit.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.withwiz.cryptocurrency.exchangeinterfaces.exchange.upbit.UpbitConsts;
import com.withwiz.cryptocurrency.exchangeinterfaces.exchange.upbit.dto.disclosure.DisclosurePost;
import com.withwiz.cryptocurrency.exchangeinterfaces.exchange.upbit.dto.disclosure.ProjectDisclosure;
import com.withwiz.util.RandomUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class UpbitUtil {
    /**
     * logger
     */
    private static Logger log = LoggerFactory.getLogger(UpbitUtil.class);

    /**
     * object mapper
     */
    private static ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    /**
     * previous DisclosurePost
     */
    public static DisclosurePost previousProjectDisclosurePost = null;

    /**
     * check new disclosure post and set
     *
     * @return new disclosure post
     */
    public static DisclosurePost checkNewDisclosure() throws IOException {
        DisclosurePost newDisclosurePost = UpbitUtil.getNewDisclosure();
        if (newDisclosurePost == null) {
            log.debug("New disclosure NOT exist.");
        }
        //set from new disclosure post
        else {
            previousProjectDisclosurePost = newDisclosurePost;
            log.info("Set a previous disclosure: {}", newDisclosurePost);
        }
        return newDisclosurePost;
    }

    /**
     * get new disclosure
     *
     * @return new DisclosurePost instance
     * @throws IOException
     */
    public static DisclosurePost getNewDisclosure() throws IOException {
        DisclosurePost disclosurePost = UpbitUtil.getLastDisclosure(UpbitConsts.URL_DISCLOSURE_API);
        log.debug("last disclosure post: {}", disclosurePost);
        // if disclosure NOT exist
        if (disclosurePost == null) {
            return null;
        }
        //check new disclosure
        if (isNewDisclosurePost(disclosurePost)) {
            log.debug("new disclosure: {}", disclosurePost);
            return disclosurePost;
        } else
            return null;
    }

    /**
     * get project disclosure
     *
     * @param url URL
     * @return DisclosurePost
     * @throws IOException
     */
    public static DisclosurePost getLastDisclosure(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", UpbitConsts.HTTP_USER_AGENT)
                .build();
        Response response = client.newCall(request).execute();
        if (response.body() == null) {
            return null;
        } else
            return getLastDisclosure(response.body().byteStream());
    }

    /**
     * get last disclosure
     *
     * @param inputStream InputStream
     * @return DisclosurePost
     */
    public static DisclosurePost getLastDisclosure(InputStream inputStream) throws IOException {
        ProjectDisclosure projectDisclosure = getProjectDisclosure(inputStream);
        return projectDisclosure.getData().getPosts().get(0);
    }

    /**
     * get a ProjectDisclosure
     *
     * @param inputStream InputStream
     * @return ProjectDisclosure
     */
    public static ProjectDisclosure getProjectDisclosure(InputStream inputStream) throws IOException {
        ProjectDisclosure result = null;
        JsonNode rootNode = objectMapper.readValue(inputStream, JsonNode.class);
        log.debug("received from remote: {}", rootNode.toPrettyString());
        return objectMapper.convertValue(rootNode, ProjectDisclosure.class);
    }

    /**
     * is new disclosurePost
     *
     * @param disclosurePost disclosure post
     * @return true / false
     */
    public static boolean isNewDisclosurePost(DisclosurePost disclosurePost) {
        //if previouse NOT exist
        if (previousProjectDisclosurePost == null) {
            //set previous
            previousProjectDisclosurePost = disclosurePost;
            log.info("Set a previous disclosure: {}", disclosurePost);
            return false;
        }
        if (!previousProjectDisclosurePost.getAssets().equals(disclosurePost.getAssets())
                || previousProjectDisclosurePost.getId() != disclosurePost.getId()) {
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

                checkNewDisclosure();

                int seconds = RandomUtil.randomInt(UpbitConsts.RANDOM_MAX_BOUND);
                log.info("delay randomized: {}", seconds);
                Thread.sleep(seconds * 1000);
            }
        } catch (InterruptedException | IOException e) {
            log.error("{}", e);
        }
    }
}
