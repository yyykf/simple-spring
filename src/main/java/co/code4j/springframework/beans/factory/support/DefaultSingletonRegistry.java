package co.code4j.springframework.beans.factory.support;

import static co.code4j.springframework.utils.ObjectUtils.*;

import co.code4j.springframework.beans.factory.config.SingletonRegistry;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例对象管理默认实现类
 *
 * @author YuKaiFan
 * @date 2022/8/22
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public class DefaultSingletonRegistry implements SingletonRegistry {

    /** BeanName -> Singleton，存在单例对象 */
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return this.singletonObjects.get(notNull(beanName, "BeanName不可为空"));
    }
}
