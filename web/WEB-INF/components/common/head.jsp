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
        <link rel="icon" type="image/png" href="<%= request.getContextPath()%>/resources/images/utp.ico" sizes="64x64">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/app.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <!-- Libraries CSS Files -->
        <link href="<%= request.getContextPath()%>/resources/lib/animate/animate.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/resources/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/resources/lib/lightbox/css/lightbox.min.css" rel="stylesheet">

        <!-- Main Stylesheet File -->
        <link href="<%= request.getContextPath()%>/resources/css/style.css" rel="stylesheet">

    </head>
    <body class="${param.bgBody}">