package com.guangchiguangchi.littlebee.controllers;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guangchiguangchi.littlebee.common.Uitls;
import com.guangchiguangchi.littlebee.models.ProjectModel;
import com.guangchiguangchi.littlebee.models.TasksModel;
import com.guangchiguangchi.littlebee.models.UserModel;
import com.jfinal.core.Controller;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangchen on 15-11-2.
 */
public class TasksController  extends Controller {
    /**
     * 新增
     */
    public void add(){
        Map<String, Object> usermap =null;
        usermap = getSessionAttr("user");
        if(usermap==null){
            setAttr("islogin",0);
            setAttr("loginmsg","请登陆");
            render("task.html");

        }else {
            if(Integer.parseInt(usermap.get("group").toString())%10!=0){
                redirect("/tasks/list");
                return;
            }
            String rtnMsg = null;
            String projectid = getPara("project");
            String assigneeid = getPara("assignee");
            String title = getPara("title");
            String content = getPara("content");
            String spendtimeStr = getPara("spendtime");

            if (title.trim().isEmpty()) {
                redirect("/tasks/list");
                return;
            }

            float spendTime = 0f;
            try {
                spendTime = Float.valueOf(spendtimeStr);
            } catch (Exception ex) {
                redirect("/tasks/list");
                return;
            }

            TasksModel task = new TasksModel();
            task.set("title", title);
            task.set("content", content);
            task.set("project_id", projectid);
            task.set("creator_id", usermap.get("id"));
            task.set("assignee_id",assigneeid);
            task.set("spendtime",spendTime);
            task.set("status",0);
            task.set("create_time",LocalTime.now().toString());

            task.save();
            redirect("/tasks/list");
        }
    }

    /**
     * 修改
     */
    public void update(){

    }

    /**
     * 修改task状态
     */
    public void changeTaskStatus(){
        Map<String, Object> usermap =null;
        usermap = getSessionAttr("user");
        if(usermap==null){
            setAttr("islogin",0);
            setAttr("loginmsg","请登陆");
            render("task.html");
        }else {
            if(Integer.parseInt(usermap.get("group").toString())%10!=0){
                redirect("/tasks/list");
                return;
            }
            String taskid = getPara("taskid");
            String statusStr = getPara("status");

            TasksModel task = TasksModel.me.findById(taskid);
            int status = 0;
            switch (statusStr){
                case "开始":
                    status=1;
                    break;
                case "完成":
                    status=2;
                    break;
                case "撤销":
                    status=3;
                    break;
            }

            task.set("status",status);
            task.update();

            redirect("/tasks/list");
        }
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
        Map<String, Object> usermap =null;
            usermap = getSessionAttr("user");
        if(usermap==null){
            setAttr("islogin",0);
            setAttr("loginmsg","请登陆");
            render("task.html");

        }else {
            List<UserModel> grouperList =null;
            //指派人列表
            if(Integer.parseInt(usermap.get("group").toString())<10) {
                grouperList = UserModel.me.find("select * from bee_users");
            }else{
                grouperList = UserModel.me.find("select * from bee_users where bee_users.group between ? and ?", usermap.get("group"), Integer.parseInt(usermap.get("group").toString()) + 9);
            }
            //产品列表
            List<ProjectModel> projectList = ProjectModel.me.find("select * from bee_projects");

            int listSize;

            List<TasksModel> creatorList = TasksModel.me.getCreatorTaskList(usermap.get("id").toString());
            List<TasksModel> assigneeList = TasksModel.me.getAssigneeTaskList(usermap.get("id").toString());

            setAttr("groupusers",grouperList);
            setAttr("projectlist",projectList);
            setAttr("creatortasks", creatorList);
            setAttr("assigneetasks", assigneeList);

            setAttr("group",usermap.get("group"));

            setAttr("islogin",1);
            setAttr("rtnMsg",getAttr("rtnMsg"));
            render("task.html");
        }
    }

    /**
     * 得到用户相关的所有任务
     * 参数: id  用户id
     */
    public void getTaskList(){
        String cid = getPara("userid");
        int listSize ;

        List<TasksModel> creatorList = TasksModel.me.getCreatorTaskList(cid);
        List<TasksModel> assigneeList = TasksModel.me.getAssigneeTaskList(cid);

        JSONObject objJson = new JSONObject();

        JSONArray creatorArrJson = new JSONArray();
        listSize = creatorList.size();
        for(int i = 0;i<listSize;i++){
            creatorArrJson.add(creatorList.get(i));
        }
        objJson.put("creator",creatorArrJson);

        JSONArray assigneeArrJson = new JSONArray();
        listSize = assigneeList.size();
        for(int i = 0;i<listSize;i++){
            assigneeArrJson.add(assigneeList.get(i));
        }
        objJson.put("assigee",assigneeArrJson);

        renderJson(Uitls.Ajax.success("成功", objJson));
    }

    /**
     * 得到用户相关的所有任务
     * 参数: id  用户id
     */
    public void getAssigneeTaskList(){
        String cid = getPara("userid");
        int listSize ;

        List<TasksModel> assigneeList = TasksModel.me.getAssigneeTaskList(cid);
        listSize = assigneeList.size();

        JSONObject objJson = new JSONObject();

        JSONArray assigneeArrJson = new JSONArray();
        for(int i = 0;i<listSize;i++){
            assigneeArrJson.add(assigneeList.get(i));
        }
        objJson.put("assigee",assigneeArrJson);

        renderJson(Uitls.Ajax.success("成功", objJson));
    }
}
