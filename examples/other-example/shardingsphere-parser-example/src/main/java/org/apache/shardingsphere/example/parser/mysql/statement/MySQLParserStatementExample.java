/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.example.parser.mysql.statement;

import org.apache.shardingsphere.sql.parser.api.CacheOption;
import org.apache.shardingsphere.sql.parser.api.SQLParserEngine;
import org.apache.shardingsphere.sql.parser.api.SQLVisitorEngine;
import org.apache.shardingsphere.sql.parser.core.ParseContext;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.mysql.MySQLStatement;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public final class MySQLParserStatementExample {
    
    private static final String DML_SELECT_SQL = "SELECT t.id, t.name, t.age FROM table1 AS t ORDER BY t.id DESC;";
    
    private static final String DML_INSERT_SQL = "INSERT INTO table1 (name, age) VALUES ('z', 18);";
    
    private static final String DML_UPDATE_SQL = "UPDATE table1 SET name = 'j' WHERE id = 1;";
    
    private static final String DML_DELETE_SQL = "DELETE FROM table1 AS t1 WHERE id = 1;";
    
    private static final String DDL_CREATE_SQL = "CREATE TABLE table2 (id BIGINT(20) PRIMARY KEY, name VARCHAR(20), age INT(2))";
    
    private static final String DDL_DROP_SQL = "DROP TABLE table1, table2;";
    
    private static final String DDL_ALTER_SQL = "ALTER TABLE table1 DROP age;";
    
    private static final String DDL_SHOW_SQL = "SHOW COLUMNS FROM table1;";
    
    private static final List<String> MYSQL_PARSER_STATEMENT_LIST;
    
    static {
        MYSQL_PARSER_STATEMENT_LIST = Arrays.asList(DML_SELECT_SQL, DML_INSERT_SQL, DML_UPDATE_SQL, DML_DELETE_SQL,
                DDL_CREATE_SQL, DDL_DROP_SQL, DDL_ALTER_SQL, DDL_SHOW_SQL);
    }
    
    public static void main(String[] args) {
        MYSQL_PARSER_STATEMENT_LIST.stream().forEach(sql -> {
            CacheOption cacheOption = new CacheOption(128, 1024L, 4);
            SQLParserEngine parserEngine = new SQLParserEngine("MySQL", cacheOption, false);
            ParseContext parseContext = parserEngine.parse(sql, false);
            SQLVisitorEngine visitorEngine = new SQLVisitorEngine("MySQL", "STATEMENT", new Properties());
            MySQLStatement sqlStatement = visitorEngine.visit(parseContext);
            System.out.println(sqlStatement.toString());
        });
    }
}
