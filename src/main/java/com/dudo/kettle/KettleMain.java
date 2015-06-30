package com.dudo.kettle;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.plugins.DatabasePluginType;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.core.variables.VariableSpace;
import org.pentaho.di.core.variables.Variables;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.job.entry.JobEntryCopy;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author zhangkai9
 * @date 2015/6/25
 */
public class KettleMain {
    public static void main(String[] args) throws Exception {
//        runTransformation("D:\\pdi-ce-5.4.0.1-130\\test.ktr");
//        runTrans("D:\\pdi-ce-5.4.0.1-130\\test.ktr");
        ;
        String path = ResourceUtils.getFile("classpath:kettle/test.kjb").getCanonicalPath();
//        String path = ResourceUtils.getFile("classpath:kettle/test.ktr").getCanonicalPath();
//        System.out.println(path);
        runJob(path);
//        runTrans(path);
    }

    public static void runJob(String jobname) {
        try {
            KettleEnvironment.init();
            VariableSpace aDefaultVariableSpace = Variables.getADefaultVariableSpace();
            aDefaultVariableSpace.setVariable("sql", "SELECT 2 as 'name' FROM cf_statistical_msg ");
            String canonicalPath = ResourceUtils.getFile("classpath:kettle/test.ktr").getCanonicalPath();
            aDefaultVariableSpace.setVariable("t1_path", canonicalPath);
            //jobname 是Job脚本的路径及名称
            JobMeta jobMeta = new JobMeta(aDefaultVariableSpace, jobname, null, null, null);
            JobEntryCopy t1 = jobMeta.findJobEntry("t1");
//            t1.setEntry();
            Job job = new Job(null, jobMeta);
            //向Job 脚本传递参数，脚本中获取参数值：${参数名}
            job.setVariable("t1_path", canonicalPath);
            job.setVariable("sql", "SELECT 2 as 'name' FROM cf_statistical_msg ");
            JobEntryCopy mysql = jobMeta.findJobEntry("转换");
            job.start();
            job.waitUntilFinished();
            if (job.getErrors() > 0) {
                System.out.println("decompress fail!");
            }
        } catch (KettleException e) {
            System.out.println(e);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void runTrans(String filename) throws Exception {

        KettleEnvironment.init();
        VariableSpace aDefaultVariableSpace = Variables.getADefaultVariableSpace();
        aDefaultVariableSpace.setVariable("sql","SELECT 2 as 'name' FROM cf_statistical_msg ");
        TransMeta transMeta = new TransMeta(filename, aDefaultVariableSpace);
        Trans trans = new Trans(transMeta);
        trans.prepareExecution(null);
        trans.startThreads();
        trans.waitUntilFinished();

        if (trans.getErrors() != 0) {
            System.out.println("Error");
        }
    }
}
