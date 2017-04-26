package rest.service.models;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by danie on 2017/04/22.
 */
@XmlRootElement
public class ApiResponse
{
    private Object Result;
    private ReponseCodes Status;

    public ApiResponse(Object result, ReponseCodes code)
    {
        Result = result;
        this.Status = code;
    }

    public ApiResponse(Object result)
    {
        Result = result;
        this.Status = ReponseCodes.SUCCESS;
    }

    public ReponseCodes getStatus()
    {
        return Status;
    }

    public Object getResult()
    {
        return Result;
    }



    public ApiResponse()
    {
        this.Status = ReponseCodes.SUCCESS;
        Result = null;
    }
}
