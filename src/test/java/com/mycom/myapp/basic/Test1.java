package com.mycom.myapp.basic;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // test 하는 메소드들의 실행 순서 지정
public class Test1 {
	
	@Test
	@Order(0)
	void test1() {
		
	}
}
