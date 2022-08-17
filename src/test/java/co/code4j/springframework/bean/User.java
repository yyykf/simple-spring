package co.code4j.springframework.bean;

/**
 * @author YuKaiFan
 * @date 2022/8/17
 * @blog <a href="https://code4j.co">https://code4j.co</a>
 */
public class User {

    private final int age = 18;
    private final String name = "Code4j";

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
