package org.example.gzipcompression;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class GzipFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(GzipFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String acceptEncoding = httpRequest.getHeader("Accept-Encoding");
        try {
            if (acceptEncoding != null && acceptEncoding.contains("gzip")) {
                httpResponse.setHeader("Content-Encoding", "gzip");
                GzipHttpServletResponseWrapper gzipResponse = new GzipHttpServletResponseWrapper(httpResponse);
                chain.doFilter(request, gzipResponse);
                gzipResponse.flushBuffer();
            } else {
                chain.doFilter(request, response);
            }
        } catch (IOException | ServletException e) {
            logger.error("Exception during filter processing", e);
            throw e;
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("GzipFilter initialized");
    }

    @Override
    public void destroy() {
        logger.info("GzipFilter destroyed");
    }
}
