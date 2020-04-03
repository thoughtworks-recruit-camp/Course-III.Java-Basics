package com.thoughtworks;

import com.thoughtworks.repository.DbUtil;

import java.sql.SQLException;
import java.util.Arrays;

public class AppStudent {

    public static void main(String[] args) throws SQLException {
        // 创建一批学生
        Student student1 = new Student("001", "张三", "男", 2020, "1994-01-12", "1-13");
        Student student2 = new Student("002", "李四", "男", 2020, "1994-05-25", "1-1");
        Student student3 = new Student("003", "王五", "男", 2019, "1995-04-02", "2-10");
        Student student4 = new Student("004", "周梅", "女", 2020, "1993-06-16", "1-1");
        Student student5 = new Student("005", "钱风", "男", 2020, "1993-01-10", "1-1");
        Student student6 = new Student("006", "吴兰", "女", 2019, "1995-06-09", "2-1");
        Student student7 = new Student("007", "李云", "男", 2019, "1993-08-11", "1-1");

        try (DbUtil dbUtil = new DbUtil("jdbc:mysql://localhost:3306/jdbc_demo?serverTimezone=UTC", "root", "root");  // FIXME
             StudentRepositoryNew repository = new StudentRepositoryNew()) {  // FIXME
//            StudentRepositoryOld repository = new StudentRepositoryOld()) {  // FIXME

            dbUtil.clear("student");
            repository.setConnection(dbUtil.getConnection());

            // 保存所有学生
            repository.saveAll(Arrays.asList(student1, student2, student3, student4, student5, student6));
            repository.save(student7);

            // 查找所有学生并打印
            System.out.println("所有学生----------------------------------------------");
            repository.queryAll().forEach(System.out::println);

            // 查询1-1班的所有同学的信息并按照学号倒序排列
            System.out.println("1-1班的学生-------------------------------------------");
            repository.queryByClassIdOrderByIdDesc("1-1").forEach(System.out::println);

            // 修改学号003的学生的姓名
            student3.setName("王伍");
            repository.update("003", student3);
            System.out.println("修改学号003的学生--------------------------------------");
            repository.queryAll().forEach(System.out::println);

            // 删除学号006的学生
            repository.delete("006");
            System.out.println("删除学号006的学生--------------------------------------");
            repository.queryAll().forEach(System.out::println);
        }
    }
}
