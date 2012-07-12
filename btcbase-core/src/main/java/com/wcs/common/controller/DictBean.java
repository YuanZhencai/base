package com.wcs.common.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.wcs.common.controller.vo.DictVO;
import com.wcs.common.model.Dict;
import com.wcs.common.service.DictService;

@ManagedBean(name="dictBean")
@ViewScoped
public class DictBean implements Serializable{

	private static final long serialVersionUID = 7687928301071245573L;

	@EJB DictService dictService;
	
	//index页面参数
	private Map<String, String> query = new HashMap<String, String>(5);
	private LazyDataModel<DictVO> indexLazyModel;
	
	//insertdialog页面参数
	private Dict addDict;
	private String dictSeqNo;  //因为model的SeqNO为long类型,无法在首次进入为NULL.
	private String sysInd="Y";
	private String defunctInd="N";
	
	public void initAddDict() {
        addDict=new Dict();
    }
	
    // reset search conditions
    public void reset() {
        query.clear();
    }
 
	//updatedialog页面参数
	private DictVO selectData=new DictVO();
	
	//构造方法
	public DictBean(){
		indexLazyModel = new LazyDataModel<DictVO>() {
			private static final long serialVersionUID = 8692533692951707240L;
			List<DictVO> bbv = null;
			@Override
			public List<DictVO> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				bbv=dictService.searchData(null,null,null,null,null);
				int size = bbv.size();
				this.setRowCount(size);
				if (size > pageSize) {
					try {
						return bbv.subList(first, first + pageSize);
					} catch (IndexOutOfBoundsException e) {
						return bbv.subList(first, first + (size % pageSize));
					}
				} else {
					return bbv;
				}
			}

			@Override
			public Object getRowKey(DictVO object) {
				return object.getId();
			}
			@Override
			public DictVO getRowData(String rowKey) {
				for (DictVO b : bbv) {
					if (b.getId() == Integer.parseInt(rowKey)) {
						return b;
					}
				}
				return null;
			}
			
		};
	}
	
	//查询 String codeCat,String codeKey,String codeVal,String sysInd, String lang
	public void serachData(){
		//query.get("codeCat"),query.get("codeKey"),query.get("codeVal"),query.get("sysInd"),query.get("lang")
		indexLazyModel = new LazyDataModel<DictVO>() {
			private static final long serialVersionUID = 8692533692951707240L;
			List<DictVO> bbv = null;
			@Override
			public List<DictVO> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				bbv=dictService.searchData(query.get("codeCat"),query.get("codeKey"),query.get("codeVal"),query.get("sysInd"),query.get("lang"));
				int size = bbv.size();
				this.setRowCount(size);
				if (size > pageSize) {
					try {
						return bbv.subList(first, first + pageSize);
					} catch (IndexOutOfBoundsException e) {
						return bbv.subList(first, first + (size % pageSize));
					}
				} else {
					return bbv;
				}
			}

			@Override
			public Object getRowKey(DictVO object) {
				return object.getId();
			}
			@Override
			public DictVO getRowData(String rowKey) {
				for (DictVO b : bbv) {
					if (b.getId() == Integer.parseInt(rowKey)) {
						return b;
					}
				}
				return null;
			}
			
		}; //end lazyModel
	}

	
	//添加
	public void insertData(){
		
		//注意SeqNO必须为NUM.在前台进行验证.
		if(dictSeqNo =="" || dictSeqNo == null){
			addDict.setSeqNo(0);
		}else{
			addDict.setSeqNo(Long.parseLong(dictSeqNo));
		}
		//addDict.setCreatedBy(loginService.getCurrentUserName());
		addDict.setCreatedDatetime(new Date());
		//addDict.setUpdatedBy(loginService.getCurrentUserName());
		addDict.setUpdatedDatetime(new Date());
		//执行添加方法
		addDict.setDefunctInd(defunctInd);
		addDict.setSysInd(sysInd);
		dictService.insertData(addDict);
		//添加成功关闭dialog窗口
		RequestContext.getCurrentInstance().addCallbackParam("addInfo","yes");
	}
	
	//编辑
	public void updateData(){
		//dictService.updateData(selectData, loginService.getCurrentUserName());
		RequestContext.getCurrentInstance().addCallbackParam("updateInfo", "yes");
	}

	
	//get and set
	public Dict getAddDict() {
		return addDict;
	}

	public void setAddDict(Dict addDict) {
		this.addDict = addDict;
	}

	public LazyDataModel<DictVO> getIndexLazyModel() {
		return indexLazyModel;
	}

	public void setIndexLazyModel(LazyDataModel<DictVO> indexLazyModel) {
		this.indexLazyModel = indexLazyModel;
	}

	public Map<String, String> getQuery() {
		return query;
	}

	public void setQuery(Map<String, String> query) {
		this.query = query;
	}

	public String getDictSeqNo() {
		return dictSeqNo;
	}

	public void setDictSeqNo(String dictSeqNo) {
		this.dictSeqNo = dictSeqNo;
	}

	public DictVO getSelectData() {
		return selectData;
	}

	public void setSelectData(DictVO selectData) {
		this.selectData = selectData;
	}

	public String getSysInd() {
		return sysInd;
	}

	public void setSysInd(String sysInd) {
		this.sysInd = sysInd;
	}
	public String getDefunctInd() {
		return defunctInd;
	}

	public void setDefunctInd(String defunctInd) {
		this.defunctInd = defunctInd;
	}
}
