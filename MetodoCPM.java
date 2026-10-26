import java.util.Scanner;

public class MetodoCPM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("                   Metodo CPM                  ");

        System.out.print("Ingresa la cantidad de actividades: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        int columnas = 10;
        String[][] matriz = new String[cantidad + 1][columnas];

        // Encabezados
        matriz[0][0] = "No.";
        matriz[0][1] = "Actividad";
        matriz[0][2] = "T.Opt";
        matriz[0][3] = "T.Prob";
        matriz[0][4] = "T.Pesim";
        matriz[0][5] = "T.Esp";
        matriz[0][6] = "Predecesores";
        matriz[0][7] = "ES";
        matriz[0][8] = "EF";
        matriz[0][9] = "LS/LF/Holg";

        // Inicializar espacios vacíos
        for (int f = 1; f <= cantidad; f++) {
            for (int c = 0; c < columnas; c++) {
                matriz[f][c] = "---";
            }
        }

        System.out.println("\n--- Ingreso de datos ---\n");

        // Ingresar datos (ahora en enteros)
        for (int i = 0; i < cantidad; i++) {
            String nombreAct = String.valueOf((char) ('A' + i));
            matriz[i+1][0] = String.valueOf(i + 1);
            matriz[i+1][1] = nombreAct;

            System.out.println("Actividad " + nombreAct);

            System.out.print("  Tiempo O : ");
            int O = sc.nextInt();
            matriz[i+1][2] = String.valueOf(O);

            System.out.print("  Tiempo M : ");
            int M = sc.nextInt();
            matriz[i+1][3] = String.valueOf(M);

            System.out.print("  Tiempo P : ");
            int P = sc.nextInt();
            matriz[i+1][4] = String.valueOf(P);

            // Cálculo y redondeo a entero
            double tiempoEspExacto = (O + 4 * M + P) / 6.0;
            int tiempoEsp = (int) Math.round(tiempoEspExacto);
            matriz[i+1][5] = String.valueOf(tiempoEsp);

            sc.nextLine();
            System.out.print("  Predecesor(es): ");
            matriz[i+1][6] = sc.nextLine();

            System.out.println();
        }

        // Calcular inicio y final tempranos
        CalculoTemprano.calcular(matriz, cantidad);
        
        // Mostrar solo la matriz
        System.out.println();
        imprimirMatriz(matriz, cantidad, columnas);

        sc.close();
    }

    public static void imprimirMatriz(String[][] matriz, int filas, int columnas) {
        String linea = "---------------------------------------------------------------------------------------------------------------------------";
        System.out.println(linea);
        for (int f = 0; f <= filas; f++) {
            for (int c = 0; c < columnas; c++) {
                System.out.printf("%-13s", matriz[f][c]);
            }
            System.out.println();
            if (f == 0) System.out.println(linea);
        }
        System.out.println(linea);
    }
}