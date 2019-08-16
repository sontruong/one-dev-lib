/**
 * 
 */
package com.ones.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.ones.dto.FieldOption;
import com.ones.service.CDNService;
import com.ones.utils.ExcelUtils;
import com.ones.utils.MyReflectField;
import com.ones.utils.ReflectionUtils;
import com.ones.utils.StringUtils;

/**
 * @author son.truong
 *
 */
public class BaseService {

	@Autowired
	protected CDNService cdnService;

	protected void handleBeanValidationError(BindingResult bindingResult) throws BindException {
		if (bindingResult.hasErrors()) {
			throw new BindException(bindingResult);
		}
	}

	protected MyReflectField[] getFields(Object object, FieldOption[] fieldOptions, boolean subClass) throws BindException {
		if (null == object) {
			return null;
		}
		MyReflectField[] myReflectFields = ReflectionUtils.getAllFields(object, subClass);
		if (null != fieldOptions && 0 < fieldOptions.length) {
			Collection<MyReflectField> fields = new ArrayList<>();
			for (FieldOption fieldOption : fieldOptions) {
				for (MyReflectField myReflectField : myReflectFields) {
					if (myReflectField.getField().getName().equalsIgnoreCase(fieldOption.getFieldName())) {
						myReflectField.setTitle(fieldOption.getFieldTitle());
						fields.add(myReflectField);
					}
				}
			}
			if (!CollectionUtils.isEmpty(fields)) {
				return fields.toArray(new MyReflectField[fields.size()]);
			}
		}

		return myReflectFields;
	}
	
	protected MyReflectField[] getFields(Object object, MyReflectField[] tmpFields, Boolean subclass) {
		if (null == object || null == tmpFields) {
			return null;
		}
		MyReflectField[] myReflectFields = ReflectionUtils.getAllFields(object, subclass);
			Collection<MyReflectField> fields = new ArrayList<>();
			for (MyReflectField tmpField : tmpFields) {
				for (MyReflectField myReflectField : myReflectFields) {
					if (myReflectField.getField().getName().equalsIgnoreCase(tmpField.getField().getName())) {
						myReflectField.setTitle(tmpField.getTitle());
						fields.add(myReflectField);
					}
				}
			}
			if (!CollectionUtils.isEmpty(fields)) {
				return fields.toArray(new MyReflectField[fields.size()]);
			}
		return null;
	}

	protected MyReflectField[] getFields(Object object, InputStream inputStream, String sheetName, Boolean subClass)
			throws BindException, IOException {
		if (null == object || null == inputStream) {
			return null;
		}
		MyReflectField[] myReflectFields = ReflectionUtils.getAllFields(object, subClass);
		if (null == myReflectFields || 0 == myReflectFields.length) {
			return null;
		}
		Collection<MyReflectField> collection = new ArrayList<>();
		Row row = ExcelUtils.getRow(inputStream, sheetName, 1);
		for (int i = 0; i < myReflectFields.length; i++) {
			String fieldName = myReflectFields[i].getField().getName();
			for (int j = 0; j < myReflectFields.length; j++) {
				String cellvalue = ExcelUtils.getStringValue(row, j + 1);
				if (StringUtils.isEmpty(cellvalue)) {
					continue;
				}
				if (cellvalue.equalsIgnoreCase(fieldName)) {
					myReflectFields[i].setIndex(j);
					collection.add(myReflectFields[i]);
				}
			}
		}
		myReflectFields = collection.toArray(new MyReflectField[collection.size()]);
		return myReflectFields;
	}

	protected ArrayList<Object> getDatas(Object object, MultipartFile file, String sheetName, boolean subclass) throws BindException,
			IOException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		if (null == object || null == file) {
			return new ArrayList<>();
		}
		MyReflectField[] myFields = getFields(object, file.getInputStream(), sheetName, subclass);
		if (null == myFields || 0 == myFields.length) {
			return new ArrayList<>();
		}
		ArrayList<Object> results = new ArrayList<>();
		Collection<Row> collection = ExcelUtils.getListRows(file.getInputStream(), sheetName, 2);
		Iterator<Row> iterator = collection.iterator();
		while (iterator.hasNext()) {
			Row row = iterator.next();
			Object object2 = object.getClass().newInstance();
			for (int j = 0; j < myFields.length; j++) {
				String value = ExcelUtils.getStringValue(row, myFields[j].getIndex() + 1);
				ReflectionUtils.setFieldValue(object2, myFields[j].getField(), value);
			}
			results.add(object2);
		}

		return results;
	}
}
