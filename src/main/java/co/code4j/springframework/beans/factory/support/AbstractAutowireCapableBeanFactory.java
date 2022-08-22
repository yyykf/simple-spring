package co.code4j.springframework.beans.factory.support;

import co.code4j.springframework.beans.BeansException;
import co.code4j.springframework.beans.factory.config.BeanDefinition;

/**
 * 实现了 Bean 创建的功能，后续需要细化生命周期相关的功能，例如依赖注入
 *
 * @author YuKaiFan
 * @date 2022/8/22
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object bean;

        try {
            // fixme 无锁，可能会重复创建对象，破坏单例语义
            // TODO 2022/8/22 17:53 yukaifan 后续不能直接使用默认构造函数，需要根据定义判断使用的构造函数
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException(String.format("Failed to instantiate bean named [%s]", beanName), e);
        }

        // 保存单例
        this.addSingleton(beanName, bean);
        return bean;
    }
}
