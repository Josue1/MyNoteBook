
<%@ page import="notebook.Placement" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'placement.label', default: 'Placement')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-placement" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-placement" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="companyName" title="${message(code: 'placement.companyName.label', default: 'Company Name')}" />
					
						<g:sortableColumn property="jobTitle" title="${message(code: 'placement.jobTitle.label', default: 'Job Title')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${placementInstanceList}" status="i" var="placementInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${placementInstance.id}">${fieldValue(bean: placementInstance, field: "companyName")}</g:link></td>
					
						<td>${fieldValue(bean: placementInstance, field: "jobTitle")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${placementInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
