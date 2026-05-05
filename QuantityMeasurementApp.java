// UC1: Feet Measurement Equality

public class QuantityMeasurementApp {

    // Inner class representing Feet measurement
    static class Feet {
        private final double value;  

        // Constructor to initialize value
        public Feet(double value) {
            this.value = value;
        }

        // Overriding equals() method for value-based comparison
        @Override
        public boolean equals(Object obj) {

            // Reflexive check (same reference)
            if (this == obj) {
                return true;
            }

            // Null check and type safety
            if (obj == null || this.getClass() != obj.getClass()) {
                return false;
            }

            // Type casting
            Feet other = (Feet) obj;

            // Compare double values safely
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // Main method to test equality
    public static void main(String[] args) {

        // Creating two Feet objects
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        // Checking equality
        boolean result = f1.equals(f2);

        // Display result
        System.out.println("Equal (" + result + ")");
    }
}