package cn.sst.scd.optional;

import java.util.Optional;

/**
 * @author shengtengsun
 * @Description Optional测试
 * @Date 2020/1/15 2:26 下午
 * @Version 1.1.0
 **/
public class OptionalTest {
    public static void main(String[] args) {
        User user = new User("sunshengteng", new Address("北京市昌平区"));
        User user1 = new User(null, new Address("北京市昌平区"));
        User user2 = new User("sunshengteng", null);
        // Map
        /*System.out.println(Optional.ofNullable(user).map(User::getName).orElseGet(() -> {
            return "默认名称";
        }));
        System.out.println(Optional.ofNullable(user).map(User::getAddress).get());*/
        // FlatMap
        System.out.println(Optional.ofNullable(user1).flatMap(User::getOptName).orElseGet(() -> {
            return "默认姓名";
        }));
        System.out.println(Optional.ofNullable(user2).flatMap(User::getOptAddress).flatMap(Address::getOptName).orElseGet(() -> {
            return "默认地址";
        }));

    }

    public static class User {
        private String name;
        private Address address;

        public Optional<Address> getOptAddress() {
            return Optional.ofNullable(address);
        }

        public Optional<String> getOptName() {
            return Optional.ofNullable(name);
        }

        public User(String name, Address address) {
            this.name = name;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }
    }

    public static class Address {
        private String name;

        public Address(String name) {
            this.name = name;
        }

        public Optional<String> getOptName() {
            return Optional.ofNullable(name);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
