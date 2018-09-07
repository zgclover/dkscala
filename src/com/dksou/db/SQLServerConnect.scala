package com.dksou.db

import java.sql.DriverManager
import java.util.Properties

object SQLServerConnect {
  def main(args: Array[String]): Unit = {
    classOf[com.microsoft.sqlserver.jdbc.SQLServerDriver]
    val info = new Properties()
    info.setProperty("user", "zhanggc")
    info.setProperty("password", "Zgc172531")
    val conn = DriverManager.getConnection("jdbc:sqlserver://192.168.1.75:1433;database=customer", info)
    //   conn.setAutoCommit(false)
    val sql = "insert into student (id,name) values (?,?)";
    val pstmt = conn.prepareStatement(sql)
    for (i <- 10001 to 20000) {
      pstmt.setInt(1, i)
      pstmt.setString(2, "name" + i)
      pstmt.addBatch()
      if (i % 1000 == 0) {
        println("当前的i的值：" + i)
        pstmt.executeBatch()
      }
    }
    pstmt.close()
    conn.close()
  }

}
