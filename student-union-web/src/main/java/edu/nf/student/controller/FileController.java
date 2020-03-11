package edu.nf.student.controller;


import edu.nf.student.controller.util.MyFileUtils;
import edu.nf.student.controller.vo.ResponseVO;
import edu.nf.student.entity.Student;
import edu.nf.student.service.StudentService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangl
 * @date 2019/11/5
 */
@Controller
public class FileController extends BaceControler{


    private final static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private StudentService service;

    /**
     *  普通请求上传
     * 使用MultipartFile对象，
     * spring将上传的文件信息都封装在这个对象中，
     * 上传单个文件只需要定义一个MultipartFile对象即可，
     * 如果是多文件，那么定义为MultipartFile[]
     * @param files
     * @param content
     * @param request
     * @param model
     * @return
     * @throws IOException
     */
    @PostMapping("/fileupload")
    public String upload(MultipartFile[] files, String content, HttpServletRequest request, Model model) throws IOException {
        //将文件上传到当前web项目部署的目录中，先获取部署目录
        String uploadPath = request.getServletContext().getRealPath(File.separator + "upload");
        List<String> fileNames = new ArrayList<>();
        MyFileUtils.createUploadDir(uploadPath);
        for (MultipartFile file : files) {
            //获取上传文件的文件名
            String fileName = file.getOriginalFilename();
            fileName = MyFileUtils.newFileName(fileName);
            File uploadFile = MyFileUtils.createUploadFile(uploadPath, fileName);
            //执行上传
            file.transferTo(uploadFile);
            //保存文件名
            fileNames.add(fileName);
        }
        //将文件名保存到model中
        model.addAttribute("fileNames", fileNames);
        //将文件名批量保存到数据库
        //.........
        //转发到download.jsp
        return "download";
    }

    /**
     * ajax上传
     * @param files
     * @param content
     * @param request
     * @param model
     * @return
     * @throws IOException
     */
    @PostMapping("/ajaxupload")
    @ResponseBody
    public ResponseVO<List<String>> ajaxUpload(MultipartFile[] files, String sid,String content, HttpServletRequest request, Model model) throws IOException {
        //将文件上传到当前web项目部署的目录中，先获取部署目录
        String uploadPath = "D:"+File.separator + "upload";
        List<String> fileNames = new ArrayList<>();
        //创建上传目录
        MyFileUtils.createUploadDir(uploadPath);
        for (MultipartFile file : files) {
            //获取上传文件的文件名
            String fileName = file.getOriginalFilename();
            fileName = MyFileUtils.newFileName(fileName);
            //构建上传的文件
            File uploadFile = MyFileUtils.createUploadFile(uploadPath, fileName);
            //执行上传
            file.transferTo(uploadFile);
            //保存文件名
            Student s = new Student();

            s.setStuImage(fileName);
            s.setSid(Integer.valueOf(sid));
            service.updateStudentImage(s);
            fileNames.add(fileName);
        }
//        System.out.println(uploadPath);
        //将文件名批量保存到数据库
        //.........
        return success(fileNames);
    }

    /**
     * 文件下载
     * @param request
     * @param fileName
     * @return
     */
    @GetMapping("/file_download")
    public ResponseEntity<InputStreamResource> download(HttpServletRequest request, String fileName){
        //构建下载路径
        String downloadPath = request.getServletContext().getRealPath(File.separator + "upload");
        //构建下载的文件对象
        File file = new File(downloadPath + File.separator + fileName);
        //创建响应头，设置响应信息
        HttpHeaders headers = new HttpHeaders();
        try {
            //对下载的文件名进行编码，防止中文乱码
            String headerFileName = URLEncoder.encode(fileName, "UTF-8");
            //设置响应的处理方式为附件的方式，并指定文件名
            headers.setContentDispositionFormData("attachment", headerFileName);
            //设置响应类型为application/octet-stream，也就是流类型
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //获取一个输入流对象，并传入下载的文件对象
            InputStream is = FileUtils.openInputStream(file);
            //创建InputStreamResource对象封装输入流，用于读取服务器的文件
            InputStreamResource isr = new InputStreamResource(is);
            //创建ResponseEntity对象，传入InputStreamResource、headers、HttpStatus.CREATED
            ResponseEntity<InputStreamResource> responseEntity = new ResponseEntity<>(isr, headers, HttpStatus.CREATED);
            //返回ResponseEntity对象
            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
