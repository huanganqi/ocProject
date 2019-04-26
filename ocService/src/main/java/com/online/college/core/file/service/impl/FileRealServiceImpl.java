package com.online.college.core.file.service.impl;

import com.online.college.common.page.TailPage;
import com.online.college.core.file.dao.FileRealDao;
import com.online.college.core.file.entity.FileReal;
import com.online.college.core.file.service.FileRealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;

/**
 * @ClassName FileRealServiceImpl
 * @Description TODO
 * @Author like
 * @Data 2019/4/19 23:32
 * @Version 1.0
 **/
@Service
@Transactional
public class FileRealServiceImpl implements FileRealService {

    @Resource
    private FileRealDao fileRealDao;


    @Override
    public TailPage<FileReal> selectForPage(FileReal fileReal, TailPage<FileReal> page) {
        Integer itemsTotalCount = fileRealDao.getTotalItemsCount(fileReal);
        List<FileReal> items = fileRealDao.selectForPage(fileReal, page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);
        return page;
    }

    @Override
    public boolean save(FileReal fileReal) {

        try {
            if (fileReal.getId() != null) {
                fileRealDao.update(fileReal);
            } else {
                fileRealDao.save(fileReal);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public void update(FileReal fileReal) {

    }

    @Override
    public boolean delete(FileReal fileReal) {
        try {
            fileRealDao.delete(fileReal);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public FileReal selectById(Long id) {
        return fileRealDao.selectById(id);
    }

    @Override
    public FileReal selectByParentId(Long fileParentId) {
        return fileRealDao.selectByParentId(fileParentId);
    }
}
