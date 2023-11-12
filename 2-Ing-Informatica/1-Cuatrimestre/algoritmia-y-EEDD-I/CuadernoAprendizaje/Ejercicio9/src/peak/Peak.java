package peak;

import java.awt.Point;

/**
 *
 * @author antoni
 */
public interface Peak {
    /**
     * Algorisme d'inicialització.
     * Donat un array d'enters.
     * Els valors van amb ordre creixent sense repetició fins a un índex, i a 
     * partir d'aquest índex van amb ordre decreixent sense repetició. 
     * Trobar l'element cim i el seu índex. Un element és el cim, si és més
     * gran que els seus veïnats. El cim mai estarà en els extrems i només hi 
     * ha un cim. (Heu de trobar el valor màxim, i només hi ha un màxim). 
     * @param a conjunt d'enters
     * @return Point x és el peak, i és l'índex
     */
    Point peak(int [] a) throws Exception;
}