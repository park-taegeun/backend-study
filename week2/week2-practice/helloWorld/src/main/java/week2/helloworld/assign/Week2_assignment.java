package week2.helloworld.assign;

import jakarta.persistence.*;

@Entity
@Table(name = "week2_assign")
public class Week2_assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private Long age;
    private String department;
    private Long studentNum;

    public Long getId() { return id;}
    public String getName() { return name;}
    public Long getAge() { return age;}
    public String getDepartment() { return department; }
    public Long getStudent_num() { return studentNum;}
}
