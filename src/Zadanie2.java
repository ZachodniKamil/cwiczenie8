public class Zadanie2 {

    record ServiceOrder(String clientName, int hours, double hourRate) {}

    @FunctionalInterface
    interface PriceStrategy {
        double calculate(ServiceOrder order);
    }

    static class PriceCalculator {
        public double calculate(ServiceOrder order, PriceStrategy strategy) {
            return strategy.calculate(order);
        }
    }

    public static void main(String[] args) {
        ServiceOrder order = new ServiceOrder("Firma Alfa", 10, 120.0);
        PriceCalculator calculator = new PriceCalculator();

        PriceStrategy standard = o -> o.hours() * o.hourRate();
        PriceStrategy discount = o -> o.hours() * o.hourRate() * 0.90;
        PriceStrategy weekend  = o -> o.hours() * o.hourRate() * 1.25;

        System.out.println("Klient: " + order.clientName());
        System.out.println("Cena standardowa: " + calculator.calculate(order, standard));
        System.out.println("Cena z rabatem:   " + calculator.calculate(order, discount));
        System.out.println("Cena weekendowa:  " + calculator.calculate(order, weekend));
    }
}
