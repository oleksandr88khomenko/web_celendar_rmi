<%--
  Created by IntelliJ IDEA.
  User: oleksandr_khomenko
  Date: 04.11.2014
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <link rel='stylesheet' type='text/css' href='resources/reset.css'/>
    <link rel='stylesheet' type='text/css'
          href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/start/jquery-ui.css'/>
    <link rel='stylesheet' type='text/css' href='resources/jquery_webcalendar.css'/>
    <link rel='stylesheet' type='text/css' href='resources/webcalendar.css'/>

    <script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js'></script>
    <script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js'></script>
    <script type='text/javascript' src='resources/jquery.weekcalendar.js'></script>
    <script type='text/javascript' src='resources/webcalendar.js'></script>


</head>
<body>
<div id='calendar'>
    <select name="selectfrom" id="select-from">
        <option value="">For all</option>
        <option value="">Oleksandr Khomenko</option>
        <option value="">Vasya Pupkin</option>
        <option value="">Andriy Lozoviy</option>
    </select>
    <label for="title"></label><input type="text" name="title"/> <input type="button" value="Add attender">

</div>
<div id="event_edit_container">
    <form>
        <input type="hidden"/>
        <ul>
            <li>
                <span>Date: </span><span class="date_holder"></span>
            </li>
            <li>
                <label for="start">Start Time: </label><select name="start">
                <option value="">Select Start Time</option>
            </select>
            </li>
            <li>
                <label for="end">End Time: </label><select name="end">
                <option value="">Select Start Time</option>
            </select>
            </li>
            <li>
                <label for="title">Title: </label><input type="text" name="title"/>
            </li>
            <li>
                <label for="body">Description: </label><textarea name="body"></textarea>
            </li>
            <!--<li>-->
            <!--<label for="attenders">Attenders: </label><textarea name="attenders"></textarea>-->
            <!--</li>-->
        </ul>
        <fieldset>
            <label for="selectfrom">Attenders to choose: </label>
            <select name="selectfrom" id="select-from" multiple size="1">
                <option value="">Oleksandr Khomenko</option>
                <option value="">Vasya Pupkin</option>
                <option value="">Andriy Lozoviy</option>
            </select>

            <a href="JavaScript:void(0);" id="btn-add">Add</a>
            <a href="JavaScript:void(0);" id="btn-remove">Remove</a>

            <select name="selectto" id="select-to" multiple size="1">
            </select>

        </fieldset>
    </form>
</div>

</body>
</html>



