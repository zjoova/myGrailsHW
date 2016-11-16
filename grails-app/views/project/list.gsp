<%@ page import="com.ms.memsource.api.RestService"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-project" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <g:applyLayout name="navigation" params="[class: 'project']"/>
        <div id="list-project" class="content scaffold-edit" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <div id="api-response-content" class="hidden">To be hidden</div>
            <div id="api-response-loader"><g:message code="content.loading.message" default="Content is loaded, please wait..."/></div>
            <div id="api-response-error" class="errors hidden" role="alert">Error message</div>
        </div>
        <script type="text/javascript">
            var contentURL = '${RestService.getApiUrl()}';
        </script>
    </body>
</html>
