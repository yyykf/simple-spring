package co.code4j.springframework.beans.factory.config;

/**
 * 单例对象管理接口
 *
 * @author YuKaiFan
 * @date 2022/8/22
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public interface SingletonBeanRegistry {

    /**
     * 根据名称获取单例对象
     *
     * @param beanName bean名称
     * @return 单例对象
     */
    Object getSingleton(String beanName);
}
