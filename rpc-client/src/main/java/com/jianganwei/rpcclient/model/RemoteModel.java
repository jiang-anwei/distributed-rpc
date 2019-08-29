package com.jianganwei.rpcclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/08/23
 */
@Data
@AllArgsConstructor
public class RemoteModel {
    private String ip;
    private int port;
}
