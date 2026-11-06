import java.util.Scanner;

public class CalculoZ {

    private String[][] matriz;
    private int cantidad;
    private Scanner sc;

    // Constructor que recibe la matriz, cantidad y el Scanner
    public CalculoZ(String[][] matriz, int cantidad, Scanner sc) {
        this.matriz = matriz;
        this.cantidad = cantidad;
        this.sc = sc;
    }

    // Método que aproxima la curva normal Φ(Z)
    private double funcionNormal(double z) {
    double t = 1.0 / (1.0 + 0.2316419 * Math.abs(z));

    double d = 0.3989422804 * Math.exp(-z * z / 2);

    double p = 1 - d * t * (
        0.319381530 +
        t * (-0.356563782 +
        t * (1.781477937 +
        t * (-1.821255978 +
        t * 1.330274429)))
    );

    return z >= 0 ? p : 1 - p;
}

    public void calcularZSegunTuCriterio() {
        // Pedimos el tiempo deseado para terminar el proyecto
        System.out.print("\nIngresa el tiempo determinado para terminar el proyecto: ");
        double tiempoDeseado = sc.nextDouble();

        // Tomamos el último valor de Fin Tardío
        double ultimoFinTardio = Double.parseDouble(matriz[cantidad][10]);

        // Fórmula para Z
        double valorZ = (tiempoDeseado - ultimoFinTardio) / 2.0;

        // Calculamos automáticamente la probabilidad
        double probabilidad = funcionNormal(valorZ);
        double porcentaje = probabilidad * 100;

        // Mostramos resultados completos
        System.out.println("\n            CÁLCULO DE Z            ");
        System.out.printf("Último Fin Tardío: %.2f días\n", ultimoFinTardio);
        System.out.printf("Tiempo determinado para terminar: %.2f días\n", tiempoDeseado);
        System.out.printf("Z = (Tiempo Deseado - Último Fin Tardío)/2 = %.2f\n", valorZ);
        System.out.printf("Probabilidad de terminar en ese tiempo o antes: %.2f%%\n", porcentaje);
        System.out.println("=====================================================");
  
    }
}