package hql;

import javax.persistence.*;

@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int experience;
    private int salary;
    @OneToOne
    private VacancyDB vacancyDB;

    public static Candidate of(String name, int experience, int salary, VacancyDB vacancyDB) {
        Candidate cnd = new Candidate();
        cnd.setName(name);
        cnd.setExperience(experience);
        cnd.setSalary(salary);
        cnd.setVacancyDB(vacancyDB);
        return cnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public VacancyDB getVacancyDB() {
        return vacancyDB;
    }

    public void setVacancyDB(VacancyDB vacancyDB) {
        this.vacancyDB = vacancyDB;
    }

    @Override
    public String toString() {
        return "Candidate{"
                + "id=" + id + '\''
                + ", name='" + name + '\''
                + ", experience=" + experience + '\''
                + ", salary=" + salary + '\''
                + ", vacancyDB=" + vacancyDB + '\''
                + '}';
    }
}
