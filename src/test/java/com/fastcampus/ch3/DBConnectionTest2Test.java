package com.fastcampus.ch3;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DBConnectionTest2Test extends TestCase {
    @Autowired
    DataSource ds; // 각 테스트는 iv를 공유하지 않는다

    @Test
    public void  testInsert() throws Exception{
        User user = new User("asdfff", "1234", "abc", "aaaa@aaaa.com", "fb", new Date(), new Date());

        deleteAll();

        int rowCnt = insertUser(user);

        assertTrue(rowCnt==1);
    }

    @Test
    public void testSelectUser() throws Exception{
        deleteAll(); // 각 테스트는 다른 테스트에서 독립적이어야 한다, 여러번 수행해도 실패하면 안됨
        User user = new User("asdfff", "1234", "abc", "aaaa@aaaa.com", "fb", new Date(), new Date());
        int rowCnt = insertUser(user);

        User user2 = selectUser("asdfff");

        assertTrue(user.getId().equals("asdfff"));
    }

    @Test
    public  void  testDeleteUser() throws Exception{
        deleteAll();
        int rowCnt = deleteUser("asdfff");

        assertTrue(rowCnt == 0);

        User user = new User("asdfff", "1234", "abc", "aaaa@aaaa.com", "fb", new Date(), new Date());
        rowCnt = insertUser(user);
        assertTrue(rowCnt == 1);

        rowCnt = deleteUser(user.getId());
        assertTrue(rowCnt == 1);

        assertTrue(selectUser(user.getId())==null);
    }

    @Test
    public void testUpdateUser() throws Exception{
        deleteAll();
        int rowCnt = deleteUser("asdfff");
        assertTrue(rowCnt == 0);

        User user = new User();

        user = new User("asdfff", "1234", "abc", "aaaa@aaaa.com", "fb", new Date(), new Date());
        rowCnt = insertUser(user);
        assertTrue(rowCnt == 1);

        user = new User("asdfff", "5678", "abc", "aaaa@aaaa.co.kr", "kakao", new Date(), new Date());
        rowCnt = updateUser(user);
        assertTrue(rowCnt == 1);

        assertTrue(selectUser(user.getId())!=null);

    }

    // 매개변수로 받은 사용자 정보로 user_info테이블을 update하는 메서드
    public int updateUser(User user) throws Exception{
        Connection conn = ds.getConnection();

        String sql = "UPDATE user_info SET  pwd=?, email=?, sns=?";

        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection 공격 방어, 성능향상 (재사용, 캐싱효과)
        pstmt.setString(1, user.getPwd());
        pstmt.setString(2, user.getEmail());
        pstmt.setString(3, user.getSns());

        int rowCnt = pstmt.executeUpdate(); // sql문 실행, 생성된 로우 카운트 반환

        return rowCnt;
    }

    public  int deleteUser(String id) throws Exception{
        Connection conn = ds.getConnection();

        String sql = "DELETE FROM user_info WHERE id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection 공격 방어, 성능향상 (재사용, 캐싱효과)
        pstmt.setString(1, id);

        return  pstmt.executeUpdate(); // sql문 실행, 생성된 로우 카운트 반환
    }

    public User selectUser(String id) throws Exception{
        Connection conn = ds.getConnection();

        String sql = "SELECT * FROM user_info WHERE id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection 공격 방어, 성능향상 (재사용, 캐싱효과)
        pstmt.setString(1, id);

        ResultSet rs = pstmt.executeQuery(); // select 일때 사용

        if(rs.next()){
            User user = new User();
            user.setId(rs.getString(1));
            user.setPwd(rs.getString(2));
            user.setName(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setSns(rs.getString(5));
            user.setBirth(new Date(rs.getDate(6).getTime()));
            user.setRegDate(new Date(rs.getDate(7).getTime()));

            return user;
        }
        return null;
    }

    private void deleteAll() throws Exception {

        Connection conn = ds.getConnection();

//        insert into user_info (id, pwd, name, email, sns, birth, reg_date)
//        values ('asdf23', '1234', 'hoondal', 'aaa@aaa.com','facebook', '2022-01-01', now());

        String sql = "DELETE FROM user_info";

        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection 공격 방어, 성능향상 (재사용, 캐싱효과)
        pstmt.executeUpdate(); // sql문 실행, 생성된 로우 카운트 반환

    }

    // 사용자 정보를 user_info테이블에 저장하는 메서드
    public int insertUser(User user) throws Exception{
        Connection conn = ds.getConnection();

//        insert into user_info (id, pwd, name, email, sns, birth, reg_date)
//        values ('asdf23', '1234', 'hoondal', 'aaa@aaa.com','facebook', '2022-01-01', now());

        String sql = "insert into user_info values (?, ?, ?, ?,?,?, now())";

        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection 공격 방어, 성능향상 (재사용, 캐싱효과)
        pstmt.setString(1,user.getId());
        pstmt.setString(2,user.getPwd());
        pstmt.setString(3,user.getName());
        pstmt.setString(4,user.getEmail());
        pstmt.setString(5,user.getSns());
        pstmt.setDate(6,new java.sql.Date(user.getBirth().getTime()));

        int rowCnt = pstmt.executeUpdate(); // sql문 실행, 생성된 로우 카운트 반환

        return rowCnt;

    }

   // @Test
    public void testMain() throws Exception{ // 테스트 시 언제나 public void, 메서드 이름은 상관없음
//        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);
        assertTrue(conn!=null); // 괄호 안의 조건식이 true이면, 테스트 성공, 아니면 실패


    }

}