package co.code4j.springframework.beans.factory;

import co.code4j.springframework.beans.BeansException;

/**
 * Bean 工厂
 *
 * @author YuKaiFan
 * @date 2022/8/17
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public interface BeanFactory {

    /**
     * 根据名称获取Bean
     *
     * @param beanName bean名称
     * @return Bean
     *
     * @throws BeansException 获取失败时抛出
     */
    Object getBean(String beanName) throws BeansException;

    /**
     * 根据名称获取Bean
     *
     * @param beanName bean名称
     * @param args     构造函数入参
     * @return Bean
     *
     * @throws BeansException 获取失败时抛出
     */
    Object getBean(String beanName, Object... args) throws BeansException;
}
