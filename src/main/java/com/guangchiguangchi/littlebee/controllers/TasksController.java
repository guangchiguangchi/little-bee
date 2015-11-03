package com.guangchiguangchi.littlebee.controllers;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guangchiguangchi.littlebee.common.Uitls;
import com.guangchiguangchi.littlebee.models.TasksModel;
import com.jfinal.core.Controller;

import java.util.List;

/**
 * Created by zhangchen on 15-11-2.
 */
public class TasksController  extends Controller {
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

        render("task.html");
    }

    /**
     * 得到用户相关的所有任务
     * 参数: id  用户id
     */
    public void getTaskList(){
        String cid = getPara("id");
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
        objJson.put("assigee",creatorArrJson);

        renderJson(Uitls.Ajax.success("成功", objJson));
    }
}
