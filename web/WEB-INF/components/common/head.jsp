<%-- 
    Document   : head
    Created on : May 12, 2019, 9:35:19 PM
    Author     : Christian Carrillo Zúñiga
--%>
<!DOCTYPE html>
<html>
    <head>
        <title>Web - ${param.pageTitle}</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" type="image/png" href="<%= request.getContextPath()%>/assets/images/utp.ico" sizes="64x64">

        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <!-- Bootstrap core CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <!-- Material Design Bootstrap -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.8.3/css/mdb.min.css" rel="stylesheet">

        <!-- Main Stylesheet File -->
        <link href="<%= request.getContextPath()%>/assets/css/styles.css" rel="stylesheet">

    </head>
    <body class="${param.bgBody}">