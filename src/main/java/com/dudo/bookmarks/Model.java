package com.dudo.bookmarks;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User: ZK
 * Date: 13-4-28
 * Time: 下午5:23
 */
public class Model implements Serializable, Comparable<Model> {
    private static final Logger logger = LoggerFactory.getLogger(Model.class);
    private static final long serialVersionUID = -3999298278601927365L;
    public String title;
    public Long id;
    public Long dateAdded;
    public Long lastModified;
    public String type;
    public String root;
    //关联id.
    public Long parent;
    public List<Model> children = new ArrayList<Model>();
    public Long livemark;
    public List<AnnosModel> annos = new ArrayList<AnnosModel>();
    public String uri;
    public String charset;
public String getRoot(){
    return  root;
}
    public void print() {
        for (Model m : this.children) {
            m.print();
        }
        for (AnnosModel am : this.annos) {
            am.print();
        }
        logger.info("id:{},\ttitle:{},\ttype:{},\troot:{},\tparent:{},\tlivemark:{},\turi:{}",
                new Object[]{this.id, this.title, this.type, this.root,
                        this.parent, this.livemark, this.uri});
    }

    @Override
    public int compareTo(Model o) {
        if (StringUtils.isEmpty(this.uri) || StringUtils.isEmpty(o.uri))
            return 0;
        return this.uri.compareTo(o.uri);
    }
}
