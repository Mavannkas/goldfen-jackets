package org.example.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.example.handlers.response.BaseResponseData;

import java.lang.reflect.ParameterizedType;

import static org.example.utils.JsonMapperUtil.convertToObject;

public abstract class BaseHandler<R, O> implements RequestHandler<Object, BaseResponseData<O>>
{
    private Class<R> requestType;
    private boolean initialized;

    @SuppressWarnings("unchecked")
    protected void init()
    {
        if (!initialized)
        {
            this.requestType = (Class<R>) ((ParameterizedType) getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0];
            this.initialized = true;
        }
    }

    protected abstract O processRequest(R request) throws Exception;

    protected abstract void validateRequest(R request) throws Exception;

    @Override
    public BaseResponseData<O> handleRequest(Object requestObject, Context context)
    {
        this.init();

        System.out.println("Request object: " + requestObject);

        final R request = convertToObject(requestObject, requestType);

        System.out.println("Converted request: " + request);

        BaseResponseData<O> response = new BaseResponseData<>();

        try
        {
            validateRequest(request);
            response.setResponse(processRequest(request));
            System.out.println("Response: " + response);
            return response;
        } catch (Exception e)
        {
            System.err.println(e.getMessage());
            response.getErrors()
                    .add(e);
            return response;
        }
    }
}
