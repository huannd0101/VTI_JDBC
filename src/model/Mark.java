package model;

public class Mark {
    private Integer id;
    private Double val;
    private Integer studentId;

    public Mark() {
    }

    public Mark(Integer id, Double val, Integer studentId) {
        this.id = id;
        this.val = val;
        this.studentId = studentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getVal() {
        return val;
    }

    public void setVal(Double val) {
        this.val = val;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
