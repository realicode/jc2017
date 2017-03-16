package com.realaicy.prod.jc.realglobal;

import com.realaicy.prod.jc.modules.pj.model.ApplianceVO;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by realaicy on 2017/3/6.
 * xxx
 */
public class ValidatorTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void checkRegisUserVO() {

        ApplianceVO applianceVO = new ApplianceVO();
        Set<ConstraintViolation<ApplianceVO>> constraintViolationSet = validator.validate(applianceVO);

//        applianceVO.setName("");
//        constraintViolationSet = validator.validate(applianceVO);
//        System.out.println("1" + constraintViolationSet.toString());
//
//        applianceVO.setName("111");
//        constraintViolationSet = validator.validate(applianceVO);
//        System.out.println("2" + constraintViolationSet.toString());

        applianceVO.setName("a11111");
        constraintViolationSet = validator.validate(applianceVO);
        System.out.println("3" + constraintViolationSet.toString());
        applianceVO.setName("a=11111");
        constraintViolationSet = validator.validate(applianceVO);
        System.out.println("4" + constraintViolationSet.toString());

//        UserRegisVO userRegisVO = new UserRegisVO();
//        Set<ConstraintViolation<UserRegisVO>> constraintViolationSet =   validator.validate(userRegisVO);
//        userRegisVO.setUsername("a1234");
//        constraintViolationSet =   validator.validate(userRegisVO);
//        System.out.println();

    }

}