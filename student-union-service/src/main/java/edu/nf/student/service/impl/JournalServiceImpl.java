package edu.nf.student.service.impl;

import edu.nf.student.dao.JournalDao;
import edu.nf.student.entity.Journal;
import edu.nf.student.service.JournalService;
import edu.nf.student.service.exception.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 * @date 2019/12/25
 */
@Service("journalService")
public class JournalServiceImpl implements JournalService {
    @Autowired
    private JournalDao dao;

    @Override
    public List<Journal> listJournal(Integer pageNum, Integer pageSize) {
        try {
            return dao.listJournal(pageNum,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("数据库错误");
        }
    }

    @Override
    public List<Journal> getJournal(String findValue,Integer pageNum, Integer pageSize) {
        try {
            return dao.getJournal(findValue,pageNum,pageNum);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("数据库错误");
        }
    }

    @Override
    public void deleteJournal(Integer id) {
        try {
            dao.deleteJounal(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("数据库错误");
        }
    }

    @Override
    public void addJournal(Journal journal) {
        try {
            dao.addJounal(journal);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("数据库错误");
        }
    }
}