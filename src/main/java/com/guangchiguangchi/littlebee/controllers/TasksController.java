package com.guangchiguangchi.littlebee.controllers;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guangchiguangchi.littlebee.common.Uitls;
import com.guangchiguangchi.littlebee.models.LogsModel;
import com.guangchiguangchi.littlebee.models.ProjectModel;
import com.guangchiguangchi.littlebee.models.TasksModel;
import com.guangchiguangchi.littlebee.models.UserModel;
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

    public void projectAndUser() {
        String userid = getPara("userid");
        JSONObject objJson = new JSONObject();
        if (StringUtils.isNotBlank(userid) && !("null".equals(userid))) {
            TasksModel task = TasksModel.me.findById(userid);
            Integer taskId = task.get("id");
            objJson.put("taskId", taskId);
            String taskTitle = task.get("title");
            objJson.put("taskTitle", taskTitle);
            setAttr("taskTitle", taskTitle);
            String taskContent = task.get("content");
            objJson.put("taskContent", taskContent);
            Integer taskSpendTime = task.get("spendtime");
            objJson.put("taskSpendTime", taskSpendTime);
        }
        List<UserModel> userList = null;
        userList = UserModel.me.find("select id,username from bee_users");

        List<ProjectModel> projectList = null;
        projectList = ProjectModel.me.find("select * from bee_projects");

        int listSize;

        JSONArray userArr = new JSONArray();
        listSize = userList.size();
        for (int i = 0; i < listSize; i++) {
            userArr.add(userList.get(i));
        }
        JSONArray projectArr = new JSONArray();
        listSize = projectList.size();
        for (int i = 0; i < listSize; i++) {
            projectArr.add(projectList.get(i));
        }
        objJson.put("users", userArr);
        objJson.put("projects", projectArr);

        renderJson(Uitls.Ajax.success("成功", objJson));
    }

    /**
     * 任务添加
     * 接口：/tasks/editTask
     * 参数：      uid
     * project  项目ID
     * userid   用户ID
     * assignee 指派人ID
     * title    标题
     * content  内容
     * spendtime    花费时间
     * 返回值：json
     */
    public void editTask() {
        /*String uid = getPara("uid");*/
        String cid = getPara("userid");
        /*if (StringUtils.isEmpty(uid)) {
            renderJson(Uitls.Ajax.failure("操作失败", ""));

        } else {*/
        TasksModel task = null;
        String taskid = getPara("taskid");
        if (StringUtils.isNoneBlank(taskid)) {
            task = TasksModel.me.findById(taskid);
        } else {
            task = new TasksModel();
        }

        String projectid = getPara("project");
        String assigneeid = getPara("assignee");
        String title = getPara("title");
        String content = getPara("content");
        String spendtimeStr = getPara("spendtime");

        if (StringUtils.isNoneBlank(title)) {
            if (title.trim().isEmpty()) {
                renderJson(Uitls.Ajax.failure("任务标题不能为空", ""));
            }
        } else {
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
        task.set("creator_id", cid);
        task.set("assignee_id", assigneeid);
        task.set("spendtime", spendTime);
        task.set("status", 0);
        task.set("create_time", Uitls.currentTime());

        boolean flag = false;
        if(StringUtils.isNoneBlank(taskid)){
            flag = task.update();
        }else{
            flag = task.save();
        }
        if (flag) {
            renderJson(Uitls.Ajax.success("添加任务成功", ""));
        } else {
            renderJson(Uitls.Ajax.failure("添加任务失败", ""));
        }
        /*}*/
    }

    /**
     * 修改任务状态状态
     * 接口：/tasks/changeTaskStatus
     * 参数：      uid
     * taskid：  任务ID
     * status：  任务状态
     */
    public void changeTaskStatus() {
        /*String uid = getPara("uid");
        if (StringUtils.isEmpty(uid)) {
            renderJson(Uitls.Ajax.failure("操作失败", ""));

        } else {*/
        String taskid = getPara("taskid");
        String statusStr = getPara("status");
        Integer status = Integer.parseInt(statusStr);
        TasksModel task = TasksModel.me.findById(taskid);
        LogsModel log = new LogsModel();
        switch (status) {
            case 0:
                status = 1;
                task.set("start_time", Uitls.currentTime());
                log.set("log","开始任务");
                break;
            case 1:
                status = 2;
                task.set("stop_time", Uitls.currentTime());
                log.set("log","完成任务");
                break;
            case 2:
                status = 3;
                task.set("stop_time", Uitls.currentTime());
                log.set("log","撤销任务");
                break;
            default:
                renderJson(Uitls.Ajax.failure("状态不存在", ""));
        }
        log.set("taskid",taskid);
        log.save();
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
     * userid 用户ID
     * 返回值：json
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

        List<TasksModel> creatorList = TasksModel.me.find("select bee_tasks.*,bee_projects.projectname as projectname,users.username as creatorname, " +
                " user.username as assigneename " +
                " from bee_tasks" +
                " join bee_projects on bee_tasks.project_id = bee_projects.id " +
                " join bee_users as users on bee_tasks.creator_id = users.id " +
                " join bee_users as user on bee_tasks.assignee_id = user.id " +
                " where creator_id = ? order by bee_tasks.id", cid);

        JSONObject objJson = new JSONObject();

        JSONArray creatorArrJson = new JSONArray();
        listSize = creatorList.size();
        for (int i = 0; i < listSize; i++) {
            creatorArrJson.add(creatorList.get(i));
        }
        objJson.put("data", creatorArrJson);

        renderJson(Uitls.Ajax.success("成功", objJson));
    }

    /**
     * 获取用户需要做的任务列表
     * 接口：/tasks/getAssigneeTaskList
     * 参数:
     * 用户ID  userid
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
        List<TasksModel> assigneeList = TasksModel.me.find("select bee_tasks.*,bee_projects.projectname as projectname,users.username as creatorname, " +
                " user.username as assigneename" +
                " from bee_tasks  " +
                " join bee_projects on bee_tasks.project_id = bee_projects.id " +
                " join bee_users as users on bee_tasks.creator_id = users.id " +
                " join bee_users as user on bee_tasks.assignee_id = user.id " +
                " where bee_tasks.status < 2 and assignee_id = ? order by bee_tasks.id", cid);
        listSize = assigneeList.size();

        JSONObject objJson = new JSONObject();

        JSONArray assigneeArrJson = new JSONArray();
        for (int i = 0; i < listSize; i++) {
            assigneeArrJson.add(assigneeList.get(i));
        }
        objJson.put("data", assigneeArrJson);

        renderJson(Uitls.Ajax.success("成功", objJson));
    }
}
