package com.dksou.db

import java.sql.DriverManager

import java.util.{Properties, Random, UUID}


object MYSQLConnect {
  def main(args: Array[String]): Unit = {
    classOf[com.mysql.jdbc.Driver]
    val info = new Properties()
    info.setProperty("user", "root")
    info.setProperty("password", "123456")
    val conn = DriverManager.getConnection("jdbc:mysql://192.168.1.74:3306/sqoop", info)
    //   conn.setAutoCommit(false)
    val sql = "insert into WebSiteRecord (ViewId,ViewIp,ViewPageUrl) values (?,?,?)";
    val random = new Random();
    val pageViewUrl = Array("http://www.baidu.com", "http://www.jd.com", "http://www.sina.cn", "http://www.alibaba.com", "http://www.taobao.com")
    val pstmt = conn.prepareStatement(sql)
    //    for (i <- 20001 to 30000) {
    //      pstmt.setString(1, UUID.randomUUID.toString.replace("-", "").toLowerCase);
    //      pstmt.setString(2, random.nextInt(255) + "." + random.nextInt(255) + "."
    //        + random.nextInt(255) + "." + random.nextInt(255));
    //      pstmt.setString(3, pageViewUrl(random.nextInt(pageViewUrl.length - 1)));
    //      pstmt.addBatch()
    //      if (i % 1000 == 0) {
    //        println("当前的i的值：" + i)
    //        pstmt.executeBatch()
    //      }
    //    }

    while (true) {
      pstmt.setString(1, UUID.randomUUID.toString.replace("-", "").toLowerCase);
      pstmt.setString(2, random.nextInt(255) + "." + random.nextInt(255) + "."
        + random.nextInt(255) + "." + random.nextInt(255));
      pstmt.setString(3, pageViewUrl(random.nextInt(pageViewUrl.length - 1)));
      //      pstmt.addBatch()
      //      if (i % 1000 == 0) {
      //        println("当前的i的值：" + i)
      //        pstmt.executeBatch()
      //      }
      pstmt.execute()
      Thread.sleep(1000)
    }
    pstmt.close()
    conn.close()

  }
}
