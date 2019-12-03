package cn.actadd.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SensitiveWordsFilter
 * @Description 敏感词汇屏蔽
 * @Author Actadd
 * @Date 17:10 2019/12/3
 * @Version 1.0
 */
@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //增强getParameter方法
                //判断是否是getParameter方法
                if(method.getName().equals("getParameter")){
                    //增强返回值
                    //获取返回值
                    String value = (String) method.invoke(req,args);
                    if(value != null){
                        for (String str : list) {
                            if(value.contains(str)){
                                value = value.replaceAll(str, "***");
                            }
                        }
                    }
                    return  value;
                }

                return method.invoke(req,args);
            }
        });
        //2.放行
        chain.doFilter(proxy_req, resp);
    }

    //敏感词汇集合
    private List<String> list = new ArrayList<String>();

    /**
     * @description 将敏感词汇封装到 list 集合
     * @param config
     * @return void
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        BufferedReader br = null;

        try {
            //1.获取文件真实路径
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/classes/SensitiveWords.txt");

            //2.读取文件
            br = new BufferedReader(new FileReader(realPath));

            //3.将文件的每一行数据添加到list中
            String line = null;
            while((line = br.readLine())!=null){
                list.add(line);
            }

            //测试
            System.out.println(list);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {
    }

}
