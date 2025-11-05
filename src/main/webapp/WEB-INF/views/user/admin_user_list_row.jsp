<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<td>
    <a href="/admin/user_detail/${rowItem.usrId}">${rowItem.logId}</a>
</td>
<td>${rowItem.nm}</td>

<c:choose>
    <c:when test="${isBlogger}">
        <td>${empty rowItem.blgAddrs ? '-' : rowItem.blgAddrs}</td>
        <td>${empty rowItem.rcntBlgCrtfctnDt ? '-' : rowItem.rcntBlgCrtfctnDt}</td>
        <td>${empty rowItem.sbscrptnExprsDt ? '-' : rowItem.sbscrptnExprsDt}</td>
    </c:when>

    <c:when test="${isAdvertiser}">
		<td>${rowItem.registAcpt}</td>
	</c:when>
</c:choose>
<td>${rowItem.cmpnPrgrssCnt}</td>
<td>${rowItem.cmpnAllCnt}</td>
<td>${rowItem.rcntLgnScsDt}</td>
<td>${rowItem.pnltCnt}</td>
<td>${rowItem.crtDt}</td>