package net.ald.projet.filters;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.LoggerFactory;


public class JsonpCallbackFilter implements Filter {


        private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(JsonpCallbackFilter.class);
        private static Log log = LogFactory.getLog(JsonpCallbackFilter.class);
        
        public void destroy() {}


        public void doFilter(ServletRequest request, ServletResponse response,
                        FilterChain chain) throws IOException, ServletException {
                LOG.info("FILTER DE JSONP");
                
                HttpServletRequest httpRequest = (HttpServletRequest) request;
             HttpServletResponse httpResponse = (HttpServletResponse) response;


            @SuppressWarnings("unchecked")
             Map<String, String[]> parms = httpRequest.getParameterMap();


             if(parms.containsKey("callback")) {
                 if(log.isDebugEnabled())
                     log.debug("Wrapping response with JSONP callback '" + parms.get("callback")[0] + "'");        
             
                 String contentTypeResponse = "text/javascript;charset=UTF-8";
                 int contentLengthTotal = 0;
                 byte[] responseBody;
                 String callBackParam = parms.get("callback")[0];


                 OutputStream out = httpResponse.getOutputStream();
                 GenericResponseWrapper wrapper = new GenericResponseWrapper(httpResponse);


                 chain.doFilter(request, wrapper);


                 responseBody = wrapper.getData();
                 contentLengthTotal = callBackParam.length();
                 contentLengthTotal += responseBody.length;
                 contentLengthTotal += 3;
                 
                 response.setContentType(contentTypeResponse);
                 response.setContentLength(contentLengthTotal);
                 
                 out.write(new String(callBackParam + "(").getBytes());
                 out.write(responseBody);
                 out.write(new String(");").getBytes());
                 
                 out.close();                 
             } else {
         chain.doFilter(request, response);
              }
        }


        public void init(FilterConfig arg0) throws ServletException {}
}

