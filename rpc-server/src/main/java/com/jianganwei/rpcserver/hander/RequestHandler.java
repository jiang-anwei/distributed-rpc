package com.jianganwei.rpcserver.hander;

import com.jianganwei.rpccommon.model.ResponseModel;
import com.jianganwei.rpccommon.model.RequestModel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/08/22
 */
@Slf4j
public class RequestHandler extends SimpleChannelInboundHandler <RequestModel>{
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RequestModel requestModel) throws Exception {
         ResponseModel responseModel =new ResponseModel();
         responseModel.setRequestId(requestModel.getRequestId());
         responseModel.setResultJSON("result");
         log.info("收到调用请求:{}",requestModel);
         channelHandlerContext.writeAndFlush(responseModel);
    }
}
