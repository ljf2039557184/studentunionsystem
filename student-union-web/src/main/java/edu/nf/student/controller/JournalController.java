package edu.nf.student.controller;

import com.github.pagehelper.PageInfo;
import edu.nf.student.controller.vo.ResponseVO;
import edu.nf.student.entity.Journal;
import edu.nf.student.service.JournalService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.io.File;
import java.util.List;

/**
 * @author admin
 * @date 2019/12/25
 */
@RestController
public class JournalController extends BaceControler{
    private final static Logger logger = LoggerFactory.getLogger(JournalController.class);


    @Autowired
    private JournalService service;

    public static final String LOGBACK_NAME= "D:"+ File.separator + "upload"+File.separator +"logback";

    @GetMapping("/list_journal")
    public ResponseVO<PageInfo<Journal>> listJournal(Integer pageNum, Integer pageSize){
        List<Journal> list = service.listJournal(pageNum,pageSize);
        PageInfo<Journal> pageInfo = new PageInfo<>(list);
        return success(pageInfo);
    }


    @GetMapping("/get_journal")
    public ResponseVO<PageInfo<Journal>> getJournal(String findValue,Integer pageNum, Integer pageSize){
        List<Journal> list = service.getJournal(findValue,pageNum,pageSize);
        PageInfo<Journal> pageInfo = new PageInfo<>(list);
        return success(pageInfo);
    }

    @GetMapping("/delete_journal_id")
    public ResponseVO deleteDataBase(Integer fileId,String fileName){
        fileName = fileName+".log";
        service.deleteJournal(fileId);
        deleteFile(fileName);
        return success("删除成功");
    }

    public void deleteFile(String fileName){

        File folder = new File(LOGBACK_NAME);
        File[] files = folder.listFiles();
        for(File file:files){
            if(file.getName().equals(fileName)){
                file.delete();
            }
        }
    }

    /**
     * 日志文件下载
     * @param request
     * @param fileName
     * @return
     */
    @GetMapping("/logback_downloads")
    public ResponseEntity<byte[]> download(HttpServletRequest request, String fileName) throws Exception {

        fileName = fileName + ".log";
        //处理中文编码
        String myFileName=new String(fileName.getBytes("utf-8"),"iso-8859-1");
        //构建下载的文件对象4
        String downlName = LOGBACK_NAME + File.separator + fileName;
        System.out.println("文件下载："+downlName);

        //设置头信息
        HttpHeaders headers=new HttpHeaders();

        //设置下载的附件 (myFileName必须处理中文名称哦!)
        headers.setContentDispositionFormData("attachment", myFileName);

        //设置MIME类型
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        //用FileUpload组件的FileUtils读取文件，并构建成ResponseEntity<byte[]>返回给浏览器
        //HttpStatus.CREATED是HTTP的状态码201
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(downlName)),headers, HttpStatus.CREATED);

    }




}