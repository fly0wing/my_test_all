package com.dudo.yaml;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zk on 3/14/16.
 */
public class A {
    @Test
    public void testName() throws Exception {

        Yaml yaml = new Yaml();
        FileInputStream yaml1 = new FileInputStream("/home/zk/workspaces/github/my_test_all/src/main/resources/error.yml");
        List<ErrorProps> errorPropses = (List<ErrorProps>) yaml.load(yaml1);

        System.out.println(JSON.toJSONString(errorPropses.get(0)));
        System.out.println(JSON.toJSONString(errorPropses));

    }

    @Test
    public void testName2() throws Exception {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);
        StringWriter writer = new StringWriter();

        ErrorProps errorProps = new ErrorProps();
        errorProps.setCode("cf_business_005001");
        errorProps.setInfo("请求服务接口标示与服务不匹配");


        ErrorProps errorProps2 = new ErrorProps();
        errorProps2.setCode("cf_business_005003");
        errorProps2.setInfo("手机验证码不匹配");
        List<ErrorProps> data = new ArrayList<>();
        data.add(errorProps);
        data.add(errorProps2);
        yaml.dump(data, writer);
        yaml.dump(data, writer);
        System.out.println(writer.toString());

    }
}
