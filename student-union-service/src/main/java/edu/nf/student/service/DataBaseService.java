package edu.nf.student.service;

import edu.nf.student.entity.DataBase;

import java.util.List;

/**
 * @author admin
 * @date 2019/12/19
 * 资料文件
 */
public interface DataBaseService {
    List<DataBase> listDataBase(Integer pageNum, Integer pageSize);

    List<DataBase> getDataBase(String findValue,Integer pageNum, Integer pageSize);

    void deleteDataBase(String fileId);

    void insertDataBase(DataBase dataBase);
}