package com.jianganwei.rpcserver.init;

import com.jianganwei.rpcserver.service.RpcServerService;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import java.net.InetAddress;

import static com.jianganwei.rpccommon.config.ZookeeperConfig.ROOT_PATH;

/**
 * @author jianganwei
 * @date 2019/9/10
 */
@Slf4j
public class StartServer implements CommandLineRunner {
    @Autowired
    private ZkClient zkClient;
    @Value("${rpc.server.port:8085}")
    private int port;


    @Override
    public void run(String... args) throws Exception {

        if (!zkClient.exists(ROOT_PATH)) {
            zkClient.createPersistent(ROOT_PATH);
        }
        InetAddress address=InetAddress.getLocalHost();
        zkClient.createEphemeral(ROOT_PATH + "/"+address.getHostAddress() + ":"+port);
        log.info("rpc server start  at {}", port);
        RpcServerService.startServer(port);

    }
}
