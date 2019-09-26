package com.jianganwei.rpcserver.hander;

import com.jianganwei.rpccommon.RequestStatus;
import com.jianganwei.rpccommon.model.ResponseModel;
import com.jianganwei.rpccommon.model.RequestModel;
import com.jianganwei.rpcserver.util.SpringUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/08/22
 */
@Slf4j
public class RequestHandler extends SimpleChannelInboundHandler<RequestModel> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RequestModel requestModel) throws Exception {
        log.info("收到调用请求:{}", requestModel);
        channelHandlerContext.writeAndFlush(handlerRequest(requestModel));
    }

    private static ResponseModel handlerRequest(RequestModel requestModel) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setRequestId(requestModel.getRequestId());
        try {
            Object o = SpringUtils.getBean(Class.forName(requestModel.getClassName()));
            Class[] classes = Arrays.stream(requestModel.getPara()).map(Object::getClass).toArray(x -> new Class[requestModel.getPara().length]);
            Method method = o.getClass().getMethod(requestModel.getMethodName(), classes);
            responseModel.setResult(method.invoke(o, requestModel.getPara()));
            responseModel.setStatus(RequestStatus.SUCCESS);
            return responseModel;
        } catch (Exception e) {
            log.error("调用出错", e);
            responseModel.setStatus(RequestStatus.ERROR);
            responseModel.setResult(e.getMessage());
            return responseModel;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("错误",cause);
        ctx.close();
        super.exceptionCaught(ctx, cause);
    }
}
