package edu.nf.student.controller;

import edu.nf.student.controller.vo.ResponseVO;
import edu.nf.student.entity.Position;
import edu.nf.student.service.PositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 职位
 * @author admin
 * @date 2019/12/5
 */
@RestController
public class PositionInfoController extends BaceControler{
    private final static Logger logger = LoggerFactory.getLogger(PositionInfoController.class);
    @Autowired
    private PositionService service;

    @GetMapping("/list_position")
    public ResponseVO listPosition(){
        List<Position> list = service.listPosition();
        return success(list);
    }
}