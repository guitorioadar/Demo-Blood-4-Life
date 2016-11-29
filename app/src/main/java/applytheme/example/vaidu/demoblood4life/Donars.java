package applytheme.example.vaidu.demoblood4life;

/**
 * Created by Guitorio on 11/11/2016.
 */
public class Donars {

    String image,name,blood_group,phone,university;

    public Donars() {
    }

    public Donars(String image, String name, String blood_group, String phone, String university) {
        this.image = image;
        this.name = name;
        this.blood_group = blood_group;
        this.phone = phone;
        this.university = university;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
