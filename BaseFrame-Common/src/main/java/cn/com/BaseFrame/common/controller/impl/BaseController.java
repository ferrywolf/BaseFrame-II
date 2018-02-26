package cn.com.BaseFrame.common.controller.impl;

import cn.com.BaseFrame.BaseUtils.BeanUtils.BeanUtils;
import cn.com.BaseFrame.common.pojo.BaseParamModel;
import cn.com.BaseFrame.common.pojo.BaseResultModel;
import cn.com.BaseFrame.common.service.IBaseService;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * controller基础类
 *
 * @author xyh
 * @create 2018-02-23 22:18
 **/
public class BaseController extends BaseDispatcherController {
    public BaseResultModel invokeMethod(Class clazz, String methodName, BaseParamModel paramModel) throws IllegalAccessException, ClassNotFoundException, InstantiationException {

        //获取到实体类
        IBaseService service = (IBaseService) BeanUtils.getBean("IBaseService");

        //在执行service中的方法之前执行一些方法
        System.out.print("方法执行之前执行");
        try {
            //获取到对应的方法
            Method method = clazz.getMethod(methodName,paramModel.getClass());

            try {
                BaseResultModel resultModel = (BaseResultModel) method.invoke(service,paramModel.getClass());
                return resultModel;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.print("方法执行完成执行");
        return null;
    }
}
