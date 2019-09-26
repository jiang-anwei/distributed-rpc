package com.jianganwei.rpcclient.util;

import com.google.common.base.Preconditions;
import com.jianganwei.rpcclient.model.RemoteModel;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

import static com.jianganwei.rpccommon.config.ZookeeperConfig.ROOT_PATH;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/08/23
 */
public class RemoteUtil {
    @Autowired
    private ZkClient zkClient;
    private static Random random = new Random();

    public RemoteModel getRemote() {
        List<String> stringList = zkClient.getChildren(ROOT_PATH);
        Preconditions.checkState(stringList.size()>0,"没有可用的远程服务");
        String[] s = stringList.get(random.nextInt(stringList.size())).split(":");
        return new RemoteModel(s[0], Integer.valueOf(s[1]));
    }
}
