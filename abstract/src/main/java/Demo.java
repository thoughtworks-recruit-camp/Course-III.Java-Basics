import chicken.Chicken;
import chicken.Cock;
import chicken.Hen;
import chicken.Chick;

public class Demo {
    public static void main(String[] args) {
        Chicken[] chickenArray = new Chicken[]{new Cock(), new Hen(), new Chick()};
        for (Chicken chicken : chickenArray) {
            System.out.format("100元可以买%s %d只\n", chicken.getType(), (int) (100 / chicken.getPrice()));
        }
    }

}
