package manzano.wilson.multiapp.Objects;

/**
 * Created by User on 22/01/2018.
 */

public class Profession {

    private String professional_description;
    private String professional_name;
    private String professional_area;

    public Profession(String professional_description, String professional_name, String professional_area) {
        this.professional_description = professional_description;
        this.professional_name = professional_name;
        this.professional_area = professional_area;
    }


    public Profession() {
    }


    public String getProfessional_description() {
        return professional_description;
    }

    public void setProfessional_description(String professional_description) {
        this.professional_description = professional_description;
    }

    public String getProfessional_name() {
        return professional_name;
    }

    public void setProfessional_name(String professional_name) {
        this.professional_name = professional_name;
    }

    public String getProfessional_area() {
        return professional_area;
    }

    public void setProfessional_area(String professional_area) {
        this.professional_area = professional_area;
    }
}
