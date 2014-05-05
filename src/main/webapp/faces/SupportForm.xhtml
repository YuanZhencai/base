<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<f:view>

<html>
<head>
<title>Support Form</title>
    <script type="text/javascript" src="/script/AutoSaveScript.js" />

    <script type="text/javascript">
        function getFormElement(formId, elemId) {
            return document.getElementById(formId + ":" + elemId);
        }
        
        function submitSaveRequest() {
            submitAllForms();
            return false;
        }
        
        function submitRestoreRequest() {
            var restoreTrigger
                = getFormElement("supportForm", "restoreTrigger");
            restoreTrigger.value = "restore";
            var supportForm = document.getElementById("supportForm");
            supportForm.submit();
        }
        
        function isRestorable() {
            return "${pageContext.request.method}".toUpperCase() == "GET"
                && <h:outputText value="#{viewRestorer.currentViewRestorable}"/>;
        }
        
        var autoSaveInterval = 10000;
    
        function init() {
            if (isRestorable())
                if (confirm("Do you want to restore the auto-saved form?")) {
                    submitRestoreRequest();
                    return;
                }
            var autoSaveCheckbox
                = getFormElement("supportForm", "autoSaveCheckbox");
            if (autoSaveCheckbox.checked)
                setAutoSaving(autoSaveInterval);
        }
    </script>
</head>
<body onload="init()">

    <h1>Support Form</h1>

    <h:form id="supportForm">
        <h:inputHidden id="restoreTrigger" value="default"
                valueChangeListener="#{viewRestorer.valueChangeListener}"
                immediate="true"/>

        <h:commandButton id="saveButton" value="Save"
                onclick="return submitSaveRequest()"/>

        <h:commandButton id="restoreButton" value="Restore"
                actionListener="#{viewRestorer.actionListener}"
                immediate="true"/>

        <h:selectBooleanCheckbox id="autoSaveCheckbox"
                onclick="setAutoSaving(this.checked ? autoSaveInterval : 0)"/>
        <h:outputLabel value="Auto-Save Form" for="autoSaveCheckbox"/>

        <p><h:outputText value="Name: "/>
        <h:message for="name"/><br>
        <h:inputText id="name" value="#{supportBean.name}"
                size="40" required="true">
        </h:inputText>

        <p><h:outputText value="Email: "/>
        <h:message for="email"/><br>
        <h:inputText id="email" value="#{supportBean.email}"
                size="40" required="true">
        </h:inputText>

        <p><h:outputText value="Versions: "/>
        <h:message for="versions"/><br>
        <h:selectManyListbox id="versions"
                value="#{supportBean.versions}" required="true">
            <f:selectItem itemValue="2.0.1" itemLabel="2.0.1"/>
            <f:selectItem itemValue="2.0.0" itemLabel="2.0.0"/>
            <f:selectItem itemValue="1.1.0" itemLabel="1.1.0"/>
            <f:selectItem itemValue="1.0.1" itemLabel="1.0.1"/>
            <f:selectItem itemValue="1.0.0" itemLabel="1.0.0"/>
        </h:selectManyListbox>

        <p><h:outputText value="Platform: "/>
        <h:message for="platform"/><br>
        <h:selectOneRadio id="platform" value="#{supportBean.platform}"
                layout="lineDirection" required="true">
            <f:selectItem itemValue="Windows" itemLabel="Windows"/>
            <f:selectItem itemValue="Linux" itemLabel="Linux"/>
            <f:selectItem itemValue="Mac" itemLabel="Mac"/>
        </h:selectOneRadio>

        <p><h:outputText value="Browser: "/>
        <h:message for="browser"/><br>
        <h:selectOneMenu id="browser" value="#{supportBean.browser}"
                required="true">
            <f:selectItem itemValue="" itemLabel=""/>
            <f:selectItem itemValue="IE" itemLabel="IE"/>
            <f:selectItem itemValue="Firefox" itemLabel="Firefox"/>
            <f:selectItem itemValue="Netscape" itemLabel="Netscape"/>
            <f:selectItem itemValue="Mozilla" itemLabel="Mozilla"/>
            <f:selectItem itemValue="Opera" itemLabel="Opera"/>
            <f:selectItem itemValue="Safari" itemLabel="Safari"/>
        </h:selectOneMenu>

        <p><h:selectBooleanCheckbox id="crash"
                value="#{supportBean.crash}"/>
        <h:outputText value="Causes browser crash"/>

        <p><h:outputText value="Problem: "/>
        <h:message for="problem"/><br>
        <h:inputTextarea id="problem" value="#{supportBean.problem}"
                rows="10" cols="40" required="true"/>

        <p><h:commandButton id="submit" value="Submit"
                action="#{supportBean.submit}"/>

    </h:form>

</body>
</html>

</f:view>
