package com.example.save1e9.repository;

import com.example.save1e9.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    // native query 연습용
    // : 붙이면 파라미터
    @Modifying
    @Transactional
    @Query(value = "insert into user(id,name,age) " +
            "values (null,name = :name, age = :age)",
            nativeQuery = true)
    public void insertUser(@Param("name") String name, @Param("age") int age);

    // 5개 한번에 저장
    @Modifying
    @Transactional
    @Query(value = "insert into user(id,name,age) " +
            "values (null,'lee',1)," +
            " (null,'lee',1)," +
            " (null,'lee',1)," +
            " (null,'lee',1)," +
            " (null,'lee',1)",
            nativeQuery = true)
    public void insertUser5();


}
