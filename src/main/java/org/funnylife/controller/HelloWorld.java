package org.funnylife.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.funnylife.vo.TableColumnsVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.List;
import java.util.Map;

//@EnableAutoConfiguration
@RestController
@RequestMapping("/hello")
public class HelloWorld {
    @Value("${app.name}")
    private String test;

    @RequestMapping("/{name}")
    public String view(@PathVariable("name") String name) {
        return "hello " + name + "<br>appName : " + test;
    }

    @RequestMapping("/test")
    public Object check(){
        Map<String, Object> data = Maps.newHashMap();
        try {
            String url = "jdbc:mysql://localhost:3306/deren?user=root&password=root";
            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            String query = "select * from t_article limit 0, 10";
            ResultSet rs = stmt.executeQuery(query);
            boolean isFirst = true;

            List<TableColumnsVO> cList = Lists.newArrayList();
            List<Map<String, String>> dList = Lists.newArrayList();

            while (rs.next()) {
                if(isFirst) {
                    ResultSetMetaData metaData = rs.getMetaData();
                    metaData.getColumnType(1);
                    metaData.getColumnDisplaySize(1);
                    for (int i = 1; i <= metaData.getColumnCount(); i++) {
                        cList.add(new TableColumnsVO(metaData.getColumnName(i), metaData.getColumnTypeName(i), metaData.getColumnDisplaySize(i)));
                        isFirst = false;
                    }
                    data.put("columns", cList);
                }
                Map<String, String> row = Maps.newHashMap();
                for(int i = 0; i<cList.size(); i++) {
                    TableColumnsVO col = cList.get(i);
                    row.put(col.getName(), rs.getString(col.getName()));
                }
                dList.add(row);
            }
            data.put("data", dList);
            data.put("success", true);
            stmt.close();
            con.close();
        }catch (Exception e){
            data.put("error", e.getCause());
            data.put("success", false);
        }
        return data;
    }

}  