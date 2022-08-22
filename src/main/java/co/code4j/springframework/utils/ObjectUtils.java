package co.code4j.springframework.utils;

import java.util.function.Supplier;

/**
 * @author YuKaiFan
 * @date 2022/8/22
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public class ObjectUtils {

    public static <T> T notNull(T obj,String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }

        return obj;
    }

    public static <T> T notNull(T obj, Supplier<RuntimeException> exceptionSupplier) {
        if (exceptionSupplier == null) {
            throw new NullPointerException("异常提供者不能为空");
        }

        if (obj == null) {
            throw exceptionSupplier.get();
        }

        return obj;
    }

}
