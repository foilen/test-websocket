package com.foilen.websocket.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.foilen.smalltools.tools.AbstractBasics;
import com.foilen.smalltools.tools.SecureRandomTools;
import com.foilen.smalltools.tools.ThreadNameStateTool;
import com.foilen.smalltools.tools.ThreadTools;

@Component
public class LoggerFilter extends AbstractBasics implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        ThreadNameStateTool threadNameStateTool = ThreadTools.nameThread() //
                .clear() //
                .appendText(SecureRandomTools.randomHexString(15)) //
                .change();

        try {

            HttpServletRequest req = (HttpServletRequest) request;
            List<String> headerNames = new ArrayList<>();
            Enumeration<String> hnIt = req.getHeaderNames();
            while (hnIt.hasMoreElements()) {
                headerNames.add(hnIt.nextElement());
            }
            Collections.sort(headerNames);

            logger.info("Request {} : {}", req.getMethod(), req.getRequestURI());
            headerNames.forEach(name -> {
                logger.info("\t{} -> {}", name, req.getHeader(name));
            });

            chain.doFilter(request, response);

        } finally {
            threadNameStateTool.revert();
        }
    }

}
