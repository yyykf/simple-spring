package co.code4j.springframework.beans.factory.support;

import co.code4j.springframework.beans.BeansException;
import co.code4j.springframework.beans.factory.config.BeanDefinition;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static co.code4j.springframework.utils.ObjectUtils.*;

/**
 * JDK实例化策略
 *
 * @author YuKaiFan
 * @date 2022/8/26
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor<?> constructorToUse,
            Object[] args) throws BeansException {
        // 构造函数不会为空的
        notNull(constructorToUse, () -> new BeansException("Constructor must not be null."));

        try {
            if (!constructorToUse.isAccessible()) {
                constructorToUse.setAccessible(true);
            }

            return constructorToUse.newInstance(args);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new BeansException(String.format("Failed to instantiate bean named [%s]", beanName), e);
        }
    }
}
