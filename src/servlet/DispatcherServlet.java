package servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jun.bean.Handler;
import com.jun.helper.BeanHelper;
import com.jun.helper.ControllerHelper;
import com.jun.loader.HelperLoader;

public class DispatcherServlet extends HttpServlet{

	

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取请求路径和请求方法
		String requestMethod = request.getMethod().toLowerCase();
		String requestPath = request.getPathInfo();
		
		//获取action处理器
		Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
		if (handler != null) {
			//获取controller类及bean实例
			Class<?> controller = handler.getControllerClass();
			Object bean = BeanHelper.getBean(controller);
			//创建请求参数对象
			Map<String, Object> paramMap = new HashMap<String,Object>();
			Enumeration<String> paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String paramName = (String) paramNames.nextElement();
				String paramValue = request.getParameter(paramName);
				paramMap.put(paramName, paramValue);
			}
			
			
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		//初始化helper类
		HelperLoader.init();
		//获取ServletContext对象
		ServletContext context = config.getServletContext();
		
		//注册处理jsp的servlet
		ServletRegistration jspServlet = context.getServletRegistration("jsp");
		jspServlet.addMapping("/WEB-INF/view/*");
		//注册处理静态资源的servlet
		ServletRegistration defaultServlet = context.getServletRegistration("default");
		defaultServlet.addMapping("/asset/*");
		
	}
	
	
	
	
	
}
