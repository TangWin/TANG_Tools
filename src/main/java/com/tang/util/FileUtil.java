package com.tang.util;



import java.io.*;
import java.util.ArrayList;

/**
 * 文件工具类
 * @author TW
 */
public class FileUtil {


    //读取文件

    public static File getFileForPath(String filePath) throws Exception {
        if (StringUtils.isEmpty(filePath)) {
            throw new Exception("文件路径不能为空！");
        }
        File file = new File(filePath);
        if (StringUtils.isEmpty(file) || !file.exists()) {
            throw new Exception("文件不存在！");
        }
        return file;
    }


    /**
     * 读取文本文件
     *
     * @param filePath 文件路径
     * @return
     */
    public static String read(String filePath) {
        File file = new File(filePath);
        String text = read(file);
        file = null;
        return text;
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath
     * @return
     */
    public static boolean exist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * 写文本文件
     *
     * @param path 文件路径
     * @param text 文本内容
     */
    public static void write(String path, String text, String encoding) {
        try {
            File file = new File(path);
            mkdir(file); // 创建目录（如果不存在）
            OutputStreamWriter bufferedWriter = new OutputStreamWriter(new FileOutputStream(path), encoding);
            bufferedWriter.write(text);
            bufferedWriter.close();
            bufferedWriter = null;
            file = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写文本文件
     *
     * @param path 文件路径
     * @param text 文本内容
     */
    public static void write(String path, String text) {
        try {
            File file = new File(path);
            mkdir(file); // 创建目录（如果不存在）
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(text);
            bufferedWriter.close();
            bufferedWriter = null;
            file = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建文件目录，如果目录不存在
     *
     * @param file
     */
    private static void mkdir(File file) {
        File dir = new File(file.getParent());
        if (!dir.exists())
            dir.mkdirs();
    }

    private static String read(File f) {
        StringBuffer str = new StringBuffer();
        try {
            String str1;
            BufferedReader bufferedreader = new BufferedReader(new FileReader(f));
            while ((str1 = bufferedreader.readLine()) != null) {
                if (str1 != null) {
                    str.append(str1).append("\n");
                }
            }
            bufferedreader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.toString();
    }
    public static String read(String filePath,String charsetName) {
        File f = new File(filePath);
        return read(f, charsetName);
    }

    public static String read(File f,String charsetName){
        StringBuffer str = new StringBuffer();
        try {
            FileInputStream fis = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(fis, charsetName);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                str.append(line).append("\r\n");
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    public static String[] reads(String path,String charsetName){
        return reads(new File(path),charsetName);
    }

    public static String[] reads(File f,String charsetName){
        ArrayList<String> contents = new ArrayList<String>();
        try {
            FileInputStream fis = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(fis, charsetName);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                contents.add(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contents.toArray(new String[0]);
    }

    public static void copy(File srcFile, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            if (srcFile.exists()) {
                InputStream inStream = new FileInputStream(srcFile);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void copy(InputStream inStream, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File targetFile = new File(newPath);
            if(targetFile.getParentFile().exists() == false){
                targetFile.getParentFile().mkdirs();
            }
            FileOutputStream fs = new FileOutputStream(targetFile);
            byte[] buffer = new byte[1444];
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                fs.write(buffer, 0, byteread);
            }
            inStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR:file=" + newPath);
        }
    }




}
