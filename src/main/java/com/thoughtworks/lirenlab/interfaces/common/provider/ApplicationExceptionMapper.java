package com.thoughtworks.lirenlab.interfaces.common.provider;

import com.thoughtworks.lirenlab.domain.model.donation.DonationNotFoundException;
import com.thoughtworks.lirenlab.domain.model.donation.InvalidHistoricalDonationException;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

/**
 *  quick and dirty solution, please refactor it.
 */

@Provider
@Component
public class ApplicationExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {

        if (exception instanceof DonationNotFoundException) {
            return Response
                    .status(BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(new ErrorMessage(
                            "0000",
                            "您请求的捐赠不存在, ID为 " + ((DonationNotFoundException) exception).donationId().strValue()))
                    .build();
        }

        if (exception instanceof InvalidHistoricalDonationException) {
            return Response
                    .status(BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(new ErrorMessage(
                            "0001",
                            "您搜索的历史捐赠不存在, ID为 " + ((InvalidHistoricalDonationException) exception).donationId().strValue()))
                    .build();
        }

        return Response
                .serverError()
                .entity(new ErrorMessage(
                        "9999",
                        "未知错误，请联系管理员: "+exception.getMessage()))
                .build();
    }

}
