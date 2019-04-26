package com.online.college.core.work.service.impl;

import com.online.college.common.page.TailPage;
import com.online.college.common.web.SessionContext;
import com.online.college.core.auth.dao.AuthUserDao;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.work.dao.StudentWorkDao;
import com.online.college.core.work.entity.StudentHomeWork;
import com.online.college.core.work.entity.TeacherHomeWork;
import com.online.college.core.work.service.StudentWorkService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName StudentWorkService
 * @Description TODO
 * @Author like
 * @Data 2019/3/31 14:41
 * @Version 1.0
 **/
@Service
@Transactional
public class StudentWorkServiceImpl implements StudentWorkService {

    @Resource
    private StudentWorkDao studentWorkDao;

    @Resource
    private AuthUserDao authUserDao;

    @Override
    public TailPage<StudentHomeWork> selectStudentWorkForPage(StudentHomeWork studentHomeWork, TailPage<StudentHomeWork> page) {
        Integer itemsTotalCount = studentWorkDao.getTotalItemsCount(studentHomeWork);
        List<StudentHomeWork> items = studentWorkDao.selectStudentWorkForPage(studentHomeWork, page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);
        return page;
    }

    @Override
    public boolean save(StudentHomeWork studentHomeWork) {
        Long userId = SessionContext.getUserId();
        AuthUser authUser = authUserDao.getById(userId);
        if (authUser != null) {
            studentHomeWork.setStuId(authUser.getUsername());
        } else {
            return false;
        }
        try {
            if (studentHomeWork.getId() != null) {
                studentWorkDao.update(studentHomeWork);
            } else {
                studentWorkDao.save(studentHomeWork);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public void update(StudentHomeWork studentHomeWork) {

    }

    @Override
    public boolean delete(StudentHomeWork studentHomeWork) {
        try {
            studentWorkDao.delete(studentHomeWork);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public StudentHomeWork selectById(Long id) {
        return studentWorkDao.selectById(id);
    }

    @Override
    public StudentHomeWork selectByWorkIdAndUserId(Long workId, String stuId) {
        return studentWorkDao.selectByWorkIdAndUserId(workId,stuId);
    }

    @Override
    public boolean saveScore(StudentHomeWork studentHomeWork) {
        try{
            studentWorkDao.update(studentHomeWork);

        }catch (Exception e) {
            return false;
        }
        return true;
    }
}
