package org.funnylife.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.funnylife.vo.QueryVO;
import org.funnylife.vo.ResultVO;
import org.funnylife.vo.TableColumnsVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cheng on 2017/7/2.
 */
@RestController
@RequestMapping("/sql")
public class SqlCmdController {

    @RequestMapping(value="/run", method= RequestMethod.POST)
    public ResultVO execut(@RequestBody QueryVO queryVO){
        ResultVO<Map<String, Object>> resultVO = new ResultVO<Map<String, Object>>();
        Connection con = null;
        Statement stmt = null;

        resultVO.setData(new HashMap<String, Object>());
        try {
            con = getConnection(queryVO.getDbName(), "root", "root");
            stmt = con.createStatement();

            String sql = queryVO.getSql();
            if("execute".equals(queryVO.getExecTtype())){
                boolean execute = stmt.execute(sql);
                resultVO.getData().put("data", execute);
                resultVO.getData().put("success", true);
            }else{
                ResultSet rs = stmt.executeQuery(sql);
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
                        resultVO.getData().put("columns", cList);
                    }
                    Map<String, String> row = Maps.newHashMap();
                    for(int i = 0; i<cList.size(); i++) {
                        TableColumnsVO col = cList.get(i);
                        row.put(col.getName(), rs.getString(col.getName()));
                    }
                    dList.add(row);
                }
                resultVO.getData().put("data", dList);
                resultVO.success();
            }
        }catch (Exception e){
            resultVO.fail();
            resultVO.setResult(e.getMessage());
        }finally {
            try {
                stmt.close();
            } catch (Exception e) {
            }
            try {
                con.close();
            } catch (Exception e) {
            }
        }
        return resultVO;
    }

    private Connection getConnection(String dbName, String user, String passwored) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/"+dbName+"?user="+user+"&password="+passwored;
        Connection conn = DriverManager.getConnection(url);
        return conn;
    }
}
