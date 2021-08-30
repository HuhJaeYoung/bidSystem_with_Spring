<%@page import="java.util.ArrayList"%>
<%@page import="asadal.jaeyoung.vo.ScheduleVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	@SuppressWarnings("unchecked")
	List<ScheduleVO> list = (ArrayList<ScheduleVO>)request.getAttribute("showSchedule");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<link href='../../resources/css/fullcalendar.min.css' rel='stylesheet' />
<script src='../../resources/js/fullcalendar.min.js'></script>
<%@ include file="layout/header.jsp"%>
<script>

  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
    	   header: {
    	        left: 'prev,next today',
    	        center: 'title',
    	        right: 'month,agendaWeek,agendaDay,listMonth'
    	      },
      locale : "ko",
      navLinks: true, // can click day/week names to navigate views
      businessHours: true, // display business hours
      editable: true,
      
      events: [
<% 
			for(int i =0;i<list.size();i++){
        	ScheduleVO scheduleVO = (ScheduleVO)list.get(i);
%>        	
			{	
				title: '<%=scheduleVO.getSubject()%>',
				start: '<%=scheduleVO.getStartDate()%>',
				end : '<%=scheduleVO.getEndDate()%>'
			},
<%			
        }
%>
			{
				title:'default',
				start:"2021-01-01",
				end:"2021-01-01"
			}
      ]
    });

    calendar.render();
  });

</script>
<style>

  body {
   
    padding: 0;
    font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 900px;
    margin: 0 auto;
  }

</style>
</head>
<body>

  <div id='calendar'></div>
  <div id ='calendar' style="position: relative;">
  <c:if test="${member.auth eq 'ROLE_ADMIN' }">
  	<div>
  		<button class = "add-button" type="button"
  		onclick="click_add();">일정추가</button>
  		
  	</div>
  	</c:if>
  </div>

</body>
</html>

<%@ include file="layout/footer.jsp"%>