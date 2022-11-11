package com.example.ecommercegateway.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.http.MediaType;

import com.google.gson.GsonBuilder;
import com.example.ecommercegateway.utils.exception.BadRequestException;
import com.example.ecommercegateway.utils.exception.MediaNotSupportedException;
import com.netflix.zuul.context.RequestContext;
import com.example.ecommercegateway.model.Error;
public class ErrorFilter extends SendErrorFilter {

    protected static final String SEND_ERROR_FILTER_RAN = "sendErrorFilter.ran";

    private Logger log = LoggerFactory.getLogger(ErrorFilter.class);

    @Value("/error")
    private String errorPath;

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        return requestContext.getThrowable() != null && !requestContext.getBoolean(SEND_ERROR_FILTER_RAN, false);
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        Throwable throwable = requestContext.getThrowable();

       if (throwable.getCause() instanceof MediaNotSupportedException) {
            MediaNotSupportedException mediaNotSupportedException = (MediaNotSupportedException) throwable.getCause();
            mediaNotSupportedException(requestContext, mediaNotSupportedException.getMessage());
        }
        else if(throwable.getCause() instanceof BadRequestException){
            BadRequestException badRequestException=(BadRequestException) throwable.getCause();
            badRequestException(requestContext,badRequestException.getMessage());
        }
         else {
            zuulException(requestContext);
        }
        requestContext.set(SEND_ERROR_FILTER_RAN, true);
        return null;
    }


    private void zuulException(RequestContext requestContext) {
        log.error("Zuul Exception Occurred", requestContext.getThrowable());
        writeErrorResponse(requestContext, HttpStatus.INTERNAL_SERVER_ERROR.value(),
                Error.setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "An error is occurred at server side due to wrong input. Please try again.."));
    }


    private void mediaNotSupportedException(RequestContext requestContext, String message) {
        log.error("Content type header missing  Exception Occurred");
        writeErrorResponse(requestContext, HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                Error.setErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                        "Content Type header is required"));
    }
    
    private void badRequestException(RequestContext requestContext, String message) {
        log.error("Bad Request Exception");
        writeErrorResponse(requestContext, HttpStatus.BAD_REQUEST.value(),
                Error.setErrorResponse(HttpStatus.BAD_REQUEST.value(),
                        "Bad Request"));
    }

   

    private void writeErrorResponse(RequestContext requestContext, int status, Error response) {
        try {
            HttpServletResponse httpResponse = requestContext.getResponse();
            httpResponse.setContentType(MediaType.APPLICATION_JSON.toString());
            httpResponse.setStatus(status);
            httpResponse.getWriter().append(
                    new GsonBuilder().serializeNulls().create().toJson(response));
            requestContext.setSendZuulResponse(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
