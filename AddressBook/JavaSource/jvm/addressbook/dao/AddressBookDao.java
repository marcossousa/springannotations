package jvm.addressbook.dao;

import net.sourceforge.sannotations.annotation.Bean;
import jvm.addressbook.vo.AddressBookVo;

@Bean(name="addressBookDao")
public class AddressBookDao extends CrudDaoImpl<AddressBookVo, Long> {
	public AddressBookDao() {
		super(AddressBookVo.class);
	}
}
