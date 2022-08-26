package co.code4j.springframework.beans.factory.support;

import co.code4j.springframework.beans.BeansException;
import co.code4j.springframework.beans.factory.config.BeanDefinition;
import java.lang.reflect.Constructor;

/**
 * 实现了 Bean 创建的功能，后续需要细化生命周期相关的功能，例如依赖注入
 *
 * @author YuKaiFan
 * @date 2022/8/22
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    /** 实例化Bean的策略，默认使用Cglib */
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean;

        try {
            // FIXME 无锁，可能会重复创建对象，破坏单例语义
            bean = this.createBeanInstance(beanName, beanDefinition, args);
        } catch (Exception e) {
            throw new BeansException(String.format("Failed to instantiate bean named [%s]", beanName), e);
        }

        // 保存单例
        this.addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 创建Bean实例
     *
     * @param beanName       bean名称
     * @param beanDefinition bean定义
     * @param args           构造函数入参，为 {@code NULL} 则使用无参构造
     * @return Bean实例对象
     */
    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        // 根据入参找一下构造函数
        Constructor<?> constructorToUse = null;
        if (args != null) {
            int initArgsNum = args.length;

            Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
            for (Constructor<?> declaredConstructor : declaredConstructors) {
                // TODO 2022/8/26 11:52 yukaifan 暂时只根据参数个数进行判断
                if (declaredConstructor.getParameterCount() == initArgsNum) {
                    // got it
                    constructorToUse = declaredConstructor;
                    break;
                }
            }

            if (constructorToUse == null) {
                // 没有找到合适的构造函数
                throw new BeansException(
                        String.format("Could not find the match constructor in %s, beanName: %s , args.length: %d",
                                beanClass, beanName, initArgsNum));
            }

        } else {
            // 使用默认的无参构造函数
            try {
                constructorToUse = beanClass.getDeclaredConstructor();
            } catch (NoSuchMethodException e) {
                throw new BeansException(
                        String.format("Could not find no args constructor in %s, beanName: %s", beanClass, beanName),
                        e);
            }

        }

        return getInstantiationStrategy().instantiate(beanName, beanDefinition, constructorToUse, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public AbstractAutowireCapableBeanFactory setInstantiationStrategy(
            InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
        return this;
    }
}
