<%@ page import="notebook.Status" %>



<div class="fieldcontain ${hasErrors(bean: statusInstance, field: 'code', 'error')} ">
	<label for="code">
		<g:message code="status.code.label" default="Code" />
		
	</label>
	<g:textField name="code" value="${statusInstance?.code}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: statusInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="status.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${statusInstance?.description}"/>
</div>

