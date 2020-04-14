package com.kd.execute;

import com.alibaba.fastjson.JSON;
import com.kd.execute.test.DBProperties;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

/**
 * @author Adil
 * @date 2020-04-14 11:22:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExecuteSqlScript.class)
public class ScriptRunnerExeSql {
    @Autowired
    private DBProperties dbProperties;
    private static final Logger logger = LoggerFactory.getLogger(ExecuteSqlScript.class);

    @Test
    public void test()  {
        try {
            Map<String, String> datasource = dbProperties.getDatasource();
            logger.info("url:{}",JSON.toJSONString(datasource.get("url")));
            logger.info("username:{}",JSON.toJSONString(datasource.get("username")));
            logger.info("password:{}",JSON.toJSONString(datasource.get("password")));
            logger.info("driver:{}",JSON.toJSONString(datasource.get("driver-class-name")));
            Class.forName(datasource.get("driver-class-name"));
            Connection conn = DriverManager.getConnection(datasource.get("url"),datasource.get("username"),datasource.get("password"));
            ScriptRunner runner = new ScriptRunner(conn);
            Resources.setCharset(Charset.forName("UTF-8"));//设置字符集,不然中文乱码插入错误
            runner.setLogWriter(null);//设置是否输出日志,如果不设置,默认打印执行的sql脚本中所有语句
            File file = new File("/sql");//这里使用相对路径(相对于工程所在路径),使用"/"不需要转义
            if (file == null) {
                logger.info("{}路径不存在",file.toString());
                return;
            }
            File[] files = file.listFiles();
            if (files == null || files.length==0) {
                logger.info("目录下不存在文件!");
                return;
            }
            for (File f : files) {
                if (f.isFile()) {
                    Reader read = new FileReader(f);
                    runner.runScript(read);
                }
            }
            runner.closeConnection();
            conn.close();
            logger.info("执行sql脚本成功");
        } catch (Exception e) {
            logger.info("执行sql脚本失败;异常原因:{}", e.getMessage());
        }
    }
}
