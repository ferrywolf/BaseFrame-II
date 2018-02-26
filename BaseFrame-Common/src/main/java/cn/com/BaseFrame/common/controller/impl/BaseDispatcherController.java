package cn.com.BaseFrame.common.controller.impl;

import cn.com.BaseFrame.common.controller.IBaseController;
import org.springframework.stereotype.Controller;

/**
 * 返回基础类
 *
 * @author xyh
 * @create 2018-02-23 22:18
 **/
public class BaseDispatcherController implements IBaseController {

    public BaseDispatcherController() {
        super();
    }

    private String path = "";

    //返回到jsp页面
    public String forwardJsp(String jspName) {
        //拼接页面链接
        return jspName;
    }
}
