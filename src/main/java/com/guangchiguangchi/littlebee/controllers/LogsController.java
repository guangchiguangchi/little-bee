package com.guangchiguangchi.littlebee.controllers;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guangchiguangchi.littlebee.common.Uitls;
import com.guangchiguangchi.littlebee.models.LogsModel;
import com.jfinal.core.Controller;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 日志控制类用来控制日志增删改查
 * Created by zhangchen on 15-11-2.
 */
public class LogsController  extends Controller {
    /**
     * 新增日志
     *
     * 接口： /logs/add
     *
     * 参数：
     *       user_id   用户id
     *       projectname 项目名称
     *       log       日志内容
     * 返回： json
     */
    public void add(){
        String user_id = getPara("user_id");
        String projectname = getPara("projectname");
        String log = getPara("log");

        if (user_id == null || log == null || user_id.trim().isEmpty() || log.trim().isEmpty()) {
            renderJson(Uitls.Ajax.failure("用户id和日志内容不能为空", ""));
            return;
        }
        if (StringUtils.isBlank(projectname)||("null".equals(projectname))){
            renderJson(Uitls.Ajax.failure("项目名称不能为空", ""));
            return;
        }
        LogsModel logsModel = new LogsModel();
        logsModel.set("user_id", user_id);
        logsModel.set("projectname",projectname);
        logsModel.set("log_time",Uitls.currentTime());
        logsModel.set("log", log);


        boolean flag = logsModel.save();
        if (flag) {
            renderJson(Uitls.Ajax.success("添加日志成功", ""));
        } else {
            renderJson(Uitls.Ajax.failure("添加日志失败", ""));
        }
    }

    /**
     * 修改日志
     *
     * 接口： /logs/update
     *
     * 参数：
     *       id             日志id
     *       user_id        用户id
     *       projectname    项目名称
     *       log            日志内容
     * 返回： json
     *//*
    public void update(){
        String id = getPara("id");
        String user_id = getPara("user_id");
        String projectname = getPara("projectname");
        String log = getPara("log");

        if (user_id == null || log == null || user_id.trim().isEmpty() || log.trim().isEmpty()) {
            renderJson(Uitls.Ajax.failure("用户id和日志内容不能为空", ""));
            return;
        }
        if (StringUtils.isBlank(projectname)||("null".equals(projectname))) {
            renderJson(Uitls.Ajax.failure("项目名称不能为空", ""));
            return;
        }
        LogsModel logsModel = new LogsModel();
        logsModel.set("id", id);
        logsModel.set("user_id", user_id);
        logsModel.set("projectname",projectname);
        logsModel.set("log", log);


        boolean flag = logsModel.update();
        if (flag) {
            renderJson(Uitls.Ajax.success("修改日志成功", ""));
        } else {
            renderJson(Uitls.Ajax.failure("修改日志失败", ""));
        }
    }

    *//**
     * 删除日志
     *
     * 接口： /logs/delete
     *
     * 参数：
     *       id    日志id
     * 返回： json
     *//*
    public void delete(){
        String id = getPara("id");
        LogsModel project = LogsModel.me.findById(id);


        boolean flag = project.delete();
        if (flag) {
            renderJson(Uitls.Ajax.success("删除成功", ""));
        } else {
            renderJson(Uitls.Ajax.failure("删除失败", ""));
        }

    }*/

    /**
     *  日志列表
     *
     *  接口： /logs/list
     *  返回： json
     */
    public void list(){
        List<LogsModel> logsList = null;
        logsList = LogsModel.me.find("select bee_logs.*,bee_tasks.title as title,bee_tasks.spendtime as spendtime," +
                " bee_projects.projectname as projectname,user.username as username from bee_logs join bee_tasks on bee_logs.taskid = bee_tasks.id " +
                " join bee_projects on bee_tasks.project_id = bee_projects.id join bee_users as user on bee_tasks.assignee_id = user.id  order by bee_logs.id ");

        int listSize = 0;
        listSize = logsList.size();
        JSONObject objJson = new JSONObject();
        JSONArray logArrJson = new JSONArray();
        for (int i = 0; i < listSize; i++) {
            logArrJson.add(logsList.get(i));
        }
        objJson.put("logs", logArrJson);
        renderJson(Uitls.Ajax.success("成功", objJson));
    }
}
