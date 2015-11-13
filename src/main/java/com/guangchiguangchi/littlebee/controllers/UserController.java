package com.guangchiguangchi.littlebee.controllers;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guangchiguangchi.littlebee.common.Uitls;
import com.guangchiguangchi.littlebee.models.UserModel;
import com.jfinal.core.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangchen on 15-11-2.
 */
public class UserController extends Controller{
    /**
     * 新增用户
     *
     * 接口： /users/add
     *
     * 参数：
     *       username   用户名
     *       password   用户密码
     *       group      用户组
     * 返回： json
     */
    public void add(){
        String username = getPara("username");
        String password = getPara("password");
        String group = getPara("group");

        if (username == null || password == null||group == null || username.trim().isEmpty() || password.trim().isEmpty()||group.trim().isEmpty()) {
            renderJson(Uitls.Ajax.failure("用户名，密码和分组不能为空", ""));
            return;
        }
        UserModel userModel = new UserModel();
        userModel.set("username", username);
        userModel.set("password", password);
        userModel.set("workgroup", group);


        boolean flag = userModel.save();
        if (flag) {
            renderJson(Uitls.Ajax.success("添加用户成功", ""));
        } else {
            renderJson(Uitls.Ajax.failure("添加用户失败", ""));
        }
    }

    /**
     * 修改用户
     *
     * 接口:  /users/update
     *
     * 参数：
     *       id         用户id
     *       username   用户名
     *       password   用户密码
     *       group      用户组
     * 返回： json
     */
    public void update(){
        String id = getPara("id");
        String username = getPara("username");
        String password = getPara("password");
        String group = getPara("group");

        if (username == null || password == null||group == null || username.trim().isEmpty() || password.trim().isEmpty()||group.trim().isEmpty()) {
            renderJson(Uitls.Ajax.failure("用户名，密码和分组不能为空", ""));
            return;
        }
        UserModel userModel = new UserModel();
        userModel.set("id", id);
        userModel.set("username", username);
        userModel.set("password", password);
        userModel.set("workgroup",group);


        boolean flag = userModel.update();
        if (flag) {
            renderJson(Uitls.Ajax.success("修改用户成功", ""));
        } else {
            renderJson(Uitls.Ajax.failure("修改用户失败", ""));
        }

    }

    /**
     * 删除
     *
     * 接口:  /users/delete
     *
     * 参数：
     *        id         用户id
     *
     * 返回： json
     */
    public void delete(){
        String id = getPara("id");
        UserModel userModel = UserModel.me.findById(id);


        boolean flag = userModel.delete();
        if (flag) {
            renderJson(Uitls.Ajax.success("删除用户成功", ""));
        } else {
            renderJson(Uitls.Ajax.failure("删除用户失败", ""));
        }
    }

    /**
     * 用户列表
     *
     * 接口:  /users/list
     *
     * 返回： json
     */
    public void list(){
        List<UserModel> userList = null;
        userList = UserModel.me.find("select * from bee_users");
        int listSize = 0;
        listSize = userList.size();
        JSONObject objJson = new JSONObject();
        JSONArray userArrJson = new JSONArray();
        for (int i = 0; i < listSize; i++) {
            userArrJson.add(userList.get(i));
        }
        objJson.put("users", userArrJson);
        renderJson(Uitls.Ajax.success("成功", objJson));
    }


    /**
     * 登录接口
     * 参数: username 用户名
     *      password 密码
     */
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
        String username=getPara("phonenumber");
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
