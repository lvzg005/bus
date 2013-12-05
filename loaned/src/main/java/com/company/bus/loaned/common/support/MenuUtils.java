package com.company.bus.loaned.common.support;

import java.util.ArrayList;
import java.util.List;

import com.company.bus.loaned.dbaccess.entity.SetFuncMenu;

/**
 * @Description 菜单生成工具
 * @author 
 * @date 2013-2-27
 * @version 1.0
 */
public class MenuUtils {
	
	
	/**
	 * 生成静态菜单节点
	 * 
	 * @param list
	 *            menu list
	 * @param pNode
	 *            menu root
	 * @return
	 */
	public static String generateStaticMenu(List<SetFuncMenu> list, SetFuncMenu pNode) {
		return "[" + generateChildren(list, pNode) + "]";
	}
	
	
	/**
	 * @Description
	 * @param list
	 * @param pNode
	 * @return
	 * @author 
	 */
	private static String generateChildren(List<SetFuncMenu> list, SetFuncMenu pNode) {
		StringBuilder result = new StringBuilder();
		List<SetFuncMenu> clist = searchChildList(list, pNode.getMenuId().longValue());
		if (clist.size() > 0) {// 不是叶子节点
			result.append("{id:").append(pNode.getMenuId()).append(", pid: ")
					.append(pNode.getPmenuId()).append(", url:'").append(
							pNode.getUrl()).append("', text:'").append(
							pNode.getMenuName()).append("', order:'").append(
							pNode.getDisplayOrder()).append("', ").append("children:");
			for (int i = 0; i < clist.size(); i++) {
				SetFuncMenu n = clist.get(i);
				if (clist.size() == 1) {
					result.append("[ ").append(generateChildren(list, n))
							.append(" ]");
				} else {
					if (i == clist.size() - 1) {// last row
						result.append(generateChildren(list, n))
								.append(" ]");
					} else if (i == 0) {// first row
						result.append(" [ ").append(generateChildren(list, n))
								.append(",");
					} else {// middle row
						result.append(generateChildren(list, n)).append(",");
					}
				}

			}
			result = result.append("}");
		} else {// 是叶子节点
			result.append("{").append("id:").append(pNode.getMenuId()).append(
					", pid: ").append(pNode.getPmenuId()).append(",").append(
					"text:'").append(pNode.getMenuName()).append("', order:'")
					.append(pNode.getDisplayOrder()).append("',")
					.append("leaf: true").append(", url:'").append(
							pNode.getUrl()).append("'}");
		}
		return result.toString();
	}
	
	private static List<SetFuncMenu> searchChildList(List<SetFuncMenu> list, long parentid) {
		List<SetFuncMenu> clist = new ArrayList<SetFuncMenu>();
		for (SetFuncMenu m : list) {
			SetFuncMenu n = m;
			if (n.getPmenuId().longValue() == parentid) {
				clist.add(n);
			}
		}
		return clist;
	}
}
