package com.tang.core;

import com.tang.util.FileUtil;
import com.tang.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

/**
 * 文件主类
 * @author TW
 */
public class FileOutputMain {


    //目标文件夹
    private final static String TARGET_PATH = "I:\\targetPath\\";
    //指定要提取的关键字
    private final static String SPECIFY_NAME = "readme" ;



    public static void main(String[] args){
        //提取文件
        extractFileForFolder("I:\\BaiduNetdiskDownload\\01 java 基础");
    }



    /**
     * 提取文件夹中指定的部分文件
     * @param folderPath
     */
    public static void extractFileForFolder(String folderPath){
        System.out.println("开始读取：");
        //读取文件
        File sourceFile = null ;
        try {
            sourceFile = FileUtil.getFileForPath(folderPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //递归读取文件
        recursionFile(sourceFile);
        System.out.println("读取完成！");
    }

    /**
     * 递归读取文件，并判断当file是文件时提取到指定目录下
     * @param file
     */
    public static void recursionFile(File file){
        if (file.isDirectory()) {
            //是文件夹时，遍历取出所有文件
            System.out.println("读取文件夹："+file.getName());
            File[] files = file.listFiles();
            //递归读取
            for (File f : files) {
                recursionFile(f);
            }
        }else{
            //判断是否需要进行copy文件
            if (!StringUtils.isEmpty(SPECIFY_NAME) && file.getName().contains(SPECIFY_NAME)) {
                //判断指定文件夹是否存在，不存在则创建
                File targetFile = new File(TARGET_PATH);
                if (!targetFile.exists()) {
                    System.out.println("目标文件夹不存在，开始创建……"+TARGET_PATH);
                    targetFile.mkdirs();
                    System.out.println("目标文件夹创建成功！");
                }
                try {
                    //将文件copy到指定文件夹
                    System.out.println("对文件进行提取：文件名："+file.getName());
                    Files.copy(file.toPath(), new FileOutputStream(new File(TARGET_PATH+file.getName())) );
                    System.out.println("提取成功！");
                }catch (Exception e){
                    System.out.println("提取失败！");
                    e.printStackTrace();
                }
            }
        }
    }


}
