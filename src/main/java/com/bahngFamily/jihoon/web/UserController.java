package com.bahngFamily.jihoon.web;

import com.bahngFamily.jihoon.config.security.JwtTokenProvider;
import com.bahngFamily.jihoon.domain.user.UserImpl;
import com.bahngFamily.jihoon.domain.user.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

  @GetMapping("/hello")
  public String hello(){
    return "Hello World!!";
  }
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;

  // 회원가입
  @PostMapping("/join")
  public Long join(@RequestBody Map<String, String> user) {
    return userRepository.save(UserImpl.builder()
        .email(user.get("email"))
        .password(passwordEncoder.encode(user.get("password")))
        .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
        .build()).getId();
  }

  // 로그인
  @PostMapping("/loginCustom")
  public String login(@RequestBody Map<String, String> user) {
    UserImpl member = userRepository.findByEmail(user.get("email"))
        .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
    if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
      throw new IllegalArgumentException("잘못된 비밀번호입니다.");
    }
    //json으로 토큰만 리턴하고 브라우저에서 header 쿠키에 저장하고 거기서 리다이렉션?
    //그럼 요청 보낼 때 마다 알아서 헤더에 토큰값이 박혀있는??
    //근데 브라우저에도 시크릿키가 있어야하는건 뭐였지
    return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
  }

  @GetMapping("/admin/qnaList")
  public List list(){
    //권한체크
    Authentication user = SecurityContextHolder.getContext().getAuthentication();

    if(user.getAuthorities() != null){
      String a="qna리스트들";
    }else{
      String a = "관리자가 아닙니다";
    }

    return null;
  }
}
