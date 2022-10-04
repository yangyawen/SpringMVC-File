<%--
  Created by IntelliJ IDEA.
  User: yangyawen
  Date: 2022/10/4
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <p1>下载</p1>
    <a href="${pageContext.request.contextPath}/download01">Download</a>
    <p1>单文件上传</p1>
    <form enctype="multipart/form-data" action="${pageContext.request.contextPath}/upload01" method="post">
      <p>文件描述：<input type="text" name="desc"/></p>
      <p>文件：<input type="file" name="myfile"/></p>
      <p><input type="submit" value="提交"></p>
    </form>
    <p1>多图片上传</p1>
    <form enctype="multipart/form-data" action="${pageContext.request.contextPath}/upload02" method="post">
      <p>文件描述：<input type="text" name="desc"/></p>
      <%--key和value一样的时候可以只写一个key，如multiple
      accept="image/*" 限制只能上传图片
      --%>
      <p>文件：<input type="file" name="myfiles" multiple accept="image/*"/></p>
      <p><input type="submit" value="提交"></p>
    </form>
    <p1>多图片多线程上传</p1>
    <form enctype="multipart/form-data" action="${pageContext.request.contextPath}/upload03" method="post">
      <p>文件描述：<input type="text" name="desc"/></p>
      <p>文件：<input type="file" name="myfiles" multiple accept="image/*"/></p>
      <p><input type="submit" value="提交"></p>
    </form>
  </body>
</html>
