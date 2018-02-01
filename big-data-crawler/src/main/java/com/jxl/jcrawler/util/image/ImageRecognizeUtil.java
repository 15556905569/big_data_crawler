package com.jxl.jcrawler.util.image;

import com.jxl.jcrawler.enums.ImageRecognizePlatform;
import com.jxl.jcrawler.enums.VCodeType;
import com.jxl.jcrawler.util.common.*;
import com.jxl.jcrawler.util.http.HttpHelper;
import com.jxl.jcrawler.util.http.SimpleHttpHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by amosli on 20/07/2017.
 */
public class ImageRecognizeUtil {

    public static final short MAX_RETRY = 3;
    public static final Integer TIMEOUT = 10000;
    public static final ImageRecognizePlatform DEFAULT_PLATFORM = ImageRecognizePlatform.CHAO_JI_YING;
    public static String domain;
    public static String orgId;
    public static String image_recognize_url;
    public static String image_recognize_error_feedback_url;
    private static HttpHelper httpHelper = new SimpleHttpHelper();
    private static Logger LOGGER = LoggerFactory.getLogger(ImageRecognizeUtil.class);

    static {
        load();
    }

    public static void load() {
        PropertiesUtil propertiesUtil = new PropertiesUtil("/config/image.properties");
        domain = propertiesUtil.getValue("domain");
        orgId = propertiesUtil.getValue("orgId");
        image_recognize_url = domain + propertiesUtil.getValue("image_recognize_url");
        image_recognize_error_feedback_url = domain + propertiesUtil.getValue("image_recognize_error_feedback_url");
    }

    /**
     * 默认识别平台为超级鹰
     *
     * @param imageBytes
     * @param type
     * @return
     */
    public static ImageResponse recognize(byte[] imageBytes, VCodeType type) {
        return recognize(imageBytes, type, DEFAULT_PLATFORM, TIMEOUT);
    }

    /**
     * 默认识别失败重试3次
     *
     * @param url
     * @param type
     * @return
     */
    public static ImageResponse recognizeWithRetry(String url, VCodeType type, HttpHelper httpHelper) {
        byte[] bytes = httpHelper.goGetBytes(url);
        //TODO 需自动切换识别平台 ImageRecognizePlatform[] values = ImageRecognizePlatform.values();
        for (int i = 0; i < MAX_RETRY; i++) {
            ImageResponse result = recognize(bytes, type, DEFAULT_PLATFORM, TIMEOUT);
            if (StringUtil.isEmpty(result)) {
                continue;
            }
            return result;
        }
        LOGGER.error("验证码识别次数过多!");
        return null;
    }

    /**
     * 默认识别失败重试3次
     *
     * @param url
     * @param type
     * @return
     */
    public static ImageResponse recognizeWithRetry(String url, VCodeType type) {
        byte[] bytes = httpHelper.goGetBytes(url);
        //TODO 需自动切换识别平台 ImageRecognizePlatform[] values = ImageRecognizePlatform.values();
        for (int i = 0; i < MAX_RETRY; i++) {
            ImageResponse result = recognize(bytes, type, DEFAULT_PLATFORM, TIMEOUT);
            if (StringUtil.isEmpty(result)) {
                continue;
            }
            return result;
        }
        LOGGER.error("验证码识别次数过多!");
        return null;
    }

    /**
     * 默认识别失败重试3次
     *
     * @param imageBytes
     * @param type
     * @return
     */
    public static ImageResponse recognizeWithRetry(byte[] imageBytes, VCodeType type) {
        for (int i = 0; i < MAX_RETRY; i++) {
            ImageResponse result = recognize(imageBytes, type, DEFAULT_PLATFORM, TIMEOUT);
            if (StringUtil.isEmpty(result)) {
                continue;
            }
            return result;
        }
        LOGGER.error("验证码识别次数过多!");
        return null;
    }

    /**
     * 默认识别失败重试3次
     *
     * @param imageBytes
     * @param type
     * @param platform
     * @param timeout
     * @param maxRetry
     * @return
     */
    public static ImageResponse recognizeWithRetry(byte[] imageBytes, VCodeType type, ImageRecognizePlatform platform, Integer timeout, short maxRetry) {
        for (int i = 0; i < maxRetry; i++) {
            ImageResponse result = recognize(imageBytes, type, platform, timeout);
            if (StringUtil.isEmpty(result)) {
                continue;
            }
            return result;
        }
        return null;
    }

    /**
     * base64String：图片验证码的base64字符串形式
     * platformType：识别平台类型（0：优优云，1：超级鹰，2：混合平台（所支持识别平台的混合））
     * timeout：识别超时时间，单位：毫秒（可选，默认为10000）
     *
     * @param imageBytes
     * @param type
     * @param platformType
     * @return
     */
    public static ImageResponse recognize(byte[] imageBytes, VCodeType type, ImageRecognizePlatform platformType, Integer timeout) {
        FileUtil.asyncWriteImages(imageBytes);
        String base64String = Base64Util.encode(imageBytes);
        String param = "base64String=%s&platformType=%d&pCodeType=%d&timeout=%d";
        param = String.format(param, base64String, platformType.ordinal(), type.getCode(), timeout);
        httpHelper.config.addHeader("OrgId", orgId);
        String result = httpHelper.goPost(image_recognize_url, param);
        ImageResponse imageResponse = JsonUtil.parse(result, ImageResponse.class);
        if (imageResponse.getSuccess()) {
            return imageResponse;
        } else {
            System.err.println(imageResponse);
        }
        return null;
    }

    /**
     * 识别错误,返回题分
     *
     * @param imageResponse
     */
    public static void errorFeedback(ImageResponse imageResponse) {
        if (StringUtil.isEmpty(imageResponse)) {
            LOGGER.error("返回题分失败,imageResponse为空!");
            return;
        }

        int i = 0;
        httpHelper.config.addHeader("OrgId", orgId);
        ImageResponse errorImageResponse;
        do {
            i++;
            String errorUrl = String.format(image_recognize_error_feedback_url, imageResponse.getPlatformType(), imageResponse.getPCodeId());
            String result = httpHelper.goGet(errorUrl);
            errorImageResponse = JsonUtil.parse(result, ImageResponse.class);
        } while (i <= MAX_RETRY && (errorImageResponse == null || !errorImageResponse.getSuccess()));
        if (i > MAX_RETRY) {
            LOGGER.error("返回题分失败!" + imageResponse);
        } else {
            LOGGER.info("返回题分成功!" + imageResponse);
        }
    }

}
