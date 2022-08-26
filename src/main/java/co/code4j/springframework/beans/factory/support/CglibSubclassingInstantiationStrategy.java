package co.code4j.springframework.beans.factory.support;

import co.code4j.springframework.beans.BeansException;
import co.code4j.springframework.beans.factory.config.BeanDefinition;
import co.code4j.springframework.cglib.core.CustomNamingPolicy;
import java.lang.reflect.Constructor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import static co.code4j.springframework.utils.ObjectUtils.*;

/**
 * Cglib实例化策略
 *
 * @author YuKaiFan
 * @date 2022/8/26
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor<?> constructorToUse,
            Object[] args) throws BeansException {
        Class<?> beanClass = beanDefinition.getBeanClass();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanClass);
        enhancer.setCallback(NoOp.INSTANCE);
        // 自定义增强类名
        enhancer.setNamingPolicy(CustomNamingPolicy.INSTANCE);
        // TODO 2022/8/26 22:40 yukaifan Spring中创建增强的子类是为了什么
        // Class<?> enhancerClass = enhancer.createClass();

        return notNull(constructorToUse, () -> new BeansException("Constructor must not be null.")).getParameterCount()
                == 0 || args == null
                ? enhancer.create()
                : enhancer.create(constructorToUse.getParameterTypes(), args);

    }
}
