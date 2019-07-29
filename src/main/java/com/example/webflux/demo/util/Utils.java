package com.example.webflux.demo.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 工具类
 *
 * @author lwk
 * @date 2019-05-14 15:37
 */
public class Utils {
    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    /**
     * 转换json为请求对象
     *
     * @param json rest接口请求数据
     * @return
     */
//    public static Request getRequest(String json) {
//        try {
//            if (StringUtils.isEmpty(json)) {
//                return null;
//            }
//            return JSONObject.parseObject(json, Request.class);
//        } catch (Exception e) {
//            logger.error("getRequest exception={}", e.getMessage(),e);
//        }
//        return null;
//    }

    /**
     * md5 数据
     *
     * @param content 内容
     * @param salt    混淆参数
     * @return 返回加密之后的字符
     */
    public static String md5(String content, String salt) {
        return DigestUtils.md5Hex(String.format("%s_%s", content, salt));
    }


    /**
     * 判断是否有空值，不要使用！操作
     *
     * @param objects
     * @return true至少有一个为空，false都不为空
     */
    public static boolean hasEmpty(Object... objects) {
        for (Object obj : objects) {
            if (obj == null || "".equals(obj)) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(md5("123456", "15211110000"));
        System.out.println(hasEmpty(null, ""));
        Integer a = 127;
        Integer b = 127;
        System.out.println(a==b);
        System.out.println(a.equals(b));
        System.out.println(Integer.valueOf(10));
    }
}
