package edu.nf.student.service.impl;

import edu.nf.student.dao.PositionDao;
import edu.nf.student.entity.Position;
import edu.nf.student.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 职位
 * @author admin
 * @date 2019/12/5
 */
@Service("positionService")
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionDao dao;

    @Override
    public List<Position> listPosition() {
        return dao.listPostition();
    }
}