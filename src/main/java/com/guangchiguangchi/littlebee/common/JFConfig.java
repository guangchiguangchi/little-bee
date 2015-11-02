package com.guangchiguangchi.littlebee.common;

import com.guangchiguangchi.littlebee.controllers.AdminController;
import com.jfinal.config.*;
import com.jfinal.render.ViewType;

/**
 * Created by jiweibo on 15/11/02.
 */
public class JFConfig extends JFinalConfig{
    @Override
    public void configConstant(Constants me) {
        me.setDevMode(true);
        me.setViewType(ViewType.JSP);
    }

    @Override
    public void configRoute(Routes me) {
        loadPropertyFile("a_little_config.txt");
        me.add("/", AdminController.class, "/index");
    }

    @Override
    public void configPlugin(Plugins me) {

    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {

    }
}
