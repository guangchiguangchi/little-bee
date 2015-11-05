package com.guangchiguangchi.littlebee.models;

import com.jfinal.plugin.activerecord.Model;

import java.util.List;

/**
 * 任务列表Model
 * Created by jiweibo on 15/11/2.
 */
public class TasksModel extends Model<TasksModel> {
    public static final TasksModel me = new TasksModel();

    /**
     * 通过用户id 得到该用户创建任务的列表
     * @param user_id 用户id
     * @return
     */
    public List<TasksModel> getCreatorTaskList (String user_id){
        List<TasksModel> tasklist = me.find("select bee_tasks.*,bee_projects.projectname as projectname,bee_users.username as username from bee_tasks join bee_projects on bee_tasks.project_id = bee_projects.id join bee_users on bee_tasks.assignee_id=bee_users.id where creator_id = ?",user_id);
        return tasklist;
    }

    /**
     * 通过用户id 得到指派给该用户的任务列表
     * @param user_id 用户id
     * @return
     */
    public List<TasksModel> getAssigneeTaskList (String user_id){
        List<TasksModel> tasklist = me.find("select bee_tasks.*,bee_projects.projectname as projectname,bee_users.username as username  from bee_tasks  join bee_projects on bee_tasks.project_id = bee_projects.id join bee_users on bee_tasks.creator_id=bee_users.id where status < 2 and assignee_id = ?",user_id);
        return tasklist;
    }
}
