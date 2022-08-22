package com.eosa.admin.util;

import org.hibernate.dialect.MySQL8Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class MySQLCustomDialect extends MySQL8Dialect {
    public MySQLCustomDialect() {
        registerFunction("GROUP_CONCAT", 
            new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
    }    
}
