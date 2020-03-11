package edu.nf.student.dao;

import edu.nf.student.entity.DataBase;
import edu.nf.student.entity.Journal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author admin
 * @date 2019/12/25
 */
public interface JournalDao {
    List<Journal> listJournal(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);


    /**
     * 模糊查询
     * @param findValue
     * @return
     */
    public List<Journal> getJournal(String findValue, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    public void deleteJounal(Integer id);

    public void addJounal(Journal journal);

}