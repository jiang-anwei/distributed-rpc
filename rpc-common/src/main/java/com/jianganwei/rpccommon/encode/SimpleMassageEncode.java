package com.jianganwei.rpccommon.encode;

import com.alibaba.fastjson.JSONObject;
import com.jianganwei.rpccommon.model.RequestModel;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * describe:rpc 客户端编码器
 *
 * @author jianganwei
 * @date 2019/08/22
 */
public class SimpleMassageEncode extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object requestModel, ByteBuf byteBuf) throws Exception {
        byte[] body = JSONObject.toJSONString(requestModel).getBytes();
        int header = body.length;
        byteBuf.writeInt(header);
        byteBuf.writeBytes(body);
    }
}
