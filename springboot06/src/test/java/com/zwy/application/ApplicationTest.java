package com.zwy.application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.zwy.application.controllers.AccountController;
import com.zwy.application.controllers.UserController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ApplicationTest {

	@Autowired
    private AccountController accountController;
	@Autowired
	private UserController userController;
	private MockMvc mvc;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(accountController,userController).build();
	}

	@Test
	public void testAccountController() throws Exception {
		RequestBuilder request = null;
		//1、查询account列表
//		request = get("/account/");
//		mvc.perform(request).andDo(print()).andExpect(status().isOk()).andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"aaa\",\"money\":100.0},{\"id\":2,\"name\":\"bbb\",\"money\":1000.0},{\"id\":3,\"name\":\"ccc\",\"money\":1000.0},{\"id\":4,\"name\":\"刘思思\",\"money\":1000.25}]")));
		
//		request = get("/account/1");
//		mvc.perform(request).andDo(print()).andExpect(status().isOk()).andExpect(content().string(equalTo("{\"id\":1,\"name\":\"aaa\",\"money\":100.0}")));
//		
//		request = put("/account/1").param("name", "aaa").param("money", "100");
//		mvc.perform(request).andDo(print()).andExpect(status().isOk()).andExpect(content().string(equalTo("{\"id\":1,\"name\":\"aaa\",\"money\":100.0}")));
//		
//		request = post("/account").param("name", "账无忧").param("money", "100000");
//		mvc.perform(request).andDo(print()).andExpect(status().isOk()).andExpect(content().string(equalTo("{\"id\":5,\"name\":\"账无忧\",\"money\":100000.0}")));
	}

	@Test
	public void testUserController() throws Exception {
		// 测试UserController
		RequestBuilder request = null;

		// 1、get查一下user列表，应该为空
		request = get("/users/");
		mvc.perform(request).andDo(print()).andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));

		// 2、post提交一个user
		request = post("/users/").param("id", "1").param("name", "测试大师").param("age", "20");
		mvc.perform(request).andDo(print()).andExpect(content().string(equalTo("success")));

		// 3、get获取user列表，应该有刚才插入的数据
		request = get("/users/");
		mvc.perform(request).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"测试大师\",\"age\":20}]")));

		// 4、put修改id为1的user
		request = put("/users/1").param("name", "测试终极大师").param("age", "30");
		mvc.perform(request).andDo(print()).andExpect(content().string(equalTo("success")));

		// 5、get一个id为1的user
		request = get("/users/1");
		mvc.perform(request).andDo(print())
				.andExpect(content().string(equalTo("{\"id\":1,\"name\":\"测试终极大师\",\"age\":30}")));

		// 6、del删除id为1的user
		request = delete("/users/1");
		mvc.perform(request).andDo(print()).andExpect(content().string(equalTo("success")));

		// 7、get查一下user列表，应该为空
		request = get("/users/");
		mvc.perform(request).andDo(print()).andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));

	}
}
