package com.jianganwei.rpcclient.config;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/08/28
 */
@Configuration
public class ZookeeperConfig {
    @Value("${zooKeeperNode:127.0.0.1:2181}")
    private String zooKeeperNode;
    @Bean
    ZooKeeper zooKeeper() throws Exception{
        return new ZooKeeper(zooKeeperNode, 100000,event -> {
            System.out.println(event.getPath());
        });
    }

}
