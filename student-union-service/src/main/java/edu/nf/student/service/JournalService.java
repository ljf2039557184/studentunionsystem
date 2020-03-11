package edu.nf.student.service;

import edu.nf.student.entity.Journal;

import java.util.List;

/**
 * @author admin
 * @date 2019/12/25
 */
public interface JournalService {
    List<Journal> listJournal(Integer pageNum, Integer pageSize);

    List<Journal> getJournal(String findValue,Integer pageNum, Integer pageSize);

    void deleteJournal(Integer id);

    void addJournal(Journal journal);
}