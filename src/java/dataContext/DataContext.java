package dataContext;

import entidades.Auto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class DataContext {

    private static List<Auto> autos = null;

    public static List<Auto> getDataState() {
        if (autos == null) {
            autos = new ArrayList<>();
        }
        return autos;
    }

    public static boolean addAutoToList(Auto auto) {
        if (autos == null) {
            autos = new ArrayList<>();
        }
        try {
            autos.add(auto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
