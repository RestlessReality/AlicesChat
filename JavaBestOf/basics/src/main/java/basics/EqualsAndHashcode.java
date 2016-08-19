package basics;

/**
 * Wenn von einer Klasse Instanzen erzeugt werden,
 * die in einer Collection landen oder verglichen werden,
 * sollte immer die Equals-Methode angepasst werden.
 *
 * Wenn die Equals-Methode angepasst wird, muss immer auch die Hashcode-Methode mit angepasst werden!
 *
 * Daran auch denken wenn ich eine bestehende Klasse ändere!
 */
public class EqualsAndHashcode {
    private int a=3;
    private int b=6;


    /**
     * Erzeugen lassen in IntelliJ mit
     * Alt + Einfg => equals und hashCode
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EqualsAndHashcode that = (EqualsAndHashcode) o;

        if (a != that.a) return false;
        return b == that.b;

    }

    @Override
    public int hashCode() {
        int result = a;
        result = 31 * result + b;
        return result;
    }
}
