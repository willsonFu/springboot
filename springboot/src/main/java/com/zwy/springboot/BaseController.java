package com.zwy.springboot;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class BaseController {
	
	private static final Logger log = Logger.getLogger(BaseController.class);

	@ExceptionHandler (Exception.class)
    @ResponseStatus (HttpStatus.OK)
	@ResponseBody
    public void handleExceptions(HttpServletRequest request,Exception ex) {
		String simpleName = ex.getClass().getSimpleName();
		if (simpleName.equals("ClientAbortException")) {//客户端取消链接不记录日志
			log.debug(getRequestString(request)+ex.getMessage());
		} else {
			log.error(getRequestString(request),ex);
		}
		ex.printStackTrace();
    }
	
	private static String getRequestString(HttpServletRequest request){
		return getRequestParameter(request)+" 请求错误";
	}
	
	
	public static String getRequestParameter(HttpServletRequest request){
		StringBuffer sb = new StringBuffer();
		sb.append(request.getMethod()).append(" ")
		.append(request.getRequestURI()).append(" ");
		if(request.getContentType() !=null){
			sb.append(request.getContentType()).append(" ");
		}
		if(request.getAttribute("DEVICECONTEXT") !=null){
			sb.append(request.getAttribute("DEVICECONTEXT").toString()).append(" ");
		}
		if(request.getParameterMap()!=null&&request.getParameterMap().size()!=0){
			sb.append(parameterMapToString(request.getParameterMap())).append(" ");
		}
		if(isMultipart(request)){
            sb.append(humanReadableByteCount(request.getContentLength())).append(" ");
        }
		return sb.toString();
	}
	
    private static boolean isMultipart(final HttpServletRequest request) {
        return request.getContentType()!=null && request.getContentType().startsWith("multipart/form-data");
    }
    
    private static String humanReadableByteCount(long bytes) {
        int unit = 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = String.valueOf("KMGTPE".charAt(exp-1));
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
    
    private static String parameterMapToString(Map<String, String[]> map){
    	StringBuffer sb = new StringBuffer();
    	for (Map.Entry<String, String[]> entry : map.entrySet())
    	{
    		sb.append(entry.getKey() + "=" + entry.getValue()[0]+"&");
    	}
    	return sb.substring(0, sb.length()-1);
    }
}
