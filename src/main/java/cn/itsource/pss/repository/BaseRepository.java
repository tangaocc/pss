package cn.itsource.pss.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import cn.itsource.pss.page.PageList;
import cn.itsource.pss.query.BaseQuery;

//@NoRepositoryBean  告诉spring不要用SimpleJpaRepository这个实现类去实现我们BaseRepository这个接口
@NoRepositoryBean 
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
	 /**
	   * 通过jpql语句去查询列表信息
	   * @param jpql
	   * @param values
	   * @return
	   */
	  List findByJpql(String jpql, Object... values);
	  
	  /**
	   * 通过jpql语句去查询缓存中去查询数据
	   * @param cacheJpql
	   * @param values
	   * @return
	   */
	  List findCacheByJpql(String cacheJpql, Object... values);
	  
	  /**
	   * 分页查询
	   * @param baseQuery
	   * @return
	   */
	  PageList<T> findPageByQuery(BaseQuery baseQuery);

	
}
