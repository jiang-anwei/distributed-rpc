package com.jianganwei.rpcserver.service;

import com.jianganwei.rpccommon.decode.SimpleMassageDecoder;
import com.jianganwei.rpccommon.encode.SimpleMassageEncode;
import com.jianganwei.rpccommon.model.RequestModel;
import com.jianganwei.rpcserver.hander.RequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/08/22
 */
@Slf4j
public class RpcServerService {
    public static void startServer(int port) {

//          创建两个线程组　用于处理网路事件
//          一个用来接收客户端的连接
//         一个用来进行SocketChannel 的网络读写
        @Cleanup(value = "shutdownGracefully") EventLoopGroup bossGroup = new NioEventLoopGroup();
        @Cleanup(value = "shutdownGracefully") EventLoopGroup workGroup = new NioEventLoopGroup();
        //辅助启动类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .channel(NioServerSocketChannel.class)
                //注册两个线程组
                .group(bossGroup, workGroup)
                .localAddress(port)
                //tcp 属性，不能处理的的请求放入队队列的队列大小
                .handler(new ChannelInitializer<ServerSocketChannel>() {
                    @Override
                    protected void initChannel(ServerSocketChannel serverSocketChannel) throws Exception {
                        serverSocketChannel.pipeline().addLast(new SimpleChannelInboundHandler<Object>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
                                log.info(">>>>>>>,{}", o);
                                channelHandlerContext.fireChannelRead(o);
                            }
                        });
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 1024)
                //tcp 属性　长连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new SimpleMassageEncode());
                        ch.pipeline().addLast(new SimpleMassageDecoder<>(RequestModel.class));
                        ch.pipeline().addLast(new RequestHandler());
//                        ch.pipeline().addLast(new HelloServerInHandler());
                    }
                });

        try {
            ChannelFuture future = serverBootstrap.bind().sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

