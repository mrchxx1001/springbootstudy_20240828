package com.project.springbootstudy.repository.user;

import com.project.springbootstudy.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    // 로그인 체크를 위한 메서드
    @Query("SELECT u FROM User u WHERE u.userId = :userId AND u.password = :password")
    User loginCheck(@Param("userId") String userId, @Param("password") String password);

    // 성별로 사용자 조회를 위한 메서드
    List<User> findByGender(String gender);

    // 이 인터페이스는 User 클래스(엔터티)를 사용하는 레포지토리입니다.
    // 레포지토리란 DB에 접근하기 위한 인터페이스입니다.
    // <User , String> 은 각각 사용할 엔터티 , PK의 타입을 의미입니다.
    //  예) userId 라는 String 을 @Id 로 지정했었습니다.

    //JpaRepository를 상속하고 있습니다.
    //이 안에는 다양한 기능을 제공합니다.
    //예) findById(); -> SELECT * FROM 테이블 WHERE ID = '' 쿼리 실행해줘
    //예) findAll(); -> SELECT * FROM 테이블 쿼리 실행해줘


}