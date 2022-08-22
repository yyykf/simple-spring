package co.code4j.springframework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import co.code4j.springframework.bean.User;
import co.code4j.springframework.beans.factory.BeanFactory;
import co.code4j.springframework.beans.factory.config.BeanDefinition;
import co.code4j.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author YuKaiFan
 * @date 2022/8/17
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public class MainTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainTest.class);

    @Test
    void testBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("user", new BeanDefinition(User.class));

        Object user = beanFactory.getBean("user");
        assertNotNull(user);
        assertTrue(user instanceof User);

        Object singleton = beanFactory.getBean("user");
        assertEquals(user, singleton);

        LOGGER.info("{}", user);
    }

}
