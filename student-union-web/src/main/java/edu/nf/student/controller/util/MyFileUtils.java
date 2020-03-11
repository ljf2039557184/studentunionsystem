package edu.nf.student.controller.util;

import java.io.File;
import java.util.UUID;

/**
 * @author wangl
 * @date 2019/11/6
 */
public class MyFileUtils {

    public static void createUploadDir(String uploadPath){
        //构建上传目录
        File uploadDir = new File(uploadPath);
        //如果不存在此目录则创建
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }
    }

    public static File createUploadFile(String uploadPath, String fileName){
        //构建一个完整的文件上传对象
        File uploadFile = new File(uploadPath + File.separator + fileName);
        return uploadFile;
    }

    public static String newFileName(String old){
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String[] split = old.split("\\.");
        if(split.length > 1){
            String ext = split[split.length - 1];
            return uuid + "." + ext;
        }
        return uuid;
    }
}
