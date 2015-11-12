package com.guangchiguangchi.littlebee.controllers;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guangchiguangchi.littlebee.common.Uitls;
import com.guangchiguangchi.littlebee.models.TasksModel;
import com.jfinal.core.Controller;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by zhangchen on 15-11-2.
 */
public class TasksController extends Controller {

    /**
     * 任务添加
     * 接口：/tasks/add
     * 参数：      uid
     *             project  项目ID
     *             userid   用户ID
     *             assignee 指派人ID
     *             title    标题
     *             content  内容
     *             spendtime    花费时间
     * 返回值：json
     */
    public void add() {
        /*String uid = getPara("uid");*/
        String cid = getPara("userid");
        /*if (StringUtils.isEmpty(uid)) {
            renderJson(Uitls.Ajax.failure("操作失败", ""));

        } else {*/
            String projectid = getPara("project");
            String assigneeid = getPara("assignee");
            String title = getPara("title");
            String content = getPara("content");
            String spendtimeStr = getPara("spendtime");

            if (StringUtils.isNoneBlank(title)) {
                if(title.trim().isEmpty()){
                    renderJson(Uitls.Ajax.failure("任务标题不能为空", ""));
                }
            }else{
                renderJson(Uitls.Ajax.failure("任务标题不能为空", ""));
            }

            float spendTime = 0f;
            try {
                spendTime = Float.valueOf(spendtimeStr);
            } catch (Exception ex) {
                renderJson(Uitls.Ajax.failure("类型转换失败", ""));
            }

            TasksModel task = new TasksModel();
            task.set("title", title);
            task.set("content", content);
            task.set("project_id", projectid);
            task.set("creator_id", cid);
            task.set("assignee_id", assigneeid);
            task.set("spendtime", spendTime);
            task.set("status", 0);
            task.set("create_time", Uitls.currentTime());

            boolean flag = task.save();
            if (flag) {
                renderJson(Uitls.Ajax.success("添加任务成功", ""));
            } else {
                renderJson(Uitls.Ajax.failure("添加任务失败", ""));
            }
        /*}*/
    }

    /**
     * 任务修改
     * 接口：/tasks/update
     * 参数：      uid
     *             taskid 任务ID
     *             project 项目ID
     *             assignee 指派人ID
     *             title 标题
     *             content  内容
     *             spendtime    花费时间
     */
    public void update() {
        /*String uid = getPara("uid");
        if (StringUtils.isEmpty(uid)) {
            renderJson(Uitls.Ajax.failure("操作失败", ""));

        } else {*/
            String taskid = getPara("taskid");
            TasksModel task = TasksModel.me.findById(taskid);

            String projectid = getPara("project");
            String assigneeid = getPara("assignee");
            String title = getPara("title");
            String content = getPara("content");
            String spendtimeStr = getPara("spendtime");


            if (title.trim().isEmpty()) {
                renderJson(Uitls.Ajax.failure("任务标题不能为空", ""));
            }

            float spendTime = 0f;
            try {
                spendTime = Float.valueOf(spendtimeStr);
            } catch (Exception ex) {
                renderJson(Uitls.Ajax.failure("类型转换失败", ""));
            }
            task.set("title", title);
            task.set("content", content);
            task.set("project_id", projectid);
            task.set("assignee_id", assigneeid);
            task.set("spendtime", spendTime);

            boolean flag = task.update();
            if (flag) {
                renderJson(Uitls.Ajax.success("修改任务成功", ""));
            } else {
                renderJson(Uitls.Ajax.failure("修改任务失败", ""));
            }
        /*}*/

    }

    /**
     * 修改任务状态状态
     * 接口：/tasks/changeTaskStatus
     * 参数：      uid
     *             taskid：  任务ID
     *             status：  任务状态
     */
    public void changeTaskStatus() {
        /*String uid = getPara("uid");
        if (StringUtils.isEmpty(uid)) {
            renderJson(Uitls.Ajax.failure("操作失败", ""));

        } else {*/
            String taskid = getPara("taskid");
            String statusStr = getPara("status");

            TasksModel task = TasksModel.me.findById(taskid);
            int status = 0;
            switch (statusStr) {
                case "开始":
                    status = 1;
                    task.set("start_time", Uitls.currentTime());
                    break;
                case "完成":
                    status = 2;
                    task.set("stop_time", Uitls.currentTime());
                    break;
                case "撤销":
                    status = 3;
                    task.set("stop_time", Uitls.currentTime());
                    break;
            }

            task.set("status", status);
            boolean flag = task.update();

            if (flag) {
                renderJson(Uitls.Ajax.success("操作成功", ""));
            } else {
                renderJson(Uitls.Ajax.failure("操作失败", ""));
            }
        /*}*/
    }

    /**
     * 删除
     */
    public void delete() {

    }

    /**
     * 列表
     */
    /*public void list() {
        String uid = getPara("uid");
        String cid = getPara("userid");
        if (StringUtils.isEmpty(uid)) {
            renderJson(Uitls.Ajax.failure("操作失败", ""));

        } else {
            List<UserModel> grouperList = null;
            //指派人列表
            if (Integer.parseInt(usermap.get("workgroup").toString()) < 10) {
                grouperList = UserModel.me.find("select * from bee_users");
            } else {
                grouperList = UserModel.me.find("select * from bee_users where bee_users.workgroup between ? and ?", usermap.get("workgroup"), Integer.parseInt(usermap.get("workgroup").toString()) + 9);
            }
            //产品列表
            List<ProjectModel> projectList = ProjectModel.me.find("select * from bee_projects");

            int listSize;

            List<TasksModel> creatorList = TasksModel.me.find("select bee_tasks.*,bee_projects.projectname as projectname,bee_users.username as username from bee_tasks join bee_projects on bee_tasks.project_id = bee_projects.id join bee_users on bee_tasks.assignee_id=bee_users.id where creator_id = ?", cid);
            List<TasksModel> assigneeList = TasksModel.me.find("select bee_tasks.*,bee_projects.projectname as projectname,bee_users.username as username  from bee_tasks  join bee_projects on bee_tasks.project_id = bee_projects.id join bee_users on bee_tasks.creator_id=bee_users.id where status < 2 and assignee_id = ?", cid);

            setAttr("groupusers", grouperList);
            setAttr("projectlist", projectList);
            setAttr("creatortasks", creatorList);
            setAttr("assigneetasks", assigneeList);

            setAttr("group", usermap.get("workgroup"));

            setAttr("islogin", 1);
            setAttr("rtnMsg", getAttr("rtnMsg"));
            render("task.html");
        }
    }*/

    /**
     * 获取用户所有的任务列表
     * 接口：/tasks/getTaskList
     * 参数：
     *             userid 用户ID
     * 返回值：json
     */
    public void getTaskList() {

        /*String uid = getPara("uid");*/
        String cid = getPara("userid");
        /*if (StringUtils.isEmpty(uid)) {
            renderJson(Uitls.Ajax.failure("操作失败", ""));

        }*/
        if (StringUtils.isEmpty(cid)) {
            renderJson(Uitls.Ajax.failure("用户ID为空", ""));
        }
        int listSize;

        List<TasksModel> creatorList = TasksModel.me.find("select bee_tasks.*,bee_projects.projectname as projectname,bee_users.username as username from bee_tasks join bee_projects on bee_tasks.project_id = bee_projects.id join bee_users on bee_tasks.assignee_id=bee_users.id where creator_id = ?", cid);
        List<TasksModel> assigneeList = TasksModel.me.find("select bee_tasks.*,bee_projects.projectname as projectname,bee_users.username as username  from bee_tasks  join bee_projects on bee_tasks.project_id = bee_projects.id join bee_users on bee_tasks.creator_id=bee_users.id where bee_tasks.status < 2 and assignee_id = ?", cid);

        JSONObject objJson = new JSONObject();

        JSONArray creatorArrJson = new JSONArray();
        listSize = creatorList.size();
        for (int i = 0; i < listSize; i++) {
            creatorArrJson.add(creatorList.get(i));
        }
        objJson.put("creator", creatorArrJson);

        JSONArray assigneeArrJson = new JSONArray();
        listSize = assigneeList.size();
        for (int i = 0; i < listSize; i++) {
            assigneeArrJson.add(assigneeList.get(i));
        }
        objJson.put("assigee", assigneeArrJson);

        renderJson(Uitls.Ajax.success("成功", objJson));
    }

    /**
     * 获取用户需要做的任务列表
     * 接口：/tasks/getAssigneeTaskList
     * 参数:
     *          用户ID  userid
     * 返回值：json
     *
     */
    public void getAssigneeTaskList() {
        /*String uid = getPara("uid");*/
        String cid = getPara("userid");
        /*if (StringUtils.isEmpty(uid)) {
            renderJson(Uitls.Ajax.failure("操作失败", ""));

        }*/
        if (StringUtils.isEmpty(cid)) {
            renderJson(Uitls.Ajax.failure("用户ID为空", ""));
        }
        int listSize;
        List<TasksModel> assigneeList = TasksModel.me.find("select bee_tasks.*,bee_projects.projectname as projectname,bee_users.username as username  " +
                "from bee_tasks  join bee_projects on bee_tasks.project_id = bee_projects.id join bee_users on bee_tasks.creator_id=bee_users.id " +
                "where bee_tasks.status < 2 and assignee_id = ?", cid);
        listSize = assigneeList.size();

        JSONObject objJson = new JSONObject();

        JSONArray assigneeArrJson = new JSONArray();
        for (int i = 0; i < listSize; i++) {
            assigneeArrJson.add(assigneeList.get(i));
        }
        objJson.put("assigee", assigneeArrJson);

        renderJson(Uitls.Ajax.success("成功", objJson));
    }
}
