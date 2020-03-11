package edu.nf.student.dao;

import edu.nf.student.entity.DataBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author admin
 * @date 2019/12/19
 * 资料存放表
 */
public interface DataBaseDao {
    /**
     * 存放资料表
     * @return
     */
    public List<DataBase> listDataBase(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);


    /**
     * 模糊查询
     * @param findValue
     * @return
     */
    public List<DataBase> getDataBaseId(String findValue, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);


    /**
     * 删除文件
     * @param fileId
     */
    public void deleteDataBase(String fileId);


    /**
     * 插入
     * @param dataBase
     */
    public void insertDataBase(DataBase dataBase);

}