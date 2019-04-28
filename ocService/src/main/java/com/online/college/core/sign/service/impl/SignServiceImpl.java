package com.online.college.core.sign.service.impl;

import com.online.college.common.page.TailPage;
import com.online.college.common.web.SessionContext;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.sign.dao.SignDao;
import com.online.college.core.sign.entity.Sign;
import com.online.college.core.sign.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SignServiceImpl
 * @Description TODO
 * @Author like
 * @Data 2019/4/27 19:30
 * @Version 1.0
 **/
@Service
public class SignServiceImpl implements SignService {

    @Resource
    private SignDao signDao;

    @Override
    public Sign selectById(Long id) {

        return signDao.selectById(id);

    }

    @Override
    public TailPage<Sign> selectSignForPage(Sign sign, TailPage<Sign> page) {
        Integer itemsTotalCount = signDao.getTotalItemsCount(sign);
        List<Sign> items = signDao.selectSignForPage(sign, page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);
        return page;
    }

    @Override
    public boolean isSign(Sign sign) {
        Sign sign1 = signDao.selectByTime(sign);
        if(sign1!=null){
            return true;
        }
        return  false;
    }

    @Override
    public boolean save(Sign sign) {

        try {
            signDao.save(sign);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
