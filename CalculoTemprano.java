import java.util.Scanner;

public class MetodoCPM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("                   Metodo CPM                  ");

        System.out.print("Ingresa la cantidad de actividades: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        //12 columnas por defecto
        int columnas = 12;
        String[][] matriz = new String[cantidad + 1][columnas];

        // Encabezados 
        matriz[0][0] = "No.";
        matriz[0][1] = "Actividad";
        matriz[0][2] = "T.Opt";
        matriz[0][3] = "T.Prob";
        matriz[0][4] = "T.Pesim";
        matriz[0][5] = "T.Esp";
        matriz[0][6] = "Predecesores";
        matriz[0][7] = "ITem";
        matriz[0][8] = "FTem";
        matriz[0][9] = "ITar";
        matriz[0][10] = "FTar";
        matriz[0][11] = "Holgura";

        // Se llena con --- para indicar vacío
        for (int f = 1; f <= cantidad; f++) {
            for (int c = 0; c < columnas; c++) {
                matriz[f][c] = "---";
            }
        }

        System.out.println("\nIngreso de datos");

        // Ciclo para ingresar cada actividad
        for (int i = 0; i < cantidad; i++) {
            // Se va colocando el abecedario en las actividades por defecto
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

            // Calculamos Tiempo Esperado (O+4M+P)/6
            double tiempoEspExacto = (O + 4.0 * M + P) / 6.0;
            int tiempoEsp = (int) Math.round(tiempoEspExacto);
            matriz[i+1][5] = String.valueOf(tiempoEsp);

            sc.nextLine(); 

            // Pedimos predecesores separados por coma
            System.out.print("  Predecesor: ");
            matriz[i+1][6] = sc.nextLine().trim();

            System.out.println();
        }

        // Llamamos al cálculo Temprano
        CalculoTemprano.calcular(matriz, cantidad);

        // Creamos objeto y llamamos al cálculo Tardío
        CalculoTardio objTardio = new CalculoTardio();
        objTardio.calcular(matriz, cantidad);

        // Creamos objeto y llamamos al cálculo Z y probabilidad
        CalculoZ objZ = new CalculoZ(matriz, cantidad, sc);
        objZ.calcularZSegunTuCriterio();

        // Imprimimos la matriz completa
        System.out.println("\nMatriz final");
        imprimirMatriz(matriz, cantidad, columnas);

        sc.close();
    }

    // Método para imprimir la matriz
    public static void imprimirMatriz(String[][] matriz, int filas, int columnas) {
        String linea = "------------------------------------------------------------------------------------------------------------------------------------------------";
        for (int f = 0; f <= filas; f++) {
            for (int c = 0; c < columnas; c++) {
                System.out.printf("%-12s", matriz[f][c]);
            }
            System.out.println();
            if (f == 0) System.out.println(linea);
        }
        System.out.println(linea);
    }
}
