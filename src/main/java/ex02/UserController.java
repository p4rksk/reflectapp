package ex02;

import ex02.RequestMapping;

public class UserController {

    @RequestMapping(uri = "/login")
    // /login
    public void login(){
        System.out.println("로그인");
    }

    @RequestMapping(uri = "/join")
    // /join
    public void join(){
        System.out.println("회원가입 호출됨");
    }

    @RequestMapping(uri = "/userinfo")
    public void userinfo(){
        System.out.println("유저정보 보기");
    }

    @RequestMapping(uri = "/updatePassword")
    public void updatePassword(){
        System.out.println("패스 워드를 수정하세요");
    }
}
