<%-- 
    Document   : head
    Created on : May 12, 2019, 9:35:19 PM
    Author     : Christian Carrillo Zúñiga
--%>
<!DOCTYPE html>
<html>
    <head>
        <title>WebEncuestas - ${param.pageTitle}</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" type="image/png" href="<%= request.getContextPath()%>/resources/images/utp.ico" sizes="64x64">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/custom.min.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/app.css">
    </head>
    <body class="${param.bgBody}">