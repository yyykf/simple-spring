package co.code4j.springframework.beans.factory.support;

import static java.util.Objects.requireNonNull;

import co.code4j.springframework.beans.BeansException;
import co.code4j.springframework.beans.factory.config.BeanDefinition;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static co.code4j.springframework.utils.ObjectUtils.*;

/**
 * Bean 容器的默认实现
 *
 * @author YuKaiFan
 * @date 2022/8/22
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    /** BeanName -> BeanDefinition，存放 Bean 定义 */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException{
        return notNull(this.beanDefinitionMap.get(notNull(beanName, "BeanName不可为空")),
                () -> new BeansException(String.format("No bean named [%s] found.", beanName)));
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(notNull(beanName, "BeanName不可为空"), notNull(beanDefinition, "Bean定义不可为空"));
    }
}
