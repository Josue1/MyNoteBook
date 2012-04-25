<%@ page import="notebook.Student" %>



<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'courseCode', 'error')} ">
	<label for="courseCode">
		<g:message code="student.courseCode.label" default="Course Code" />
		
	</label>
	<g:textField name="courseCode" value="${studentInstance?.courseCode}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="student.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${studentInstance?.name}"/>
</div>

