package com.jianganwei.rpccommon.decode;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/08/22
 */
public class SimpleMassageDecoder<T> extends ByteToMessageDecoder {
    private Class<T> target;

    public SimpleMassageDecoder(Class<T> target) {
        this.target = target;

    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //编码的时候用一个int 作为header 所以长度必须大于4
        if (byteBuf.readableBytes() <= 4) {
            return;
        }
        //标记当前读的位置
        byteBuf.markReaderIndex();
        //获取消息头,及数据的长度
        int bodyLength = byteBuf.readInt();
        //如果消息体没有全部传过来,返回标记的位置
        if (byteBuf.readableBytes() < bodyLength) {
            byteBuf.resetReaderIndex();
        } else {
            //读取消息体
            byte[] bytes = new byte[bodyLength];
            byteBuf.readBytes(bytes);
            list.add(JSONObject.parseObject(new String(bytes), target));
        }

    }
}
