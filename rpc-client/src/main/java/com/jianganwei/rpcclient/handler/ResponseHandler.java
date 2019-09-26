package com.jianganwei.rpcclient.handler;

import com.google.common.collect.Maps;
import com.jianganwei.rpcclient.util.ResultCollector;
import com.jianganwei.rpccommon.model.ResponseModel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/08/23
 */
@Slf4j
public class ResponseHandler extends SimpleChannelInboundHandler<ResponseModel> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ResponseModel responseModel) throws Exception {
        ResultCollector.setResult(responseModel.getRequestId(), responseModel);
    }
}
