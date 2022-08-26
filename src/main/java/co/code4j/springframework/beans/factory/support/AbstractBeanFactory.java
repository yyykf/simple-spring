package co.code4j.springframework.beans.factory.support;

import co.code4j.springframework.beans.BeansException;
import co.code4j.springframework.beans.factory.BeanFactory;
import co.code4j.springframework.beans.factory.config.BeanDefinition;

import static co.code4j.springframework.utils.ObjectUtils.*;

/**
 * Bean 容器的抽象基类，使用模板模式定义获取 Bean 的基本步骤
 *
 * @author YuKaiFan
 * @date 2022/8/22
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) throws BeansException {
        return this.doGetBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return this.doGetBean(beanName, args);
    }

    /**
     * 执行获取Bean
     * @param beanName bean名称
     * @param args 构造函数入参
     * @param <T> TODO 2022/8/26 11:23 yukaifan 为什么需要泛型
     * @return
     */
    protected <T> T doGetBean(String beanName, Object[] args) {
        // 是否存在对应的单例对象?
        Object singleton = this.getSingleton(notNull(beanName, "BeanName不可为空"));
        if (singleton != null) {
            return (T) singleton;
        }

        // 获取 Bean 定义
        BeanDefinition beanDefinition = this.getBeanDefinition(beanName);
        // 创建单例对象 todo 保存单例对象应该放在 createBean 中实现，因为需要确保线程安全?
        singleton = this.createBean(beanName, beanDefinition, args);
        return (T) singleton;
    }

    /**
     * 根据名称获取 Bean 定义
     * <p>
     * TODO 2022/8/22 18:01 yukaifan 后续应该迁移到 BeanDefinitionRegistry 中
     *
     * @param beanName bean名称
     * @return Bean 定义
     *
     * @throws BeansException 当不存在对应 Bean 定义时抛出
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 创建 Bean
     *
     * @param beanName       bean名称
     * @param beanDefinition bean定义
     * @param args           构造函数入参
     * @return Bean
     *
     * @throws BeansException 创建 Bean 失败时抛出
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args)
            throws BeansException;
}
