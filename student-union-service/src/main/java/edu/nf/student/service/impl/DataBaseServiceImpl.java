package edu.nf.student.service.impl;

import edu.nf.student.dao.DataBaseDao;
import edu.nf.student.entity.DataBase;
import edu.nf.student.service.DataBaseService;
import edu.nf.student.service.exception.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * @author admin
 * @date 2019/12/19
 */
@Service("dataBaseService")
public class DataBaseServiceImpl implements DataBaseService {

    @Autowired
    private DataBaseDao dao;

    /**
     * 返回全部数据
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<DataBase> listDataBase(Integer pageNum, Integer pageSize) {

        try {
            return  dao.listDataBase(pageNum,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("数据库后台错误");
        }
    }

    /**
     * 模糊查询
     * @param findValue
     * @return
     */
    @Override
    public List<DataBase> getDataBase(String findValue,Integer pageNum, Integer pageSize) {

        try {
            return  dao.getDataBaseId(findValue,pageNum, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("数据库后台错误");
        }
    }


    /**
     * 删除
     * @param fileId
     */
    @Override
    public void deleteDataBase(String fileId) {
        try {
            dao.deleteDataBase(fileId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("数据库后台错误");
        }
    }


    @Override
    public void insertDataBase(DataBase dataBase){
        try {
            dao.insertDataBase(dataBase);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("数据库错误");
        }
    }
}