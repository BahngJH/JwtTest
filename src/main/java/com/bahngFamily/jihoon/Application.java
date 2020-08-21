package com.bahngFamily.jihoon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//아래 어노테이션으로 인해 스프링부트의 자동설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정됩니다.
//특히나 @SpringBootApplication가 있는 위치부터 설정을 읽어가기 때문에 이 클래스는 항상 프로젝트 최상단에 위치해야함
//@EnableJpaAuditing
@SpringBootApplication
public class Application {
  public static void main(String[] args){
    //SpringApplication.run로 인해 내장 WAS를 실행함.
    //내장 WAS란 별도로 외부에 WAS를 두지 않고 애플리케이션을 실행할 때 내부에서 WAS를 실행 하는 것
    //이렇게 되면 항상 서버에 톰캣을 설치할 필요가 없게 되고, 스프링 부트로 만들어진 Jar 파일로 실행하면 됩니다.
    SpringApplication.run(Application.class, args);
  }
}
