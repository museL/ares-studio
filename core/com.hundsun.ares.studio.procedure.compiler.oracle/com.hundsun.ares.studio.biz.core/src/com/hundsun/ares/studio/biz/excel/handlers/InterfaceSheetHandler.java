/**
 * Դ�������ƣ�InterfaceListHandler.java
 * ��������Ȩ���������ӹɷ����޹�˾ ��Ȩ����
 * ϵͳ���ƣ�JRES Studio
 * ģ�����ƣ�com.hundsun.ares.studio.biz.core
 * ����˵����$desc
 * ����ĵ���
 * ���ߣ�sundl
 */
package com.hundsun.ares.studio.biz.excel.handlers;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.core.excel.ISheetHandler;
import com.hundsun.ares.studio.core.excel.handler.BooleanEMFPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.EMFPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.EMFSheetHandler;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.NullPropertyHandler;
import com.hundsun.ares.studio.core.model.CorePackage;

/**
 * ���ڽӿڵ�Sheetҳ��Ĵ�����
 * @author sundl
 *
 */
public abstract class InterfaceSheetHandler extends EMFSheetHandler implements ISheetHandler{
	
	public static Logger logger = Logger.getLogger(InterfaceSheetHandler.class);

	/** �ӿڶ���һ�����Ե�handler */
	public static Map<String, IPropertyHandler> INTERFACE_PROPERTY_HANDLERS = new HashMap<String, IPropertyHandler>();
	/** �������Դ�����Ӧ�б� */
	public static Map<String, IPropertyHandler> PARAM_PROPERTY_HANDLERS = 	new HashMap<String, IPropertyHandler>();
	
	static {
		// ����Interface��һ�����Ժʹ������б��� ���ྡ����Ҫֱ������Щmap�м����ݣ���������֮�以�����
		INTERFACE_PROPERTY_HANDLERS.put("�����", new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID));
		// ȷ�����账�������ԣ���NullPropertyHandler���ⱨ��
		INTERFACE_PROPERTY_HANDLERS.put("�汾��", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("��������", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("������", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("��������", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("��������", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("����˵��", EMFPropertyHandler.CNAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("����˵��", EMFPropertyHandler.CNAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("������", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("��������", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("����˵��", EMFPropertyHandler.CNAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("������", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("��������", EMFPropertyHandler.NAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("����˵��", EMFPropertyHandler.CNAME_PROPERTY_HANDLER);
		INTERFACE_PROPERTY_HANDLERS.put("���������", new BooleanEMFPropertyHandler(BizPackage.Literals.BIZ_INTERFACE__OUTPUT_COLLECTION));
		INTERFACE_PROPERTY_HANDLERS.put("�ӿڱ�־", new EMFPropertyHandler(BizPackage.Literals.BIZ_INTERFACE__INTERFACE_FLAG));
		INTERFACE_PROPERTY_HANDLERS.put("˵��", new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__DESCRIPTION));
		INTERFACE_PROPERTY_HANDLERS.put("���ܺ�", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("�������ݿ�", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("������ɫ", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("�������", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("������", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("���󼶱�", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("ҵ������", new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__DESCRIPTION));
		//2014-01-28 modified by zhuyf ���ӹ����������������ӿڣ�ʱ�ĵ����д˸�ʽ��Ϣ��
		INTERFACE_PROPERTY_HANDLERS.put("��������", new EMFPropertyHandler(CorePackage.Literals.BASIC_RESOURCE_INFO__DESCRIPTION));
		INTERFACE_PROPERTY_HANDLERS.put("����˵��", NullPropertyHandler.INSTANCE);
		//2014-04-17 modified by zhuyf ���Ӳ�����ʾ��������ڲ�Ʒ����ϵͳ06��۰����д���Ϣ����ȥ������
		INTERFACE_PROPERTY_HANDLERS.put("������ʾ", NullPropertyHandler.INSTANCE);
		//2014-01-28 modified by zhuyf ����ҵ��˵�����������ӿڣ�ʱ�ĵ����д˸�ʽ��Ϣ��
		INTERFACE_PROPERTY_HANDLERS.put("ҵ��˵��", NullPropertyHandler.INSTANCE);
		
		INTERFACE_PROPERTY_HANDLERS.put("�޸ļ�¼", NullPropertyHandler.INSTANCE);
		
		INTERFACE_PROPERTY_HANDLERS.put("����", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("�ڲ�����", NullPropertyHandler.INSTANCE);
		INTERFACE_PROPERTY_HANDLERS.put("���̱���", NullPropertyHandler.INSTANCE);

		// ������:
		PARAM_PROPERTY_HANDLERS.putAll(BizPropertyHandler.PARAM_BASIC_HANDLERS);
	}
	
	protected Map<String, IPropertyHandler> getPropertyHandlers() {
		if (StringUtils.equals(parser.getCurrentBlockTag(), "�������")
				|| StringUtils.equals(parser.getCurrentBlockTag(), "�������")) {
			return PARAM_PROPERTY_HANDLERS;
		}
		return getDefaultPropertyHandlers();
	}
	
	protected Map<String, IPropertyHandler> getDefaultPropertyHandlers() {
		return INTERFACE_PROPERTY_HANDLERS;
	}
	
	protected EClass getEClass() {
		if (isInputParmaBlock() || isOutputParmaBlock()) {
			return BizPackage.Literals.PARAMETER;
		}
		return null;
	}
	
	@Override
	protected EStructuralFeature getTableFeature() {
		if (isInputParmaBlock()) {
			return BizPackage.Literals.BIZ_INTERFACE__INPUT_PARAMETERS;
		} else if (isOutputParmaBlock()) {
			return BizPackage.Literals.BIZ_INTERFACE__OUTPUT_PARAMETERS;
		}
		return null;
	}

	/** �жϵ�ǰ�Ƿ����������Block�� */
	protected boolean isInputParmaBlock() {
		return StringUtils.equals(parser.getCurrentBlockTag(), "�������");
	}
	
	/** �жϵ�ǰ�Ƿ����������Block�� */
	protected boolean isOutputParmaBlock() {
		return StringUtils.equals(parser.getCurrentBlockTag(), "�������");
	}
	
}