package com.wumugulu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wumugulu.entity.Book;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="Book", tags="Book Managment Apis")//书目管理api集,这地方貌似用中文就会有问题，还没研究出来。。。
@Controller
@RequestMapping("/books")
public class BookController {

	@ApiOperation(value="测试上传文的api方法", notes="需要提交name，size并选择要文件上传至server，如果成功的话，会返回status：0")
	@ApiImplicitParams({
		@ApiImplicitParam(name="authToken", value="Header中的验证码", paramType="header", dataType="string"),
		@ApiImplicitParam(name="id", value="Book的id值", paramType="path", dataType="int"),
		@ApiImplicitParam(name="name", value="表单提交的name", paramType="query", dataType="string"),
		@ApiImplicitParam(name="size", value="表单提交的size", paramType="form", dataType="int"),
		@ApiImplicitParam(name="headimg", value="选择要上传的文件", paramType="form", dataType="file")
	})
	@PostMapping(value={"/upload1/{id}"})
	@ResponseBody
	public String upload1(@RequestHeader("authToken") String token, @PathVariable("id")Integer bookId, 
						String name,Integer size, @RequestParam("headimg")MultipartFile file){
		System.out.println("token = " + token);
		System.out.println("bookId= " + bookId);
		System.out.println("name  = " + name);
		System.out.println("size  = " + size);
		System.out.println("file.getOriginalFilename()=" + file.getOriginalFilename());
		return "upload1() success: " + file.getOriginalFilename();
	}
	
	@PostMapping(value={"/upload2"}, consumes="multipart/form-data")
	@ResponseBody
	public String upload2(@RequestParam("file") MultipartFile[] file){
		return "upload2() success: " + file[0].getOriginalFilename();
	}
	
	// 新增Book-by json
	@PostMapping(value="", consumes={"application/json"})
	@ResponseBody
	public String addByJson(@RequestBody Book book){
		System.out.println("binded : " + book.toString());
		return "addByJson() success";
	}

	// 新增Book-by form 1
	@PostMapping(value="/addbyform1")
	@ResponseBody
	public String addByForm1(Book book){
		System.out.println("add book=" + book.toString());
		return "addByForm1() success";
	}

	// 新增Book by form 2
	@PostMapping(value="/addbyform2")
	@ResponseBody
	public String addByForm2(Integer id, String name, String address, Integer age){
		System.out.println("id =" + id);
		System.out.println("name =" + name);
		System.out.println("address =" + address);
		System.out.println("age =" + age);
		return "addByForm2() success";
	}

	@GetMapping(value={"/getAll"})
	@ResponseBody
	public String getAll(){
		return "getAll() return success";
	}
	
}
