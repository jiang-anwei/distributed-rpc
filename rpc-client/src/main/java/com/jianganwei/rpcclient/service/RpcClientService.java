package com.jianganwei.rpcclient.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.jianganwei.rpcclient.model.RemoteModel;
import com.jianganwei.rpcclient.util.Connector;
import com.jianganwei.rpcclient.util.RemoteUtil;
import com.jianganwei.rpcclient.util.ResultCollector;
import com.jianganwei.rpccommon.model.RequestModel;
import com.jianganwei.rpccommon.model.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

import static com.jianganwei.rpccommon.exception.ExceptionEnum.GET_RPC_RESULT_EXCEPTION;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/08/22
 */
@Slf4j
public class RpcClientService {
    private int workerCount = 5;
    private static BlockingQueue<RequestModel> requestModels = new LinkedBlockingQueue<>(10000);
    @Autowired
    private RemoteUtil remoteUtil;

    @PostConstruct
    private void init() {
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("连接池线程-%d").build();
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(workerCount, workerCount, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(workerCount * 2), factory);
        new Thread(() -> {
            while (true) {
                try {
                    if (executorService.getActiveCount() < workerCount) {
                        log.info("当前连接数目:{},需要连接:{}", executorService.getActiveCount(), workerCount);
                        RemoteModel remoteModel = remoteUtil.getRemote();
                        log.info("建立远程连接：{}", remoteModel);
                        executorService.execute(new Connector(requestModels, remoteModel.getIp(), remoteModel.getPort()));
                    } else {
                        log.info("当前连接数目已经达到需要");
                        LockSupport.parkNanos(1000 * 1000 * 30000L);
                    }
                } catch (Exception e) {
                    log.warn("错误:{}", e.getMessage());
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        }).start();
    }

    /**
     * 发送远程请求
     *
     * @param requestModel
     * @return
     */
    public static ResponseModel sendRequest(RequestModel requestModel) {
        try {
            requestModels.offer(requestModel);
            return ResultCollector.getResult(requestModel.getRequestId(), 5);
        } catch (Exception e) {
            log.error("获取结果失败:", e);
            throw GET_RPC_RESULT_EXCEPTION.newInstance();
        }

    }


}
