package com.jianganwei.rpcclient.util;

import com.jianganwei.rpcclient.model.RemoteModel;

/**
 * describe:
 *
 * @author jianganwei
 * @date 2019/08/23
 */
public class RemoteUtil {
    public static RemoteModel getRemote() {
        return new RemoteModel("127.0.0.1", 8085);
    }
}
