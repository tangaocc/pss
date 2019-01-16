package cn.itsource.pss.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.jpa.QueryHints;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import cn.itsource.pss.page.PageList;
import cn.itsource.pss.query.BaseQuery;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements BaseRepository<T, ID> {
	private EntityManager entityManager;

	public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public List findByJpql(String jpql, Object... values) {
		Query query = entityManager.createQuery(jpql);
		builderJpaParameter(query, values);
		return query.getResultList();
	}

	@Override
	public List findCacheByJpql(String cacheJpql, Object... values) {
		// 实现查询缓存核心代码setHint("org.hibernate.cacheable", true);
		// public static final java.lang.String HINT_CACHEABLE =
		// "org.hibernate.cacheable";
		Query query = entityManager.createQuery(cacheJpql).setHint(QueryHints.HINT_CACHEABLE, true);
		/* 设置参数 */
		builderJpaParameter(query, values);
		return query.getResultList();
	}
	
	@Override
	public PageList<T> findPageByQuery(BaseQuery baseQuery) {
		
		Query query = entityManager.createQuery(baseQuery.getCountJpql());
		/* 设置参数 */
		builderJpaParameter(query, baseQuery.getParams().toArray()); 
		//查询总共条数           
		Long count = (Long)query.getSingleResult();
		if(count == 0){// 如果查询出来的结果是0，那就没有必要继续去查询列表信息
			return new PageList<>();
		}
		//把前端页面传递过来的 当前页，和每页的条数，放到PageList进行一个自动的纠错
		PageList<T> pageList = new PageList<T>(baseQuery.getCurrentPage(), baseQuery.getPageSize(), count.intValue());
		//继续去查询列表
		query = entityManager.createQuery(baseQuery.getLimitJpql());
		/* 设置参数 */
		builderJpaParameter(query, baseQuery.getParams().toArray()); 
		//分页
		int begin = (pageList.getCurrentPage()-1)*pageList.getPageSize();// 开始的条数 
		int pageSize = pageList.getPageSize();//每页的条数
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		//开始查询
		List<T> data = query.getResultList();
		pageList.setData(data);
		return pageList;
	}

	// 设置查询参数
	private void builderJpaParameter(Query query, Object... values) {
	    if (values != null) {
		      // jpa索引从1开始
		      for (int i = 0; i < values.length; i++) {
		    	  query.setParameter(i + 1, values[i]);
		      }
	    }
	}

	

}
