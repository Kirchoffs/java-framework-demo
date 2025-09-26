package org.syh.demo.thirdparty.experiment.spring.customized.beanutils;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

public class CustomizedBeanUtilsTest {
    public static void main(String[] args) {
        User u1 = new User(1L, "benjamin", 1);
        User u2 = new User(2L, "oliver", 2);
        List<User> userList = Arrays.asList(u1, u2);

        UserVO vo = CustomizedBeanUtils.convertTo(u1, UserVO::new, (s, t) -> {
            t.setStatus(s.getStatus() == 1 ? "Enabled" : "Disabled");
        });
        System.out.println(vo);

        List<UserVO> voListSimple = CustomizedBeanUtils.convertListTo(userList, UserVO::new);
        System.out.println(voListSimple);

        List<UserVO> voListFull = CustomizedBeanUtils.convertListTo(userList, UserVO::new, (s, t) -> {
            t.setStatus(s.getStatus() == 1 ? "Enabled" : "Disabled");
        });
        System.out.println(voListFull);
    }

    @Data
    @AllArgsConstructor
    private static class User {
        private Long id;
        private String username;
        private Integer status;
    }

    @Data
    private static class UserVO {
        private Long id;
        private String username;
        private String status;
    }
}
