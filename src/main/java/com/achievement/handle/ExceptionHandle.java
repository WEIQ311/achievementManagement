package com.achievement.handle;

import com.achievement.utils.GloabalUtils;
import com.achievement.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 异常统一处理
 *
 * @author weiQiang
 */
@ControllerAdvice
public class ExceptionHandle {

  private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

  /**
   * 转换异常返回信息
   *
   * @param request   请求
   * @param exception 异常
   * @return Object
   */
  public Object convertReturn(HttpServletRequest request, Exception exception) {
    if (GloabalUtils.isAjaxRequest(request)) {
      return ResultUtil.error(exception.getMessage());
    } else {
      String path = request.getRequestURL().toString();
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("message", exception.getMessage());
      modelAndView.addObject("status", 500);
      modelAndView.addObject("timestamp", new Date());
      modelAndView.addObject("path", path);
      modelAndView.setViewName("error");
      return modelAndView;
    }
  }

  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  public Object handle(Exception e, HttpServletRequest request, HttpServletResponse response) {
    String path = request.getRequestURL().toString();
    if (null != e && e.getStackTrace().length > 0) {
      StackTraceElement stackTraceElement = e.getStackTrace()[0];
      String className = stackTraceElement.getClassName();
      String methodName = stackTraceElement.getMethodName();
      int lineNumber = stackTraceElement.getLineNumber();
      logger.error("请求路径:{},在:{},方法:{},行:{},发生了错误:{}", path, className, methodName, lineNumber, e.getMessage());
    } else {
      logger.error("发生了错误:{}", e.getMessage());
    }
    return ResultUtil.error(e.getMessage());
  }
}
