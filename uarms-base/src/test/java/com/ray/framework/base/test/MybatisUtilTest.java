package com.ray.framework.base.test;

import org.testng.annotations.Test;

import com.ray.framework.util.MybatisUtil;

import org.testng.Assert;


public class MybatisUtilTest {
	
	
	/**
	 * 测试验证邮箱的方法是否正确
	 */
	@Test
	public void checkEmailTest(){
		
		boolean rslt = MybatisUtil.checkEmail("test@test.com");
		Assert.assertTrue(rslt);
		rslt = MybatisUtil.checkEmail("tes@t@test.com");
		Assert.assertFalse(rslt);
	}
	

}
