package com.dudo.bookmarks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * User: ZK
 * Date: 13-4-28
 * Time: 下午5:30
 */
public class AnnosModel implements Serializable,Comparable<AnnosModel>
{
    private static final Logger logger = LoggerFactory.getLogger(Model.class);
    private static final long serialVersionUID = -8036210044097960299L;
    public String name;
    public String mimeType;
    public Long flags;
    public Long expires;
    public Long type;
    public String value;

    public void print() {
        logger.info("name:{},\tmimeType:{},\tflags:{},\texpires:{},\ttype:{},\tvalue:{}",
                new Object[]{this.name, this.mimeType, this.flags, this.expires,
                        this.type, this.value});
    }

    @Override
    public int compareTo(AnnosModel o) {
        return  this.name.compareTo(o.name);
    }
}
