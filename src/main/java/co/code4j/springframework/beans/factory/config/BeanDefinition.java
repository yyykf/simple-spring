package co.code4j.springframework.beans.factory.config;

/**
 * Bean 定义
 *
 * @author YuKaiFan
 * @date 2022/8/17
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public class BeanDefinition {

    /** 对应的 Bean 类 */
    private final Class<?> beanClass;

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }
}
