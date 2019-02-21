package eduwork.smartapps.com.hero.Models;
import java.util.List;
import eduwork.smartapps.com.hero.Helper.MessageWriter;
import eduwork.smartapps.com.hero.Interface.IAttack;
public class Hero extends LivingProperties implements IAttack  {
    private boolean alive=true;
    private MessageWriter writer;
    private StringBuilder output = new StringBuilder();
    public void start(List<Position> positions) {
        output.append("Hero started journey with "+ getHealthPower() +" HP! \n");
        for (Position position : positions) {
            if(isAlive()) {
                attack(position);
            }
        }

        if(getHealthPower() > 0 )
            output.append("Hero survived!");

        writer.writeMessage(output.toString());

    }
    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public MessageWriter getWriter() {
        return writer;
    }

    public void setWriter(MessageWriter writer) {
        this.writer = writer;
    }

    @Override
    public void attack(Position position) {
        Enemy e = position.getEnemy();

        while(getHealthPower() > 0 && e.getHealthPower() >0) {
            setHealthPower(getHealthPower()-e.getAttackPoints());
            e.setHealthPower(e.getHealthPower()-getAttackPoints());
        }

        if(getHealthPower() > 0) {
            output.append("Hero defeated "+  e.getType() + " with " + getHealthPower()+ " HP remaining \n");
        }
        else {
            output.append(e.getType() + " defeated Hero"+ " with " + e.getHealthPower()+ " HP remaining \n");
            output.append("Hero is Dead!! Last seen at position "+ position.getPosition() +"!! \n");
            setAlive(false);
        }
    }
}
