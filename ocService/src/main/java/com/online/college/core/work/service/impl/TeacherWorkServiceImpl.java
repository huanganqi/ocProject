package com.online.college.core.work.service.impl;

import com.online.college.common.page.TailPage;
import com.online.college.core.work.dao.TeacherWorkDao;
import com.online.college.core.work.entity.TeacherHomeWork;
import com.online.college.core.work.service.TeacherWorkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TeacherWorkServiceImpl
 * @Description TODO
 * @Author like
 * @Data 2019/3/31 14:41
 * @Version 1.0
 **/
@Service
@Transactional
public class TeacherWorkServiceImpl implements TeacherWorkService {

    @Resource
    private TeacherWorkDao teacherWorkDao;

    @Override
    public TailPage<TeacherHomeWork> selectTeacherWorkForPage(TeacherHomeWork teacherHomeWork, TailPage<TeacherHomeWork> page) {
        Integer itemsTotalCount = teacherWorkDao.getTotalItemsCount(teacherHomeWork);
        List<TeacherHomeWork> items = teacherWorkDao.selectTeacherWorkForPage(teacherHomeWork, page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);
        return page;
    }

    @Override
    public boolean save(TeacherHomeWork teacherHomeWork) {
        try {
            if (teacherHomeWork.getId() != null) {
                teacherWorkDao.update(teacherHomeWork);
            } else {
                teacherWorkDao.save(teacherHomeWork);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void update(TeacherHomeWork teacherHomeWork) {

    }

    @Override
    public boolean delete(Long id) {
        try {
            teacherWorkDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public TeacherHomeWork selectById(Long id) {
        return teacherWorkDao.selectById(id);
    }
}
