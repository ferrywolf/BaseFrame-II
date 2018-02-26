package cn.com.BaseFrame.BaseUtils.BeanUtils;

import cn.com.BaseFrame.BaseUtils.StringUtils.StringUtils;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * POJO工具类
 *
 * @author xyh
 * @create 2018-02-22 12:35
 **/
public class BeanUtils implements Serializable{
    private static Object target;
    
    /**
     *  @Description: 获取到某个类的实例化
     *  @author xyh
     *  @Date 13:38 2018/2/22
     *  @method getBean
     *  params  clazz 需要获取实例化的class
     *  @return 单例的实例化
     *  @exception InstantiationException,IllegalAccessException
     **/
    public static Object getBean(Class clazz){
        return compare(target,clazz);
    }
    
    /**
     *  @Description: 获取到某个类的实例化
     *  @author xyh
     *  @Date 13:39 2018/2/22
     *  @method getBean
     *  params  className 需要获取实例化的类名
     *  @return 单例的实例化
     *  @exception InstantiationException, IllegalAccessException, ClassNotFoundException
     **/
    public static Object getBean(String className){
        try {
            Class clazz = Class.forName(className);
            return compare(target,clazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    /**
     *  @Description: 判断需要实例化的对象是否已经存在
     *  @author xyh
     *  @Date 13:39 2018/2/22
     *  @method compare
     *  params  target 目标对象,clazz 需要实例化的class
     *  @return 实例化对象
     *  @exception 
     **/
    public static Object compare(Object target,Class clazz) {
        String className = clazz.getName();
        //判断这个对象是不是已经存在,如果存在,那么返回,不存在则新建对象
        try {
            if (target != null) {
                if (className.equals(target.getClass().getName())) {
                    //判断是不是同一个对象,是则返回
                    return target;
                } else {
                    return clazz.newInstance();
                }
            }else{
                return clazz.newInstance();
            }
        } catch(InstantiationException e){
            throw new RuntimeException(e.getMessage());
        } catch(IllegalAccessException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    
    /**
     *  @Description: 复制
     *  @author xyh
     *  @Date 13:40 2018/2/22
     *  @method coprProperties
     *  params  sourceModel 源对象,targetModel 目标对象,flag 判断如果目标对象原来有值,是否需要继续赋值
     *  @return 目标对象
     *  @exception 
     **/
    public static Object coprProperties(Object sourceModel,Object targetModel,boolean flag){
        Class sourceClass = sourceModel.getClass();
        Class targetClass = targetModel.getClass();

        Field[] sourceFields = sourceClass.getFields();

        for(Field sourceField : sourceFields) {
            String fieldName = sourceField.getName();
            Class fieldType = sourceField.getType();

            String getMethodName = "get" + StringUtils.subString(fieldName);
            String setMethodName = "set" + StringUtils.subString(fieldName);

            try {
                Method getMethod = sourceClass.getMethod(getMethodName);

                Method setMethod = targetClass.getMethod(setMethodName,fieldType); //根据类型去查找,如果类型不对,那么就不赋值

                //如果找到了,那么就执行
                try {
                    if(flag) {
                        //判断原来的是否有值,有值那么就不赋值,跳过
                        if(getMethod.invoke(targetModel) != null) {
                            continue;
                        }else {
                            setMethod.invoke(targetModel,getMethod.invoke(sourceModel));
                        }
                    }else {
                        setMethod.invoke(targetModel,getMethod.invoke(sourceModel));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                //如果没找到
                System.out.print("方法在执行中出问题 : " + e.getMessage());
                continue; //跳过这次循环,进行下一次
            }
        }
        return targetModel;
    }

    public static Object coprProperties(Object sourceModel,Object targetModel){
        boolean flag = true;
        return coprProperties(sourceModel,targetModel,flag);
    }
}
