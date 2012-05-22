<%@ page import="notebook.Placement" %>



<div class="fieldcontain ${hasErrors(bean: placementInstance, field: 'companyName', 'error')} ">
	<label for="companyName">
		<g:message code="placement.companyName.label" default="Company Name" />
		
	</label>
	<g:textField name="companyName" value="${placementInstance?.companyName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: placementInstance, field: 'jobTitle', 'error')} ">
	<label for="jobTitle">
		<g:message code="placement.jobTitle.label" default="Job Title" />
		
	</label>
	<g:textField name="jobTitle" value="${placementInstance?.jobTitle}"/>
</div>

