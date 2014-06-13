/**
 * Դ�������ƣ�ModifyColumnTypeComposite.java
 * ��������Ȩ���������ӹɷ����޹�˾ ��Ȩ����
 * ϵͳ���ƣ�JRES Studio
 * ģ�����ƣ�com.hundsun.ares.studio.jres.database.ui
 * ����˵����$desc
 * ����ĵ���
 * ���ߣ�
 */
package com.hundsun.ares.studio.jres.clearinghouse.composite;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseRefType;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataItemEditingSupportDecorator;
import com.hundsun.ares.studio.jres.model.chouse.CTCPMDetail;
import com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnPrimaryKeyModifycation;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.ui.editor.editingsupport.BooleanEditingSupport;
import com.hundsun.ares.studio.ui.editor.editingsupport.JresTextEditingSupportWithContentAssist;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.CheckBoxColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

/**
 * @author yanwj06282
 *
 */
public class ModifyColumnPrimaryKeyComposite extends ModifyActionColumnComposite<CTCPMDetail>{

	private EList<CTCPMDetail> input;
	
	public ModifyColumnPrimaryKeyComposite(Composite parent, TableResourceData tableData, IARESResource resource,
			Modification action) {
		super(parent, tableData, resource, action);
	}
	
	@Override
	protected EReference getEReference() {
		return ChousePackage.Literals.CHANGE_TABLE_COLUMN_PRIMARY_KEY_MODIFYCATION__DETAILS;
	}

	@Override
	protected CTCPMDetail creatBlankItem() {
		CTCPMDetail detail = ChouseFactory.eINSTANCE.createCTCPMDetail();
		return detail;
	}

	@Override
	protected EList<CTCPMDetail> getActionItems(Modification modification) {
		return input;
	}

	@Override
	protected void creatColumnComposite(TreeViewer treeViewer,
			IARESResource resource) {
		
		{
			EAttribute attribute = ChousePackage.Literals.MODIFY_DETAIL__MARK;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("���");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
			
			tvColumn.getColumn().setMoveable(true);
		}
		
		// �ֶ���
		{
			EAttribute attribute = ChousePackage.Literals.MODIFY_DETAIL__NAME;
			
			TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(200);
			tvColumn.getColumn().setText("�ֶ���");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
			
			ColumnProposalProvider proposalProvider = new ColumnProposalProvider(IDatabaseRefType.TableField);
			JresTextEditingSupportWithContentAssist es = new JresTextEditingSupportWithContentAssist(
					treeViewer,
					attribute, 
					proposalProvider);
			es.setDecorator(new MetadataItemEditingSupportDecorator(attribute,resource));
			tvColumn.setEditingSupport(es);
		}
		
		// �Ƿ�����
		{
			EAttribute attribute = ChousePackage.Literals.CTCPM_DETAIL__PRIMARK_KEY;
			
			TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("�Ƿ�����");
			
			CheckBoxColumnLabelProvider provider = new CheckBoxColumnLabelProvider(attribute , resource);
			tvColumn.setLabelProvider(provider);
			
			tvColumn.setEditingSupport(new BooleanEditingSupport(treeViewer, attribute));
		}
		
	}

	@Override
	protected void initAction(Modification modification) {
		if(modification != null && modification instanceof ChangeTableColumnPrimaryKeyModifycation){
			action = modification;
			input = ((ChangeTableColumnPrimaryKeyModifycation)action).getDetails();
		}else{
			action = ChouseFactory.eINSTANCE.createChangeTableColumnPrimaryKeyModifycation();
			input = ((ChangeTableColumnPrimaryKeyModifycation)action).getDetails();
			if (tableData == null) {
				try {
					tableData = resource.getInfo(TableResourceData.class);
				} catch (ARESModelException e) {
					e.printStackTrace();
				}
			}
			for (TableColumn column : tableData.getColumns()){
				CTCPMDetail de = ChouseFactory.eINSTANCE.createCTCPMDetail();
				de.setName(column.getName());
				if (column.isPrimaryKey()) {
					de.setPrimarkKey(column.isPrimaryKey());
					input.add(de);
				}
			}
		}
	}
	
}