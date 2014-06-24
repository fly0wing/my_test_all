package com.dudo.jxpath;

import com.dudo.bookmarks.Model;
import org.apache.commons.jxpath.JXPathContext;

import java.util.ArrayList;
import java.util.List;

/**
 * User: ZK
 * Date: 13-5-13
 * Time: 下午5:52
 */
public class Mytest {
    public static void main(String[] args) {
        List<Model> mList = new ArrayList<Model>();
        for (int i = 1; i < 100; i++) {
            Model model=new Model();
            model.uri=i+"";
            model.parent= Long.valueOf(i);
            model.root=i+"";
            mList.add(model);
        }
        System.out.println(JXPathContext.newContext(mList).getValue("root"));
    }
}
