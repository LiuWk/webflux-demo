package com.example.webflux.demo.util;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 图形处理工具类
 *
 * @author lwk
 */
public class ImageUtils {

    private final static Logger logger = LoggerFactory.getLogger(ImageUtils.class);


    /**
     * 图片转换  支持格式转换
     *
     * @param from         源文件
     * @param to           目标文件
     * @param quality      质量
     * @param cleanProfile 是否清除profile信息  清除profile信息可能会造成图片旋转，请根据实际情况测试使用
     * @throws InterruptedException
     * @throws IOException
     * @throws IM4JavaException
     */
    public static void convertImage(String from, String to, int quality, boolean cleanProfile) throws Exception {
        long beginTime = System.currentTimeMillis();
        IMOperation op = new IMOperation();
        op.addImage(from);
        if (quality > 0 && quality <= 100) {
            op.addRawArgs("-quality", String.valueOf(quality));
        }
        if (cleanProfile) {
            op.addRawArgs("+profile", "*");
        }
        op.addImage(to);
        if (logger.isInfoEnabled()) {
            logger.info("convertImage operation:[{}]", op.toString());
        }
        ConvertCmd convert = new ConvertCmd(true);
        convert.run(op);
        if (logger.isInfoEnabled()) {
            logger.info("convertImage executeTime:[{}]ms", (System.currentTimeMillis() - beginTime));
        }
    }

    /**
     * 获取图片信息
     *
     * @param imageURL 图片地址
     * @return 图片信息
     */
    public static Info getImageInfo(String imageURL) {
        System.setProperty("im4java.useGM", "true");
        try {
            return new Info(imageURL, false);
        } catch (Exception e) {
            logger.error("获取图片信息发生异常：", e);
        }
        return null;
    }

    /**
     * 根据尺寸缩放图片
     *
     * @param width   缩放后的图片宽度
     * @param height  缩放后的图片高度
     * @param srcPath 源图片路径
     * @param newPath 缩放后图片的路径
     */
    public static void zoomImage(Integer width, Integer height, String srcPath, String newPath) throws Exception {
        IMOperation op = new IMOperation();
        op.addImage(srcPath);
        //根据高度缩放图片
        if (width == null) {
            op.resize(null, height);
            //根据宽度缩放图片
        } else if (height == null) {
            op.resize(width, null);
        } else {
            op.resize(width, height);
        }
        op.addImage(newPath);
        ConvertCmd cmd = new ConvertCmd(true);
        cmd.run(op);
    }

    public static void main(String[] args) throws Exception {
        long t = System.currentTimeMillis();
        ConvertCmd cmd = new ConvertCmd();
        IMOperation op = new IMOperation();
        op.resize(480, 360);
        op.addImage("C:\\Users\\Administrator\\Desktop\\og.jpg");
        op.addImage("C:\\Users\\Administrator\\Desktop\\1.jpg");
        System.out.println(op.toString());
        cmd.setSearchPath("C:\\Program Files (x86)\\ImageMagick-6.2.7-Q16");
        cmd.run(op);
        System.out.println("程序处理时间：" + (System.currentTimeMillis() - t) + "ms");
    }
}
