package edu.nf.student.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import edu.nf.student.controller.util.MyFileUtils;
import edu.nf.student.controller.vo.ResponseVO;
import edu.nf.student.entity.DataBase;
import edu.nf.student.entity.Student;
import edu.nf.student.service.DataBaseService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author admin
 * @date 2019/12/19
 */
@RestController
public class DataBaseController extends BaceControler{

    private final static Logger logger = LoggerFactory.getLogger(DataBaseController.class);


    @Autowired
    private DataBaseService service;

    public static final String FILE_NAME= "D:"+ File.separator + "upload"+File.separator +"database";

    @GetMapping("/database_info")
    public ResponseVO<PageInfo<DataBase>> listDataBase(Integer pageNum,Integer pageSize){
        List<DataBase> dataBases = service.listDataBase(pageNum,pageSize);
        PageInfo<DataBase> pageInfo = new PageInfo<>(dataBases);
        return success(pageInfo);
    }

    @GetMapping("/database_by_id")
    public ResponseVO<PageInfo<DataBase>> getDataBase(Integer pageNum,Integer pageSize,String findValue){
        List<DataBase> dataBases = service.getDataBase(findValue,pageNum,pageSize);
        PageInfo<DataBase> pageInfo = new PageInfo<>(dataBases);
        System.out.println("当前页："+pageInfo.getPageNum());
        System.out.println("首页：" + pageInfo.getNavigateFirstPage());
        System.out.println("上一页：" + pageInfo.getPrePage());
        System.out.println("下一页：" + pageInfo.getNextPage());
        System.out.println("尾页：" + pageInfo.getNavigateLastPage());
        System.out.println("总页数：" + pageInfo.getPages());
        System.out.println("总条数：" + pageInfo.getTotal());
        System.out.println(String.valueOf(pageInfo.getSize()));
        return success(pageInfo);
    }

    @GetMapping("/delete_database_id")
    public ResponseVO deleteDataBase(String fileId,String fileName){
          service.deleteDataBase(fileId);
          deleteFile(fileName);
        return success("删除成功");
    }



    public void deleteFile(String fileName){

        File folder = new File(FILE_NAME);
        File[] files = folder.listFiles();
        for(File file:files){
            if(file.getName().equals(fileName)){
                file.delete();
            }
        }
    }

    /**
     * 文件下载
     * @param request
     * @param fileName
     * @return
     */
    @GetMapping("/file_downloads")
    public ResponseEntity<byte[]>  download(HttpServletRequest request, String fileName) throws Exception {


       //处理中文编码
        String myFileName=new String(fileName.getBytes("utf-8"),"iso-8859-1");
        //构建下载的文件对象4
        String downlName = FILE_NAME + File.separator + fileName;
        System.out.println("文件下载："+downlName);

        //设置头信息
        HttpHeaders headers=new HttpHeaders();

        //设置下载的附件 (myFileName必须处理中文名称哦!)
        headers.setContentDispositionFormData("attachment", myFileName);

        //设置MIME类型
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        //用FileUpload组件的FileUtils读取文件，并构建成ResponseEntity<byte[]>返回给浏览器
        //HttpStatus.CREATED是HTTP的状态码201
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(downlName)),headers,HttpStatus.CREATED);

    }


    @PostMapping("/insert_database")
    public ResponseVO insertDataBase(String name,String department, MultipartFile[] filename) throws IOException {
//        Student student = JSON.parseObject(jsonStr,Student.class);

        System.out.println("我进来了");
        System.out.println(name);
        System.out.println(department);
        DataBase base = new DataBase();
        //将文件上传到当前web项目部署的目录中，先获取部署目录

        List<String> fileNames = new ArrayList<>();
        //创建上传目录
        MyFileUtils.createUploadDir(FILE_NAME);
        for (MultipartFile file : filename) {
            //获取上传文件的文件名
            String fileName = file.getOriginalFilename();

            System.out.println("文件名"+fileName);
            //构建上传的文件
            File uploadFile = MyFileUtils.createUploadFile(FILE_NAME, fileName);
            //执行上传
            file.transferTo(uploadFile);
            //保存文件名

            base.setFileName(fileName);
            base.setFileDate(new Date());
            String uuid = UUID.randomUUID().toString().replace("-", "");
            base.setDid(uuid);
            base.setStuDepartment(department);
            base.setStuOperator(name);
            service.insertDataBase(base);

            fileNames.add(fileName);
        }


        return success("上传成功");
    }
}