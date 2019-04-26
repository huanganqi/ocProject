package com.online.college.core.file.service.impl;

import com.online.college.common.page.TailPage;
import com.online.college.core.file.dao.FileParentDao;
import com.online.college.core.file.entity.FileParent;
import com.online.college.core.file.service.FileParentService;
import com.online.college.core.work.entity.TeacherHomeWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName FileParentServiceImpl
 * @Description TODO
 * @Author like
 * @Data 2019/4/19 20:56
 * @Version 1.0
 **/
@Service
@Transactional
public class FileParentServiceImpl implements FileParentService {

    @Resource
    private FileParentDao fileParentDao;



    @Override
    public boolean save(FileParent fileParent) {
        try {
            if (fileParent.getId() != null) {
                fileParentDao.updateDirectory(fileParent);
            } else {
                fileParentDao.createDirectory(fileParent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delDirectory(Long id) {
        try {
            fileParentDao.delDirectory(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public FileParent findById(Long id) {
        return fileParentDao.findById(id);

    }

    @Override
    public TailPage<FileParent> findAllForPage(FileParent fileParent, TailPage<FileParent> page) {
        Integer itemsTotalCount = fileParentDao.getTotalItemsCount(fileParent);
        List<FileParent> items = fileParentDao.findAllForPage(fileParent, page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);
        return page;
    }
}
