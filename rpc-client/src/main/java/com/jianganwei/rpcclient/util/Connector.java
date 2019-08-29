package com.jianganwei.rpcclient.util;

import com.jianganwei.rpcclient.handler.ResponseHandler;
import com.jianganwei.rpcclient.model.RemoteModel;
import com.jianganwei.rpcclient.service.RpcClientService;
import com.jianganwei.rpccommon.decode.SimpleMassageDecoder;
import com.jianganwei.rpccommon.encode.SimpleMassageEncode;
import com.jianganwei.rpccommon.model.RequestModel;
import com.jianganwei.rpccommon.model.ResponseModel;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/08/23
 */
@Slf4j
public class Connector implements Runnable {
    private BlockingQueue<RequestModel> linkedBlockingQueue;
    private String ip;
    private int port;
    private EventLoopGroup group;
    private ChannelFuture channelFuture;

    public Connector(BlockingQueue<RequestModel> linkedBlockingQueue, String ip, int port) {
        this.linkedBlockingQueue = linkedBlockingQueue;
        this.ip = ip;
        this.port = port;
    }

    private void init() {
        try {
            group = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(ip, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new SimpleMassageEncode());
                            ch.pipeline().addLast(new SimpleMassageDecoder<>(ResponseModel.class));
                            ch.pipeline().addLast(new ResponseHandler());
                        }
                    });
            channelFuture = bootstrap.connect().sync();
        } catch (Exception e) {
            close();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        init();
        while (channelFuture.channel().isActive()) {
            try {
                RequestModel requestModel = linkedBlockingQueue.take();
                log.info(">>>>>发送请求,{}", requestModel);
                channelFuture.channel().writeAndFlush(requestModel);
            } catch (Exception e) {
                close();
                log.info("发送请求出错", e);
                break;
            }

        }
        close();
    }

    private void close() {
        if (null != group) {
            group.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//            RequestModel requestModel = new RequestModel();
//            requestModel.setMethodName("test");
//            RpcClientService.sendRequest(requestModel);
//        }
    }
}
