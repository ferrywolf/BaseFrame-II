package cn.com.BaseFrame.Login.controller;

/**
 * 登录的controller
 *
 * @author xyh
 * @create 2018-02-22 13:08
 **/


import cn.com.BaseFrame.BaseUtils.BeanUtils.BeanUtils;
import cn.com.BaseFrame.common.controller.impl.BaseController;
import cn.com.BaseFrame.common.service.IBaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/Login")
public class LoginController extends BaseController {
    /**
     *  @Description: 跳转到登录页面
     *  @author xyh
     *  @Date 17:21 2018/2/23
     *  @method handlerLogin
     *  params  HttpServletRequest request 请求, HttpServletResponse response 响应
     *  @return 
     *  @exception 
     **/
    @RequestMapping("loginHandler")
    public String loginHandler() {
        IBaseService service = (IBaseService) BeanUtils.getBean("IBaseService");
        System.out.print("hello my first ssm project");
        return service.toString();
    }

}
