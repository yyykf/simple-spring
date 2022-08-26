package co.code4j.springframework.beans.factory.support;

import co.code4j.springframework.beans.BeansException;
import co.code4j.springframework.beans.factory.config.BeanDefinition;
import java.lang.reflect.Constructor;

/**
 * 实例化接口
 *
 * @author YuKaiFan
 * @date 2022/8/26
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public interface InstantiationStrategy {

    /**
     * 实例化Bean
     *
     * @param beanName         bean名称
     * @param beanDefinition   bean定义
     * @param constructorToUse 用于实例化的构造函数，不为空
     * @param args             构造函数入参，可空
     * @return 实例化后的Bean
     *
     * @throws BeansException 实例化失败时抛出
     */
    Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor<?> constructorToUse, Object[] args)
            throws BeansException;
}
