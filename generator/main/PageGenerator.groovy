class PageGenerator {
    def TEMPLATE_PATH = ".\\main\\template";
    def OUTPUT_PATH = ".\\output"

    def numberTypeList = ['short',"int","long","float","double","java.lang.Integer","java.lang.Long","java.lang.Float","java.lang.Double",'java.math.BigDecimal']
    def boolTypeList = ["bool","java.lang.Boolean"]
    def stringTypeList = ["java.lang.String"]
    def dateTypeList = ["java.util.Date","java.sql.Date"]

    def excludeProperties = ['id','createdDatetime','createdBy','updatedDatetime','updatedBy','defunctInd']

    def declarationName = ""
    def entityName = ""

    def generate(Class clazz) {

        declarationName = clazz.getName().tokenize(".")[-1]
        entityName = declarationName[0].toLowerCase()+declarationName[1..-1]

        //println """-------------------------${declarationName}--------------------------------------------"""
		
		//def obj = clazz.newInstance()
        //def items = gen_item_str(obj.metaClass.properties)
		def items = gen_item_str(clazz.metaClass.properties)
		
        gen_page_file(items)
		
        return entityName + "'s pages generated successfully!"
    }

    def gen_item_str(beanProperties) {
        def inputItemStr = ""
        def viewItemStr = ""
        def listItemStr = ""
        def queryItemStr = ""
        def items = ['', '', '', '']

        for (it in beanProperties) {
            //beanProperties.each{
            if (excludeProperties.contains(it.name)) {
                continue
            }
            //println "name(${it.type.name}) =${it.name}"
            if (numberTypeList.contains(it.type.name)) {
                items = gen_item_for_number(it)
            } else if (stringTypeList.contains(it.type.name)) {
                items = gen_item_for_string(it)
            } else if (dateTypeList.contains(it.type.name)) {
                items = gen_item_for_date(it)
            } else if (boolTypeList.contains(it.type.name)) {
                items = gen_item_for_bool(it)
            } else {
                continue
            }

            inputItemStr = inputItemStr + items[0]
            viewItemStr = viewItemStr + items[1]
            listItemStr = listItemStr + items[2]
            queryItemStr = queryItemStr + items[3]

        }
        return [inputItemStr, viewItemStr, listItemStr, queryItemStr]
    }

    def gen_item_for_number(beanProperty){
         //print  "type=" + it.type.name  //+ ", package=" + it.type.package.name
         def inputItem = """
         <h:outputLabel value="${beanProperty.name}:"/>
         <p:inputText id="${entityName}_${beanProperty.name}" value="#{${entityName}Bean.instance.${beanProperty.name}}"/>
        """
        def viewItem = """
        <h:outputLabel value="${beanProperty.name}:"/>
        <h:outputText value="#{${entityName}Bean.instance.${beanProperty.name}}" style="font-weight:bold"/>
        """
        def listItem = """
        <p:column>
            <f:facet name="header"><h:outputText value="${beanProperty.name}"/>
            </f:facet><h:outputText value="#{${entityName}.${beanProperty.name}}"/>
        </p:column>
        """
        def queryItem = """
        <h:outputLabel value="${beanProperty.name}:"/>
        <input type="text" id="filter_EQI_${beanProperty.name}" name="filter_EQI_${beanProperty.name}"
            class="input_160" value="#{param['filter_EQI_${beanProperty.name}']}"/>
        """
        //println  "name(${beanProperty.type.name}) =${beanProperty.name}"
        return [inputItem, viewItem, listItem, queryItem]
    }

    def gen_item_for_string(beanProperty){
        //print  "type=" + it.type.name  //+ ", package=" + it.type.package.name
        def inputItem = """
            <h:outputLabel value="${beanProperty.name}:"/>
            <p:inputText id="${entityName}_${beanProperty.name}" value="#{${entityName}Bean.instance.${beanProperty.name}}"/>
            """
        def viewItem = """
        <h:outputLabel value="${beanProperty.name}:"/>
        <h:outputText value="#{${entityName}Bean.instance.${beanProperty.name}}" style="font-weight:bold"/>
        """
        def listItem = """
        <p:column>
            <f:facet name="header"><h:outputText value="${beanProperty.name}"/>
            </f:facet><h:outputText value="#{${entityName}.${beanProperty.name}}"/>
        </p:column>
        """
        def queryItem = """
        <h:outputLabel value="${beanProperty.name}:"/>
        <input type="text" id="filter_LIKES_${beanProperty.name}" name="filter_LIKES_${beanProperty.name}"
            class="input_160" value="#{param['filter_LIKES_${beanProperty.name}']}"/>
        """
        return [inputItem, viewItem, listItem,queryItem]
    }

    def gen_item_for_date(beanProperty){
        //print  "type=" + it.type.name  //+ ", package=" + it.type.package.name
        def inputItem = """
            <h:outputLabel value="${beanProperty.name}:"/>
            <p:calendar id="${entityName}_${beanProperty.name}" value="#{${entityName}Bean.instance.${beanProperty.name}}"/>
            """
        def viewItem = """
        <h:outputLabel value="${beanProperty.name}:"/>
        <h:outputText value="#{${entityName}Bean.instance.${beanProperty.name}}" style="font-weight:bold"/>
        """
        def listItem = """
        <p:column>
            <f:facet name="header"><h:outputText value="${beanProperty.name}"/>
            </f:facet><h:outputText value="#{${entityName}.${beanProperty.name}}"/>
        </p:column>
        """
        def queryItem = """
        <h:outputLabel value="${beanProperty.name}:"/>
        <input type="text" id="filter_EQD_${beanProperty.name}" name="filter_EQD_${beanProperty.name}"
            class="input_160" value="#{param['filter_EQD_${beanProperty.name}']}"/>
        """
        return [inputItem, viewItem, listItem, queryItem]
    }

    def gen_item_for_bool(beanProperty){
        //print  "type=" + it.type.name  //+ ", package=" + it.type.package.name
        def inputItem = """
            <h:outputLabel value="${beanProperty.name}:"/>
            <h:selectBooleanCheckbox id="${entityName}_${beanProperty.name}" value="#{${entityName}Bean.instance.${beanProperty.name}}"/>
            """
        def viewItem = """
        <h:outputLabel value="${beanProperty.name}:"/>
        <h:outputText value="#{${entityName}Bean.instance.${beanProperty.name}}" style="font-weight:bold"/>
        """
        def listItem = """
        <p:column>
            <f:facet name="header"><h:outputText value="${beanProperty.name}"/>
            </f:facet><h:outputText value="#{${entityName}.${beanProperty.name}}"/>
        </p:column>
        """
        def queryItem = """
        <h:outputLabel value="${beanProperty.name}:"/>
        <input type="checkbox" id="filter_EQB_${beanProperty.name}" name="filter_EQB_${beanProperty.name}"
            value="#{param['filter_EQB_${beanProperty.name}']}"/>
        """
        return [inputItem, viewItem, listItem, queryItem]
    }

    def gen_page_file(items){
        def pagePath = OUTPUT_PATH+'\\pages\\'+entityName
        def d1= new File(pagePath)
        if (!d1.exists()) d1.mkdirs()

        def inputItemStr = items[0]
        def viewItemStr = items[1]
        def listItemStr = items[2]
        def queryItemStr = items[3]

        gen_input_item_file(inputItemStr,pagePath)
        gen_input_file(pagePath)
        gen_view_file(viewItemStr,pagePath)
        gen_list_file(queryItemStr, listItemStr,pagePath)
    }

    def gen_input_item_file(inputItemStr, pagePath){
        def text = new File( TEMPLATE_PATH+'\\pages\\input-item.xhtml' ).getText()
        text = text.replace("INPUT_ITEM_LIST",inputItemStr)

        new File(pagePath+"\\input-item.xhtml").withWriter{ writer ->
            writer.write text
        }
    }

     def gen_input_file(pagePath){
        def text = new File( TEMPLATE_PATH+'\\pages\\input.xhtml' ).getText()
        text = text.replace('ENTITY_NAME_',entityName)

        new File(pagePath+"\\input.xhtml").withWriter{ writer ->
            writer.write text
        }
    }

     def gen_view_file(viewItemStr, pagePath){
        def text = new File( TEMPLATE_PATH+'\\pages\\view.xhtml' ).getText()
        text = text.replace('VIEW_ITEM_LIST',viewItemStr).replace('ENTITY_NAME_',entityName)

        new File(pagePath+"\\view.xhtml").withWriter{ writer ->
            writer.write text
        }
    }

    def gen_list_file(queryItemStr, listItemStr,pagePath){
        def text = new File( TEMPLATE_PATH+'\\pages\\list.xhtml' ).getText()
        text = text.replace('QUERY_ITEM_LIST',queryItemStr).replace('LIST_ITEM_LIST',listItemStr).replace('ENTITY_NAME_',entityName)

        new File(pagePath+"\\list.xhtml").withWriter{ writer ->
            writer.write text
        }
    }
}





