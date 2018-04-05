<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="theUser" class="business.User" scope="session"/>
<%--
    Document   : header
    Created on : Oct 6, 2017, 8:09:40 PM
    Author     : Adam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <head>
        <!-- Basic Tags -->
        <meta charset="utf-8">
        <title>Aperture Photography</title>
        
        <!-- Mobile Specific -->
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        
        <!-- Style Sheets -->
        <link rel="stylesheet" type="text/css" href="styles/main.css"> 
        
        <!-- Compatibility for older versions of IE-->
        <!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![end if]-->
    </head>
    
    <body>
        
        <header>
                <c:if test="${theUser.firstName != ''}">
                    <p id="accountName">Welcome, <c:out value="${theUser.firstName}"/>
                        <c:out value="${theUser.lastName}"/></p>
                </c:if>
                <c:if test="${theUser.firstName == ''}">
                    <p id="accountName">Not signed in.</p>
                </c:if>
            <section id="mainHeader">
                <img id="logo" src="imgs/aperture.png" alt="Aperture Logo" height="150">
            <h1 id="mainHeading">Aperture Photography Shop</h1>
            </section>