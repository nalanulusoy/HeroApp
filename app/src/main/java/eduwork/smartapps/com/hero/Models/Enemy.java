package eduwork.smartapps.com.hero.Models;

public class Enemy extends LivingProperties {
    private String type;
    public Enemy(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

}
