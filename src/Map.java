import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Map extends Entity {
    public List<Entity> fixOb;
    public int[] check;
    public HashMap<Integer, Entity> link = new LinkedHashMap<>();

    public Map(int n, String s) {
        check = new int[n * n];
        for (int i : check) i = 0;
        size = n;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'E') {
                fixOb.add(new Entity((double) (i / size), (double) (i % size)));
                check[i] = 2;
            }
            if (s.charAt(i) == 'N') {
                fixOb.add(new Entity((double) (i / size), (double) (i % size)));
                check[i] = 1;
                link.put(i, fixOb.get(fixOb.size() - 1));
            }
        }

    }

    public int[] getCheck() {
        return check;
    }

    public void setCheck(int[] check) {
        this.check = check;
    }

    public List<Entity> getFixOb() {
        return fixOb;
    }

    public void setFixOb(List<Entity> fixOb) {
        this.fixOb = fixOb;
    }

    public void delete(List<Entity> selections) {
        for (int i = 0; i < selections.size(); i++)
            if (check[selections.get(i).value] == 1) {
                check[selections.get(i).value] = 0;
                fixOb.remove(fixOb.indexOf(selections.get(i).value));
                link.remove(i);
            }
    }


}
