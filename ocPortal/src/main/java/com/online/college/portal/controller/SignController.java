package com.online.college.portal.controller;

import com.online.college.common.web.JsonView;
import com.online.college.common.web.SessionContext;
import com.online.college.core.sign.entity.Sign;
import com.online.college.core.sign.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName SignController
 * @Description TODO
 * @Author like
 * @Data 2019/4/28 14:17
 * @Version 1.0
 **/
@Controller
@RequestMapping("/sign")
public class SignController {

    @Autowired
    private SignService signService;

    @ResponseBody
    @RequestMapping(value = "/signIn")
    public String toSignIn(){
       String userName = SessionContext.getUsername();
       Sign sign = new Sign();
       sign.setStuId(userName);
        boolean flag = signService.isSign(sign);
        if(flag){
            return new JsonView(0).toString();
        }
       boolean bo = signService.save(sign);
       if(bo){
           return new JsonView(0).toString();
       }else {
           return new JsonView(1, "签到失败", null).toString();
       }

    }


}
