/**
 * http://www.blogjava.net/yongboy/archive/2014/09/11/417840.html
 * 使用netty分别做一个服务器端和客户端程序，
 * 单纯TCP长连接，ping-pong类型，或者简单纯文本echo也行，连接上来不断开。
 *
 * netty所写的服务器，在（主要是逻辑）升级的过程中，客户端和服务器端程序不断开，
 * 服务器的逻辑代码升级完毕之后，要通知到客户端，已经升级完毕
 * Created by zkai on 2015/2/4.
 */
package com.dudo.hotswap;