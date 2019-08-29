package com.jianganwei.rpcclient.util;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;
import com.jianganwei.rpccommon.model.RequestModel;
import com.jianganwei.rpccommon.model.ResponseModel;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Promise;
import lombok.extern.slf4j.Slf4j;

import java.lang.ref.ReferenceQueue;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/08/23
 */
@Slf4j
public class ResultCollector {

    public static volatile ConcurrentMap<String, ResponseModel> map = Maps.newConcurrentMap();


    public static void setResult(String requestId, ResponseModel responseModel) {
        map.putIfAbsent(requestId, responseModel);
    }

    /**
     * @param requestId
     * @param timeOut   最长阻塞时间 秒
     * @return
     * @throws Exception
     */
    public static ResponseModel getResult(String requestId, int timeOut) throws Exception {

        long maxCount = timeOut * 10L;
        int count = 0;
        while (count < maxCount) {
            if (map.containsKey(requestId)) {
                ResponseModel model = map.get(requestId);
                map.remove(requestId);
                return model;
            } else {
                count++;
                LockSupport.parkNanos(1000 * 100000L);
            }
        }
        throw new TimeoutException();
    }

}
