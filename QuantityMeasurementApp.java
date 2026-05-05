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

    // UC2: Inches Measurement Equality
    static class Inch {
        private final double value;

        public Inch(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || this.getClass() != obj.getClass())
                return false;

            Inch other = (Inch) obj;

            return Double.compare(this.value, other.value) == 0;
        }
    }

     // ✅ UC3: NEW CLASS (ADD THIS BELOW)
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0);

        private final double toFeetFactor;

        LengthUnit(double factor) {
            this.toFeetFactor = factor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;

            if (obj == null || this.getClass() != obj.getClass())
                return false;

            QuantityLength other = (QuantityLength) obj;

            double thisFeet = this.unit.toFeet(this.value);
            double otherFeet = other.unit.toFeet(other.value);

            return Double.compare(thisFeet, otherFeet) == 0;
        }
    }

    // Main method to test equality
    public static void main(String[] args) {

        // UC1: Feet Equality
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);
        System.out.println("Feet Equal (" + f1.equals(f2) + ")");

        // UC2: Inch Equality (same)
        Inch i1 = new Inch(1.0);
        Inch i2 = new Inch(1.0);
        System.out.println("Inch Equal (" + i1.equals(i2) + ")");

        // UC2: Inch Equality (different)
        Inch i3 = new Inch(1.0);
        Inch i4 = new Inch(2.0);
        System.out.println("Inch Equal (" + i3.equals(i4) + ")");

         // UC3: Quantity Length
        QuantityLength q1 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);
        System.out.println("12 inch equals 1 foot (" + q1.equals(q2) + ")");

    }
}