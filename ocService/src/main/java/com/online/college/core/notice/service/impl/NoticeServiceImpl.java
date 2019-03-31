package com.online.college.core.notice.service.impl;

import com.online.college.common.page.TailPage;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.notice.dao.NoticeDao;
import com.online.college.core.notice.entity.Notice;
import com.online.college.core.notice.service.NoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName NoticeServiceImpl
 * @Description TODO
 * @Author like
 * @Data 2019/3/20 13:41
 * @Version 1.0
 **/
@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

    @Resource
    private NoticeDao noticeDao;


    @Override
    public boolean save(Notice notice) {
        try {
            if (notice.getId() != null) {
                noticeDao.update(notice);
            } else {
                noticeDao.save(notice);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void update(Notice notice) {
        noticeDao.update(notice);
    }

    @Override
    public List<Notice> queryAll() {

        return noticeDao.queryAll();
    }

    @Override
    public Notice queryById(Long id) {
        return noticeDao.queryById(id);
    }

    @Override
    public boolean delete(Long id) {
        try {
            noticeDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public TailPage<Notice> queryPage(Notice notice, TailPage<Notice> page) {
        Integer itemsTotalCount = noticeDao.getTotalItemsCount(notice);
        List<Notice> items = noticeDao.queryPage(notice, page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);
        return page;
    }
}
