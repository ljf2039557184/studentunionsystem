package edu.nf.student.dao;

import edu.nf.student.entity.Position;

import java.util.List;

/**
 * 职位信息
 * @author admin
 * @date 2019/12/5
 */
public interface PositionDao {

    List<Position> listPostition();
}