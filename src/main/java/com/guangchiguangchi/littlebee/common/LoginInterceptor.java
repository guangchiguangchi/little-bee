package com.guangchiguangchi.littlebee.common;

import com.guangchiguangchi.littlebee.models.UserModel;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

import java.util.List;

/**
 * Created by zhangchen on 15-11-18.
 */
public class LoginInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
        if(inv.getActionKey().equals("/")){
            inv.getController().render("/login/login.html");
        }else if(inv.getActionKey().equals("/users/login")){
            inv.invoke();
        }else {
            String uid = inv.getController().getPara("uid");
            if (uid != null) {
                List<UserModel> users = UserModel.me.find("select * from bee_users where uid=?", uid);
                if (users.size() == 1) {
                    inv.invoke();
                    return;
                }
            }
            inv.getController().renderText("{'issuccess':false,'data':{},'message':'身份验证失败,请重新登陆.'}");
        }
    }
}
