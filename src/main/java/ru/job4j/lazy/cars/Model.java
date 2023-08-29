package ru.job4j.lazy.cars;

import javax.persistence.*;

@Entity
@Table(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "carbrand_id")
    private CarBrand carBrand;

    public static Model of(String name, CarBrand carBrand) {
        Model model = new Model();
        model.name = name;
        model.carBrand = carBrand;
        return model;
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

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    @Override
    public String toString() {
        return "Model{"
                + "id=" + id + '\''
                + ", name='" + name + '\''
                + ", carBrand=" + carBrand
                + '}';
    }
}
