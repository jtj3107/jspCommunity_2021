package com.jtj.example.jspCommunity.service;

import com.jtj.example.jspCommunity.container.Container;
import com.jtj.example.jspCommunity.dao.AttrDao;
import com.jtj.example.jspCommunity.dto.Attr;

public class AttrService {
	private AttrDao attrDao;

	public AttrService() {
		attrDao = Container.attrDao;
	}

	public Attr get(String name) {
		String[] nameBits = name.split("__");
		String relTypeCode = nameBits[0];
		int relId = Integer.parseInt(nameBits[1]);
		String typeCode = nameBits[2];
		String type2Code = nameBits[3];

		return get(relTypeCode, relId, typeCode, type2Code);
	}

	public Attr get(String relTypeCode, int relId, String typeCode, String type2Code) {
		return attrDao.get(relTypeCode, relId, typeCode, type2Code);
	}

	public int setValue(String name, String value, String expireDate) {
		String[] nameBits = name.split("__");
		String relTypeCode = nameBits[0];
		int relId = Integer.parseInt(nameBits[1]);
		String typeCode = nameBits[2];
		String type2Code = nameBits[3];

		return setValue(relTypeCode, relId, typeCode, type2Code, value, expireDate);
	}

	public String getValue(String name) {
		String[] nameBits = name.split("__");
		String relTypeCode = nameBits[0];
		int relId = Integer.parseInt(nameBits[1]);
		String typeCode = nameBits[2];
		String type2Code = nameBits[3];

		return getValue(relTypeCode, relId, typeCode, type2Code);
	}

	public String getValue(String relTypeCode, int relId, String typeCode, String type2Code) {
		String value = attrDao.getValue(relTypeCode, relId, typeCode, type2Code);

		if (value == null) {
			return "";
		}

		return value;
	}

	public int remove(String name) {
		String[] nameBits = name.split("__");
		String relTypeCode = nameBits[0];
		int relId = Integer.parseInt(nameBits[1]);
		String typeCode = nameBits[2];
		String type2Code = nameBits[3];

		return remove(relTypeCode, relId, typeCode, type2Code);
	}

	public int remove(String relTypeCode, int relId, String typeCode, String type2Code) {
		return attrDao.remove(relTypeCode, relId, typeCode, type2Code);
	}

	public int setValue(String relTypeCode, int relId, String typeCode, String type2Code, String value, String expireDate) {
		attrDao.setValue(relTypeCode, relId, typeCode, type2Code, value, expireDate);
		Attr attr = get(relTypeCode, relId, typeCode, type2Code);

		if (attr != null) {
			return attr.getId();
		}

		return -1;
	}

	public void setValue(String name, boolean value, String expireDate) {
		setValue(name, value ? "1" : "0", expireDate);
	}

	public boolean getValueASBoolean(String name) {
		String value = getValue(name);
		
		if( value == null || value.equals("1") == false) {
			return false;
		}
		
		return true;
	}
}
