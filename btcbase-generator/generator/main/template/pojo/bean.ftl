${pojo.getPackageDeclaration()}
<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign entityName = declarationName?uncap_first>

import com.google.common.collect.Lists;
import com.wcs.base.service.EntityService;
import com.wcs.base.util.Page;
import com.wcs.base.util.orm.PersistenceUtils;
import com.wcs.base.view.BaseBean;
import com.wcs.base.view.PropertyFilter;
import com.wcs.ncp.model.${declarationName};
import com.wcs.ncp.service.demo.${declarationName}Service;
import org.primefaces.model.LazyDataModel;

import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author gjhuai email:gjhuai@gmail.com
 * @version 1.0
 * @since 1.0
 */
 
@${pojo.importType("javax.inject.Named")}
@${pojo.importType("javax.enterprise.context.ConversationScoped")}
public class ${declarationName}Bean extends BaseBean<${declarationName}>{
	 private static final long serialVersionUID = 1L;
	
	 @${pojo.importType("javax.inject.Inject")}
        EntityService entityService;
        @Inject
	 private ${declarationName}Service ${entityName}Service;
     
	private List<${declarationName}> ${entityName}List = Lists.newArrayList();
	private Page<${declarationName}> page = new Page<${declarationName}>(10);
	private LazyDataModel<${declarationName}> lazyModel;
	
	 /**
	 * 构造函数
	 */
	public ${declarationName}Bean() {
        this.setupPage("/faces/${entityName}/list.xhtml",
                "/faces/${entityName}/input.xhtml",
                "/faces/${entityName}/view.xhtml");
    }

	/**
	 * 查找图书
	 * @return
	 */
	public String search(){
        System.out.println("${declarationName}Bean =>  search");

        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        List<PropertyFilter> filters = PersistenceUtils.buildPropertyFilters(request);
        PropertyFilter notDefunct = new PropertyFilter("EQB_defunctInd","false");
        filters.add(notDefunct);

		${entityName}List = entityService.find(${declarationName}.class, filters);

		return this.getListPage();
	}

    public LazyDataModel<${declarationName}> getLazyModel() {
    	 lazyModel = new LazyDataModel<${declarationName}>(){
             @Override
             public List<${declarationName}> load(int first, int pageSize, String sortField, boolean sortOrder, Map<String, String> filters)
             {
                 System.out.println("first = "+first+"  pageSize = "+pageSize);
                 System.out.println(filters);
                 //return populateLazyRandomBoos(pageSize);

                 ${declarationName}Bean.this.search();
                 return ${entityName}List;
             }

         };
         lazyModel.setPageSize(2);
         lazyModel.setRowCount(3);
        return lazyModel;
    }

/*
    private List<${declarationName}> populateLazyRandomBoos(int size) {
        List<${declarationName}> list = new ArrayList<${declarationName}>();

        for(int i = 0 ; i < size ; i++) {
            list.add(new ${declarationName}(UUID.randomUUID().toString().substring(0, 8), UUID.randomUUID().toString().substring(0, 8)));
        }
        return list;
    }
 */
    /**
     * 跳转到输入页面
     * @return
     */
    public String input(){
        System.out.println("${declarationName}Bean => input()");
        return this.getInputPage();
    }

    public String view(){
        System.out.println("${declarationName}Bean => view()");
        return this.getViewPage();
    }

    public List<${declarationName}> get${declarationName}List() {
        //System.out.println("${declarationName}Bean => get${declarationName}List");
        if (${entityName}List.size()==0l)  search();          /* 不加这行，“修改”不能跳转   */
        return ${entityName}List;
    }

    public void set${declarationName}List(List<${declarationName}> ${entityName}List) {
        this.${entityName}List = ${entityName}List;
    }

    public Page<${declarationName}> getPage() {
		page = this.${entityName}Service.getListByPage(page);
		return page;
	}

    public void setPage(Page<${declarationName}> page) {
		this.page = page;
	}
}
</#assign>

${pojo.generateImports()}
import ${pojo.getQualifiedDeclarationName()};

${classbody}
