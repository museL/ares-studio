/**
 * 源程序名称：ITableSpaceScriptWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.script.api.database;

import com.hundsun.ares.studio.jres.script.api.model.IScriptModelWrap;

/**
 * 数据库表空间封装对象
 * 
 * @author yanwj06282
 *
 */
public interface ITableSpaceScriptWrap extends IScriptModelWrap{

	/**
	 * 表空间条目
	 * 
	 * @return
	 */
	public ITableSpaceItemScriptWrap[] getSpace();
	
}
