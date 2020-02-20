package com.thoughtworks;

public class App {

    public static void main(String[] args) {
        MemoryRepository<Student> memoryRepository = new MemoryRepository<>();
        Student[] initStudents = new Student[]{
                new Student("3", "张三"),
                new Student("4", "李四"),
                new Student("5", "王五"),
                new Student("6", "赵六"),
                new Student("7", "钱七")
        };
        for (Student student : initStudents) {
            memoryRepository.save(student.getId(), student);
        }

        System.out.println("\n获取并打印张三的信息：");
        System.out.println(memoryRepository.get("3"));

        System.out.println("\n删除李四（无输出）");
        memoryRepository.delete("4");

        System.out.println("新建学生冯五替换王五（无输出）");
        Student newStudentV = new Student("5", "冯五");
        memoryRepository.update(newStudentV.getId(), newStudentV);

        System.out.println("\n获取仓库中的所有学生并打印：");
        RepositoryUtil.printList(memoryRepository.list());

        System.out.println("\n打印操作日志：");
        RepositoryUtil.printList(memoryRepository.getLogs());
    }
}
