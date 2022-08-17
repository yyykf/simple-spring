package co.code4j.springframework.factory.config;

/**
 * Bean 定义
 *
 * @author YuKaiFan
 * @date 2022/8/17
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public class BeanDefinition {

    /** 对应的 Bean  TODO 2022/8/17 16:13 yukaifan 后续会替换为目标类，而不是实际对象 */
    private final Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
