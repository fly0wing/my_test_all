package com.dudo.echo;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * User: zk
 * Date: 13-9-2
 * Time: 上午9:51
 */

public interface TCPProtocol {
    void handleAccept(SelectionKey key) throws IOException;

    void handleRead(SelectionKey key) throws IOException;

    void handleWrite(SelectionKey key) throws IOException;
}
