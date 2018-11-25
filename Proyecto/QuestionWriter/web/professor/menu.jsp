<%--
    Document   : menu
    Created on : Nov 24, 2018, 7:38:49 PM
    Author     : damian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import ="professorpkg.*"%>
<!DOCTYPE html>
<html>
    <title>Menu Professor</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <ling rel="stylesheet" href="css/menu.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    
    <body>
        <div class="w3-sidebar w3-bar-block w3-black w3-xxlarge center" style="width:70px">
            <button href="#" class="w3-bar-item w3-button"><i class="far fa-question-circle"></i></button> 
            <button href="#" class="w3-bar-item w3-button"><i class="far fa-clipboard"></i></button> 
        </div>

        <div style="margin-left:70px">
            <div class="w3-container header">
                <h1>Bienvenido <%= session.getAttribute("userName") %></h1>
                <%%>
            </div>

        </div>

    </body>
</html>
