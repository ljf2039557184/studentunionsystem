package edu.nf.student.service;

import edu.nf.student.entity.Position;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 职位信息
 * @author admin
 * @date 2019/12/5
 */

public interface PositionService {
    List<Position> listPosition();
}