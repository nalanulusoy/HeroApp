package eduwork.smartapps.com.hero.Helper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import eduwork.smartapps.com.hero.Models.Enemy;
import eduwork.smartapps.com.hero.Models.Hero;
import eduwork.smartapps.com.hero.Models.Position;
import eduwork.smartapps.com.hero.Models.ProjectConstants;
import eduwork.smartapps.com.hero.Models.Resource;
public class InputParseOperation {

    public Hero getHero(String input) {
        Hero hero = new Hero();
        hero.setHealthPower(getHealthPower("Hero", input));
        hero.setAttackPoints(getAttackPoints("Hero", input));
        return hero;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Position> getRoute(String input) {

        List<Position> routeList = new ArrayList<Position>();
        Pattern routePat = Pattern.compile(ProjectConstants.positionRegex);
        Matcher routeMatcher = routePat.matcher(input);

        while(routeMatcher.find()) {
            String group = routeMatcher.group();

            Pattern enemyTypePattern = Pattern.compile("[a-z,A-Z]+");
            Matcher enemyTypeMatcher = enemyTypePattern.matcher(group);
            if(enemyTypeMatcher.find()) {
                String enemyType = enemyTypeMatcher.group();

                Pattern positionPattern = Pattern.compile("[0-9]+");
                Matcher positionMatcher = positionPattern.matcher(group);

                if(positionMatcher.find()) {
                    int position = Integer.valueOf(positionMatcher.group());
                    Position Location = new Position(getEnemy(enemyType,input),position);
                    routeList.add(Location);
                }
            }
        }


        Resource resource = getResource(input);
        //remove the routes that are ahead of the resource so that hero does not have to fight with those enemies.
        Iterator<Position> iterator = routeList.iterator();
        while(iterator.hasNext()) {
            Position Location = iterator.next();
            if(Location.getPosition() > resource.getDistance()) {
                iterator.remove();
            }
        }

        //sort using position comparator
        if(!routeList.isEmpty()) {
            routeList.sort(new LocationComparator());
        }

        return routeList;
    }

    private Resource getResource(String input) {
        Pattern resourcesPat = Pattern.compile(ProjectConstants.resourcesRegex);
        Matcher resourceMatcher = resourcesPat.matcher(input);

        while(resourceMatcher.find()) {
            String resourceMatch = resourceMatcher.group();
            Pattern metersPattern = Pattern.compile("[0-9]+");
            Matcher metersMatchMatcher = metersPattern.matcher(resourceMatch);
            if(metersMatchMatcher.find()) {
                int metersMatch = Integer.valueOf(metersMatchMatcher.group());
                return new Resource(metersMatch);
            }
        }
        return new Resource(0);
    }

    private Enemy getEnemy(String type, String input) {

        Pattern pattern = Pattern.compile(ProjectConstants.enemyRegex);
        Matcher matcher = pattern.matcher(input);

        Enemy enemy = null;
        while(matcher.find()) {

            String match = matcher.group();
            Pattern typePattern = Pattern.compile( type + " is Enemy");
            Matcher typeMatcher = typePattern.matcher(match);
            if(typeMatcher.find()) {
                enemy = new Enemy(type);
                enemy.setHealthPower(getHealthPower(type, input));
                enemy.setAttackPoints(getAttackPoints(type, input));
            }
        }

        return enemy;

    }

    private int getAttackPoints(String type, String input) {
        Pattern apPattern = Pattern.compile(type +" " + ProjectConstants.attackPointsRegex);
        Matcher apMatcher = apPattern.matcher(input);
        if(apMatcher.find()) {
            String apMatch = input.substring(apMatcher.start(), apMatcher.end());
            Pattern apNumeric = Pattern.compile("[0-9]+");
            Matcher apNumMatcher = apNumeric.matcher(apMatch);
            if(apNumMatcher.find()) {
                int ap = Integer.valueOf(apMatch.substring(apNumMatcher.start(), apNumMatcher.end()));
                return ap;
            }
        }
        return 0;
    }

    private int getHealthPower(String type, String input) {
        Pattern hpPattern = Pattern.compile(type + " " + ProjectConstants.healthPowerRegex);
        Matcher hpMatcher = hpPattern.matcher(input);
        if(hpMatcher.find()) {
            String hpMatch = input.substring(hpMatcher.start(), hpMatcher.end());
            Pattern hpNumeric = Pattern.compile("[0-9]+");
            Matcher hpnumMatcher = hpNumeric.matcher(hpMatch);
            if(hpnumMatcher.find()) {
                int hp = Integer.valueOf(hpMatch.substring(hpnumMatcher.start(), hpnumMatcher.end()));
                return hp;
            }
        }
        return 0;
    }

}
