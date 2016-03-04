package pengyi.repository.generic;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Author: pengyi
 * Date: 15-12-30
 * Time: 下午3:42
 */
public interface IHibernateGenericRepository<T, ID extends Serializable> {

    T loadById(ID id);

    T getById(ID id);

//    List<T> findByExample(T exampleInstance, String... excludeProperty);
//
//    T findByProperties(Map<String, Object> propertyMap);
//
//    List<T> findAllByProperties(Map<String, Object> propertyMap);

    void save(T entity);

    void saveOrUpdate(T entity);

    void update(T entity);

    void delete(T entity);

    void flush();

    void clear();

    void evict(Object obj);

    void refresh(Object obj);

    //TODO 重构没有传入fetchModeMap的方法
    Pagination<T> pagination(int page, int pageSize, Criterion[] criteria, Order[] orders);

    Pagination<T> pagination(int page, int pageSize, Criterion[] criteria, Order[] orders,
                             Map<String, FetchMode> fetchModeMap);

    Pagination<T> pagination(int page, int pageSize, Criterion[] criteria, Map<String, String> aliasMap, Order[] orders,
                             Map<String, FetchMode> fetchModeMap);

    Pagination<T> pagination(int page, int pageSize, Criterion[] criteria, Map<String, String> aliasMap,
                             Order[] orders, Map<String, FetchMode> fetchModeMap, ProjectionList projectionList);

    List<T> findAll();

    List<T> list(Criterion[] criteria, Order[] orders);

    List<T> list(Criterion[] criteria, Order[] orders, ProjectionList projectionList,
                 Map<String, FetchMode> fetchModeMap, Map<String, String> alias);
}
