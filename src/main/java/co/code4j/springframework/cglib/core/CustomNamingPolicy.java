package co.code4j.springframework.cglib.core;

import net.sf.cglib.core.DefaultNamingPolicy;

/**
 * 自定义CGLIB类名
 *
 * @author YuKaiFan
 * @date 2022/8/26
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public class CustomNamingPolicy extends DefaultNamingPolicy {

    /** 单例对象 */
    public static final CustomNamingPolicy INSTANCE = new CustomNamingPolicy();

    @Override
    protected String getTag() {
        return "ByCode4jCGLIB";
    }
}
