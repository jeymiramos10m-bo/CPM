// Clase que calcula Inicio Tardío, Fin Tardío y Holgura
public class CalculoTardio {

    public void calcular(String[][] matriz, int cantidad) {
        // Buscamos el tiempo final máximo del proyecto
        int tiempoFinal = 0;
        for (int i = 1; i <= cantidad; i++) {
            int ef = Integer.parseInt(matriz[i][8]);
            if (ef > tiempoFinal) tiempoFinal = ef;
        }

        // Arreglo para saber cuáles ya fueron calculados
        boolean[] calculado = new boolean[cantidad + 1];

        boolean terminado;
        do {
            terminado = true;
            // Recorremos cada actividad
            for (int i = 1; i <= cantidad; i++) {
                if (calculado[i]) continue;

                String nomAct = matriz[i][1];
                // se busca cuáles son los sucesores de esta actividad
                String[] sucesores = buscarSucesores(matriz, cantidad, nomAct);

                // Si no tiene sucesores su Fin Tardío es el tiempo final
                if (sucesores.length == 0) {
                    int duracion = Integer.parseInt(matriz[i][5]);
                    matriz[i][10] = String.valueOf(tiempoFinal);
                    matriz[i][9] = String.valueOf(tiempoFinal - duracion);
                    int es = Integer.parseInt(matriz[i][7]);
                    int ls = Integer.parseInt(matriz[i][9]);
                    matriz[i][11] = String.valueOf(ls - es);
                    calculado[i] = true;
                    continue;
                }

                boolean todosSucesoresListos = true;
                int minLS = Integer.MAX_VALUE;

                // Tomamos el menor Inicio Tardío de los sucesores
                for (String nomSuc : sucesores) {
                    nomSuc = nomSuc.trim();
                    int idxSuc = buscarIndice(matriz, cantidad, nomSuc);
                    if (idxSuc == -1 || matriz[idxSuc][9].equals("---")) {
                        todosSucesoresListos = false;
                        break;
                    }
                    int lsSuc = Integer.parseInt(matriz[idxSuc][9]);
                    if (lsSuc < minLS) minLS = lsSuc;
                }

                // Si todos sucesores están listos calculamos
                if (todosSucesoresListos) {
                    int duracion = Integer.parseInt(matriz[i][5]);
                    matriz[i][10] = String.valueOf(minLS);
                    matriz[i][9] = String.valueOf(minLS - duracion);
                    int es = Integer.parseInt(matriz[i][7]);
                    int ls = Integer.parseInt(matriz[i][9]);
                    matriz[i][11] = String.valueOf(ls - es);
                    calculado[i] = true;
                } else {
                    terminado = false;
                }
            }
        } while (!terminado);
    }

    // Método auxiliar para buscar los sucesores
    private String[] buscarSucesores(String[][] matriz, int cantidad, String nombreAct) {
        StringBuilder sb = new StringBuilder();
        for (int j = 1; j <= cantidad; j++) {
            String predStr = matriz[j][6];
            String[] preds = predStr.split(",");
            for (String p : preds) {
                p = p.trim();
                if (p.equalsIgnoreCase(nombreAct)) {
                    sb.append(matriz[j][1]).append(",");
                }
            }
        }
        if (sb.length() == 0) return new String[0];
        return sb.toString().split(",");
    }

    // Método auxiliar para buscar el índice por nombre
    private int buscarIndice(String[][] matriz, int cantidad, String nombre) {
        for (int i = 1; i <= cantidad; i++) {
            if (matriz[i][1].equalsIgnoreCase(nombre)) return i;
        }
        return -1;
    }
}