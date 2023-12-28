package backtracking;

import java.util.Arrays;

/**
 * Implementació de l'algorisme de Backtracking per a la resolució de MapColor.
 * @author Marc Link y Jesús Castillo
 */
public class BacktrackingImpl implements Backtracking {
    /**
     * Donat un map, indica si és pot colorejar amb un màxim de nColors de forma
     * que les regions veïnades del mapa, no comparteixin nColors.
     * @param map mapa, on per cada regió es llisten les regions veïnades
     * @param nColors número màxim de colors a utilitzar
     * @return indica si es pot colorejar amb el nColors o no
     */
    @Override
    public boolean mapColorRecursive(int[][] map, int nColors) {
        int[] colors = new int[map.length];
        return mapColorRecursive(map, nColors, colors, 0);
    }

    /**
     * Donat un map, indica si és pot colorejar amb un màxim de nColors de forma
     * que les regions veïnades del mapa, no comparteixin nColors.
     * <br><br>
     * <p>
     * Analisis de casos:
     * - Cas base: si region == map.length, significa que s'ha assignat un color a cada regió amb èxit,
     * i per tant es retorna true.
     * - Cas recursiu: per cada color, es comprova si és vàlid per a la regió, i si ho és, es crida
     *  recursivament amb la següent regió.
     * </p>
     * <br>
     * <p>
     * Proof:
     * - Cas base: si region == map.length, significa que s'ha assignat un color a cada regió, i per tant
     * es retorna true.
     * - Cas recursiu: per cada color, es comprova si és vàlid per a la regió, i si ho és, es crida
     * recursivament amb la següent regió. Si en algun moment retorna true, significa que s'ha assignat
     * un color a cada regió, i per tant es retorna true. Si no retorna true, significa que no s'ha pogut
     * assignar un color a la regió, i per tant es retorna false.
     * </p>
     * <br>
     * <p>
     * Relació del ordre de complexitat amb un arbre binari:
     * - Cada nivell de l'arbre representa una regió, i cada fill representa un color. Per tant, el nombre
     * de nodes de l'arbre és nColors^map.length.
     * </p>
     * @pre necesitam un mapa que indiqui les regions veïnades, el número màxim de colors a utilitzar,
     * un array de colors que indiqui el color assignat a cada regió i la regió a la que s'assignarà un color.
     * El mapa ha de seguir la següent estructura: mapa[regió][veinats de la regió]
     * @post si retorna true, colors[0..map.length-1] té assignat un color a cada regió i les 
     * regions veïnades del mapa no comparteixin color.
     * @complexity O(nColors^map.length)
     * @param map mapa, on per cada regió es llisten les regions veïnades
     * @param nColors número màxim de colors a utilitzar
     * @param colors colors assignats a cada regió
     * @param region regió a la que s'assignarà un color
     * @return indica si es pot colorejar amb el nColors o no
     */
    private boolean mapColorRecursive(int[][] map, int nColors, int[] colors, int region) {
        if (region == map.length) {
            return true;
        }
        for (int color = 1; color <= nColors; color++) {
            if (isColorValid(map, colors, region, color)) {
                colors[region] = color;
                if (mapColorRecursive(map, nColors, colors, region + 1)) return true;
            }
        }
        return false;
    }

    /**
     * Comprova si el color és vàlid per a la regió
     * @pre colors[0..region-1] té assignat un color a cada regió
     * @post si retorna true, colors[0..map.length-1] té assignat un color a cada regió
     * @complexity O(n)
     * @param map mapa, on per cada regió es llisten les regions veïnades
     * @param colors colors assignats a cada regió
     * @param region regió a la que s'assignarà un color
     * @param color color a assignar
     * @return true si el color és vàlid per a la regió, false en cas contrari
     */
    private boolean isColorValid(int[][] map, int[] colors, int region, int color) {
       for (int i = 0; i < map[region].length; i++) {
            if (colors[map[region][i]] == color) {
                return false;
            }
        }
        return true;
    }

    /**
     * Donat un map, indica si és pot colorejar amb un màxim de nColors de forma
     * que les regions veïnades del mapa, no comparteixin nColors.
     * @param map mapa, on per cada regió es llisten les regions veïnades
     * @param nColors número màxim de colors a utilitzar
     * @return indica si es pot colorejar amb el nColors o no
     */
    public boolean mapColorIterative(int[][] map, int nColors) {
        int[] colors = new int[map.length];
        Arrays.fill(colors, -1);
        int region = 0;
        while (region >= 0) {
            if (colors[region] < nColors - 1) {
                colors[region]++;
                if (isColorValid(map, colors, region, colors[region]) && (region == map.length - 1)) {
                    return true;
                } else if (isColorValid(map, colors, region, colors[region]) && (region < map.length - 1)) {
                    region++;
                }
            } else {
                colors[region] = -1;
                region--;
            }
        }
        return false;
    }
}
