package co.code4j.springframework.factory;

import static java.util.Objects.requireNonNull;

import co.code4j.springframework.factory.config.BeanDefinition;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean 工厂
 *
 * @author YuKaiFan
 * @date 2022/8/17
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public class BeanFactory {

    /** BeanName -> BeanDefinition，存放 Bean 定义 */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 根据名称获取Bean
     *
     * @param beanName bean名称
     * @return Bean
     */
    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(requireNonNull(beanName, "BeanName不可为空"));
        if (beanDefinition == null) {
            return null;
        }

        return beanDefinition.getBean();
    }

    /**
     * 往容器中注册Bean定义
     *
     * @param beanName       bean名称
     * @param beanDefinition bean定义
     */
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(requireNonNull(beanName, "BeanName不可为空"),
                requireNonNull(beanDefinition, "Bean定义不可为空"));
    }

}
