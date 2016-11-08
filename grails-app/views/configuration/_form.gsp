<%@ page import="com.ms.memsource.Configuration" %>



<div class="fieldcontain ${hasErrors(bean: configurationInstance, field: 'userName', 'error')} required">
	<label for="userName">
		<g:message code="configuration.userName.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="userName" required="" value="${configurationInstance?.userName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: configurationInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="configuration.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${configurationInstance?.password}"/>

</div>

