package easybuy.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import easybuy.dao.CateDao;
import easybuy.dao.impl.CateDaoImpl;
import easybuy.model.Category;
import easybuy.model.MyCateItem;
import easybuy.model.MyPage;
import easybuy.service.CateService;

public class CateServiceImpl implements CateService {
    
	CateDao cateDao = new CateDaoImpl();
	
	@Override
	public List<MyCateItem> getAll() throws SQLException {
		List<MyCateItem> list = new ArrayList<>();
		
		try {
			List<Category> parents = cateDao.getCatesById(0); //先查询所有大类
			
			for (Category category : parents) {
				MyCateItem myCateItem = new MyCateItem();
				myCateItem.setCateid(category.getCategoryid());
				myCateItem.setCatename(category.getName());
				List<Category> childs = cateDao.getCatesById(category.getCategoryid());
				List<MyCateItem> childlist = new ArrayList<>();
				if(childs!=null&&childs.size()>0){
					for (Category child : childs) {
						MyCateItem childItem = new MyCateItem();
						childItem.setCateid(child.getCategoryid());
						childItem.setCatename(child.getName());
						childlist.add(childItem);
					}
				}
				myCateItem.setChild(childlist);
				list.add(myCateItem);
			}
			
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return list;
	}
	@Override
	public MyPage<Category> getPage(int pageNo, int pageSize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveCate(Category category) throws SQLException {
		// TODO Auto-generated method stub
		return cateDao.save(category);
	}

	@Override
	public int updateCate(Category category) throws SQLException {
		// TODO Auto-generated method stub
		return cateDao.update(category);
	}

	@Override
	public int deleteCate(int cid) throws SQLException {
		// TODO Auto-generated method stub
		return cateDao.delete(cid);
	}

	@Override
	public Category getCateById(int cid) throws SQLException {
		// TODO Auto-generated method stub
		return cateDao.get(cid);
	}

	@Override
	public List<Category> getCatesById(int parentId) throws SQLException {
		// TODO Auto-generated method stub
		return cateDao.getCatesById(parentId);
	}

}
