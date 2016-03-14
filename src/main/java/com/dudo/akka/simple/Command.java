package com.dudo.akka.simple;


import java.io.Serializable;
/**
 * Created by zk on 2/2/16.
 */
public class Command implements Serializable {

    private final String data;

    public Command(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return "Command{" +
                "data='" + data + '\'' +
                '}';
    }
}
