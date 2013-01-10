<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
{
	"scatter" : [
	<c:forEach items="${scatter}" var="dot" varStatus="status">
		{
			"traceId" : "${dot.traceId}",
			"timestamp" : ${dot.timestamp},
			"executionTime" : ${dot.executionTime},
			"resultCode" : ${dot.resultCode}
		}
	    <c:if test="${!status.last}">,</c:if>
	</c:forEach>
	]
}