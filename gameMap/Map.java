package gameMap;
public class Map {
    private int[][] terrain;


    public Map(int[][] terrain) {
        this.terrain = terrain;
    }

    public int getRowCount() {
        return terrain.length; // Nombre de lignes dans la matrice
    }

    public int getColumnCount() {
        return terrain[0].length; // Nombre de colonnes dans la matrice
    }

    public int getElementAt(int i, int j) {
        return terrain[i][j]; // Obtenir la valeur de l'élément à la position (i, j)
    }


}
