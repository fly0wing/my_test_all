package com.dudo.bookmarks;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 读取firefox的书签导出文件.
// * User: ZK
// * Date: 13-4-28
// * Time: 下午5:22
// */
//public class Test {
//    private static final Logger logger = LoggerFactory.getLogger(Model.class);
//
//    public static void main(String[] args) throws IOException {
//        String markStr = "";
//        Gson gson = new GsonBuilder().serializeNulls().create();
//        File file = new File("D:\\my_test_all\\src\\main\\resources\\bookmarks.json");
//        markStr = IOUtils.toString(new FileInputStream(file));
//        Model m = gson.fromJson(markStr, Model.class);
////        List<Model> modelList = new ArrayList<Model>();
////        allBookMarks(m.children, modelList);
////
////        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
////        Collections.sort(modelList, new Comparator<Model>() {
////            @Override
////            public int compare(Model o1, Model o2) {
////                if (StringUtils.isEmpty(o1.uri) || StringUtils.isEmpty(o2.uri))
////                    return 0;
////                return o1.uri.compareTo(o2.uri);
////            }
////        });
////        m.children = modelList;
//        File fileTo = new File("D:\\my_test_all\\src\\main\\resources\\to_bookmarks.json");
//        FileOutputStream fileOutput = FileUtils.openOutputStream(fileTo);
//        fileOutput.write(gson.toJson(m).getBytes());
//        fileOutput.close();
//
//        logger.info(gson.toJson(m));
//    }
//
//    private static void allBookMarks(Model model, List<Model> modelList) {
//        if (modelList == null) {
//            modelList = new ArrayList<Model>();
//        }
//        modelList.add(model);
//        for (Model m : model.children) {
//            allBookMarks(m, modelList);
//        }
//    }
//
//    private static void allBookMarks(List<Model> models, List<Model> modelList) {
//        if (modelList == null) {
//            modelList = new ArrayList<Model>();
//        }
//        for (Model m : models) {
//            if (StringUtils.isNotBlank(m.uri)) {
//                modelList.add(m);
//            }
//        }
//        for (Model m : models) {
//            if (StringUtils.isBlank(m.uri)) {
//                allBookMarks(m.children, modelList);
//                m.children = new ArrayList<Model>();
//            }
//        }
//    }
//}
