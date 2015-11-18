package com.guangchiguangchi.littlebee.controllers;

import com.jfinal.core.Controller;

/**
 * Created by zhangchen on 15-11-18.
 */
public class HomeController extends Controller{

    public void index(){
        renderJsp("index.html");
    }

}
