package com.jianganwei.rpccommon.model;

import com.jianganwei.rpccommon.RequestStatus;
import lombok.Data;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/08/22
 */
@Data
public class ResponseModel {
    Object result;
    String requestId;
    RequestStatus status;
}
