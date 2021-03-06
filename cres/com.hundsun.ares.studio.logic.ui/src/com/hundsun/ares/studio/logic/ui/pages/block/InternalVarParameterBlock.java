/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.ui.pages.block;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TreeViewer;

import com.hundsun.ares.studio.atom.AtomPackage;
import com.hundsun.ares.studio.biz.ui.action.ParameterPasteAction;
import com.hundsun.ares.studio.biz.ui.block.ParameterViewerBlock;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.logic.ui.InternalParameterPasteAction;
import com.hundsun.ares.studio.ui.ColumnViewerHoverToolTip;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerPasteAction;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

/**
 * @author qinyuan
 *
 */
public class InternalVarParameterBlock extends ParameterViewerBlock{

	/**
	 * @param reference
	 * @param editingDomain
	 * @param resource
	 * @param problemPool
	 */
	public InternalVarParameterBlock(EditingDomain editingDomain, IARESResource resource,
			IProblemPool problemPool) {
		super(AtomPackage.Literals.ATOM_FUNCTION__INTERNAL_VARIABLES, editingDomain, resource, problemPool);
	}
	@Override
	protected void addToolTipSupport(TreeViewer viewer) {
		ColumnViewerHoverToolTip.enableFor(viewer);
	}
	
	protected EClass getParameterEClass(){
		return AtomPackage.Literals.INTERNAL_PARAM;
	}	
	
	protected ColumnViewerPasteAction createPasteAction() {
		return new InternalParameterPasteAction(getColumnViewer(), this.editingDomain, null, this.reference);
	}
}
