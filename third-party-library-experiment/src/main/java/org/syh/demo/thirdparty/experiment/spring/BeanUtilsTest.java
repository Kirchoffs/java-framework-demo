package org.syh.demo.thirdparty.experiment.spring;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;

import lombok.Data;

public class BeanUtilsTest {
    public static void main(String[] args) {
        copyPropertiesTest();
    }

    private static void copyPropertiesTest() {
        UserPO userPO = new UserPO();
        userPO.setId(42L);
        userPO.setUsername("benjamin");
        userPO.setPassword("hashed_password");
        userPO.setAge(28);
        userPO.setTags(Arrays.asList("Java", "Spring", "AI"));

        UserDTO userDTO = new UserDTO();

        BeanUtils.copyProperties(userPO, userDTO);

        System.out.println("---- Copy Operation Finished ----");
        System.out.println("DTO Username: " + userDTO.getUsername());
        System.out.println("DTO Age: " + userDTO.getAge());          
        System.out.println("DTO Tags: " + userDTO.getTags());        
        
        boolean containsPassword = userDTO.toString().contains("hashed_password");
        System.out.println("Does DTO contain sensitive password? " + containsPassword); 
        
        userPO.getTags().set(1, "Python"); 
        
        System.out.println();
        System.out.println("---- Shallow Copy Demo (After modifying PO list) ----");
        System.out.println("PO Tags: " + userPO.getTags());
        System.out.println("DTO Tags: " + userDTO.getTags()); 
        System.out.println("Result: The second tag in DTO also changed to 'Python' because they share the same memory address.");
    }

    @Data
    public static class UserPO {
        private Long id;
        private String username;
        private String password;
        private Integer age;
        private List<String> tags;
    }

    @Data
    public static class UserDTO {
        private String username;
        private Integer age;
        private List<String> tags;
    }
}
