package jvm.addressbook.actions;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import jvm.addressbook.actions.utils.Utils;
import jvm.addressbook.dao.CrudDao;
import jvm.addressbook.vo.AddressBookVo;
import net.sourceforge.sannotations.annotation.Bean;
import net.sourceforge.sannotations.annotation.DataModel;
import net.sourceforge.sannotations.annotation.DataModelSelection;
import net.sourceforge.sannotations.annotation.ManagedBean;
import net.sourceforge.sannotations.annotation.Scope;

@ManagedBean
@Bean(name="addressBook", scope=Scope.SESSION)
public class AddressBookBean {

	private CrudDao<AddressBookVo, Long> addressBookDao;
	@DataModel
	private List<AddressBookVo> list;
	@DataModelSelection
	private AddressBookVo addressBookVo;
	
	public AddressBookBean() {}

	/**
	 * @return the addressBookDao
	 */
	public CrudDao<AddressBookVo, Long> getAddressBookDao() {
		return addressBookDao;
	}

	/**
	 * @param addressBookDao the addressBookDao to set
	 */
	public void setAddressBookDao(CrudDao<AddressBookVo, Long> addressBookDao) {
		this.addressBookDao = addressBookDao;
	}

	/**
	 * @return the addressBookVo
	 */
	public AddressBookVo getAddressBookVo() {
		return addressBookVo;
	}

	/**
	 * @param addressBookVo the addressBookVo to set
	 */
	public void setAddressBookVo(AddressBookVo addressBookVo) {
		this.addressBookVo = addressBookVo;
	}

	/**
	 * @return the list
	 */
	public List<AddressBookVo> getList() {
		if (list == null)
			list = addressBookDao.listAll();
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<AddressBookVo> list) {
		this.list = list;
	}
	
	public String getDefaultMessage() {
		if (Utils.isMensagensEmpty())
			addMessage("listMsg");
		
		return null;
	}
	
	public int getRows() {
		if (list == null)
			return 0;
		return list.size();
	}
	
	public String createNew() {
		addressBookVo = new AddressBookVo();
		addMessage("newMsg");
		return "def:form";
	}
	
	public String save() {
		if (addressBookVo.getId() == null) {
			addressBookDao.save(addressBookVo);
			addMessage("createSuccess");
		} else {
			addressBookDao.update(addressBookVo);
			addMessage("updateSuccess");
		}
		
		return "def:form";
	}
	
	public String delete() {
		addressBookDao.delete(addressBookVo);
		list = null;
		addMessage("deleteSuccess");
		return "def:list";
	}
	
	public String edit() {
		addMessage("editMsg");
		return "def:form";
	}
	
	public String list() {
		list = null;
		return "def:list";
	}

	private void addMessage(String label) {
		FacesMessage f = new FacesMessage(Utils.getResourceString(label, null));
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, f);
	}
	
	
}
