package co.code4j.springframework.beans.factory.support;

import co.code4j.springframework.beans.factory.config.BeanDefinition;

/**
 * Bean定义管理接口
 *
 * @author YuKaiFan
 * @date 2022/8/22
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public interface BeanDefinitionRegistry {

    /**
     * 往容器中注册Bean定义
     *
     * @param beanName       bean名称
     * @param beanDefinition bean定义
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
