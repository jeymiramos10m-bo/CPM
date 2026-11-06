import java.util.Scanner;

// Clase que calcula Z y la probabilidad automática de terminar el proyecto
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
        double a1 = 0.4361836;
        double a2 = -0.1201676;
        double a3 = 0.9372980;
        double t = 1.0 / (1.0 + 0.33267 * Math.abs(z));
        double area = 0.5 * (1.0 + Math.signum(z) * (1.0 - Math.exp(-z*z/2) * t * (a1 + a2*t + a3*t*t)));
        return area;
    }

    public void calcularZSegunTuCriterio() {
        // Pedimos el tiempo deseado para terminar el proyecto
        System.out.print("\nIngresa el tiempo determinado para terminar el proyecto: ");
        double tiempoDeseado = sc.nextDouble();

        // Tomamos el último valor de Fin Tardío
        double ultimoFinTardio = Double.parseDouble(matriz[cantidad][10]);

        // Fórmula que tú indicaste: Z = (Tiempo Deseado − Último Fin Tardío)/2
        double valorZ = (tiempoDeseado - ultimoFinTardio) / 2.0;

        // Calculamos automáticamente la probabilidad
        double probabilidad = funcionNormal(valorZ);
        double porcentaje = probabilidad * 100;

        // Mostramos resultados completos
        System.out.println("\n            CÁLCULO DE Z Y PROBABILIDAD            ");
        System.out.printf("Último Fin Tardío: %.2f días\n", ultimoFinTardio);
        System.out.printf("Tiempo determinado para terminar: %.2f días\n", tiempoDeseado);
        System.out.printf("Z = (Tiempo Deseado − Último Fin Tardío)/2 = %.2f\n", valorZ);
        System.out.printf("Probabilidad de terminar en ese tiempo o antes: %.2f%%\n", porcentaje);
        System.out.println("=====================================================");
  
    }
}