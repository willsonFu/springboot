package com.zwy.returns;

public enum ReturnCode
{
	C101(101, "没有登录或会话超时"),
	C102(102, "没有权限"),
	C103(103, "访问超时 "),
	C104(104, "请指定公司 "),
	
	C200(200, "success"), 
	C201(201, "数据不存在"), 
	C202(202, "数据已存在 "),
	C203(203, "数据已禁用 "),
	C204(204, "数据已使用 "),
	
	C210(210, "用户名或密码错误，请重试"),
	C211(211, "该手机号码已注册过哦！"),
	C236(236, "用户不存在"),
	
	C400(400, "客户端参数错误"),
	C401(401, "参数为空或不存在"),
	C402(402, "参数非法或格式有误 "), 


	C500(500, "服务器错误"),
	;

	private final int code;
	private String message;

	private ReturnCode(int code, String message)
	{
		this.code = code;
		this.message = message;
	}

	public int getCode()
	{
		return code;
	}

	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
}
