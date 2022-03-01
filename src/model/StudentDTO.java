package model;

import java.util.List;

public class StudentDTO {
    private Integer id;
    private String name;
    private List<Double> points;

    public StudentDTO() {
    }

    public StudentDTO(Integer id, String name, List<Double> points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getPoints() {
        return points;
    }

    public void setPoints(List<Double> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "{" +
                "'id': " + id +
                ", 'name': " + name + '\'' +
                ", 'points': " + points +
                '}';
    }
}
