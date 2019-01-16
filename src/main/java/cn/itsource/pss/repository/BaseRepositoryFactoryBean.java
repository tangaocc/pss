package cn.itsource.pss.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

/***
 * 持久层基类接口FactoryBean
 * 
 *
 */
public class BaseRepositoryFactoryBean<DAO extends JpaRepository<T, ID>, T, ID extends Serializable>
		extends JpaRepositoryFactoryBean<DAO, T, ID> {

	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new BaseRepositoryFactory<T, ID>(entityManager);
	}
	/*FactoryBean： 专门创建比较复杂的bean对象*/

	
	
	// 创建一个内部类，该类不用在外部访问
	private static class BaseRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

		private final EntityManager entityManager;

		public BaseRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
			this.entityManager = entityManager;
		}

		// 设置具体的实现类是BaseRepositoryImpl， 把默认的SimpleJpaRepository进行覆盖
		@Override
		protected Object getTargetRepository(RepositoryInformation information) {
			return new BaseRepositoryImpl<T, I>((Class<T>) information.getDomainType(), entityManager);
		}

		// 设置具体的实现类的class
		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			return BaseRepositoryImpl.class;
		}
	}
}