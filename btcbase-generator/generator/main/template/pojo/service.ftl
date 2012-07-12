${pojo.getPackageDeclaration()}
<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign entityName = declarationName?uncap_first>

import com.wcs.base.service.EntityService;
import com.wcs.base.util.Page;
import com.wcs.ncp.model.${declarationName};
import org.jboss.seam.transaction.TransactionPropagation;
import org.jboss.seam.transaction.Transactional;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ConversationScoped
public class ${declarationName}Service implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    EntityService entityService;

    /**
     * �õ���ҳ���
     *
     * @param page
     * @return
     */
    @Transactional(TransactionPropagation.REQUIRED)
    public Page<${declarationName}> getListByPage(Page<${declarationName}> page) {
        String sql = "select b from ${declarationName} b";
        try {
            return entityService.findPage(page, sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}


</#assign>

${pojo.generateImports()}
import ${pojo.getQualifiedDeclarationName()};

${classbody}
