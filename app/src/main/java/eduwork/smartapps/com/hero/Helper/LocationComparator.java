package eduwork.smartapps.com.hero.Helper;

import java.util.Comparator;

import eduwork.smartapps.com.hero.Models.Position;

public class LocationComparator implements Comparator<Position> {
    @Override
    public int compare(Position o1, Position o2) {
        return Integer.compare(o1.getPosition(), o2.getPosition());
    }
}
