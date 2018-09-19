package com.tang.util;



import java.io.File;

/**
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





}
