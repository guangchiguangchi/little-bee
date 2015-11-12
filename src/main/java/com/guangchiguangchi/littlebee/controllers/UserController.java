package com.guangchiguangchi.littlebee.controllers;

import com.guangchiguangchi.littlebee.models.UserModel;
import com.jfinal.core.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangchen on 15-11-2.
 */
public class UserController extends Controller{
    /**
     * 新增
     */
    public void add(){

    }

    /**
     * 修改
     */
    public void update(){

    }

    /**
     * 删除
     */
    public void delete(){

    }

    /**
     * 列表
     */
    public void list(){

    }

    public void weblogin(){
        String username=getPara("username");
        String password=getPara("password");

        String msg="";
        Map<String,Object> data=new HashMap<>();
        boolean success=false;
        if("".equals(username)||username==null||"".equals(password)||password==null){
            msg="用户名或者密码不能为空！";
        }
        else{
            String sql="SELECT m.id,m.`password`,m.username,m.workgroup FROM bee_users as m WHERE m.username'"+username+"' and m.password='"+password+"' ";
            UserModel manager= UserModel.me.findFirst(sql);
            if(manager!=null){
                int t=manager.get("id");
                data.put("id",String.valueOf(t));
                data.put("workgroup",manager.get("workgroup"));
                success=true;
            }
            else
            {
                msg="用户名或者密码错误！请重新输入！";
            }
        }
        if(success){
            setAttr("islogin",1);
            setSessionAttr("user",data);
            redirect("/tasks/list");
        }else{
            setAttr("islogin",0);
            setAttr("loginmsg",msg);
            render("/tasks/task.html");
        }
    }
    /**
     * 登录接口
     * 参数: phonenumber 客户手机号
     *      password 密码
     * 返回值: 用户id
     */
    public void login()
    {
        String username=getPara("username");
        String password=getPara("password");

        String msg="";
        Map<String,Object> data=new HashMap<>();
        boolean success=false;
        if("".equals(username)||username==null||"".equals(password)||password==null){
            msg="用户名或者密码不能为空！";
        }
        else{
            String sql="select m.id,m.username,m.password,m.workgroup  from bee_users m where m.username='"+username+"' and m.password='"+password+"' ";
            UserModel manager= UserModel.me.findFirst(sql);
            if(manager!=null){
                int t=manager.get("id");
                data.put("id",String.valueOf(t));
                data.put("group",manager.get("group"));
                success=true;
            }
            else
            {
                msg="用户名或者密码错误！请重新输入！";
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("issuccess",success);
        map.put("message",msg);
        map.put("data",data);
        renderJson(map);
    }
}
