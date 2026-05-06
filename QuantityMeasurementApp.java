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

     // UC3: NEW CLASS
        enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),

        // UC4 additions
        YARD(3.0),
        CENTIMETER(0.393701 / 12.0);

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
        // UC5: Unit Conversion Method
        public static double convert(double value, LengthUnit source, LengthUnit target) {

            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid value");
            }

            if (source == null || target == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            // Convert to base unit (feet)
            double valueInFeet = source.toFeet(value);

            // Convert to target unit
            return valueInFeet / target.toFeet(1.0);
        }
        // UC6: Addition of two lengths
        public QuantityLength add(QuantityLength other) {

            if (other == null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }

            if (!Double.isFinite(this.value) || !Double.isFinite(other.value)) {
                throw new IllegalArgumentException("Invalid value");
            }

            if (this.unit == null || other.unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            // Convert both to feet
            double thisInFeet = this.unit.toFeet(this.value);
            double otherInFeet = other.unit.toFeet(other.value);

            // Add
            double sumInFeet = thisInFeet + otherInFeet;

            // Convert back to THIS unit
            double resultValue = sumInFeet / this.unit.toFeet(1.0);

            return new QuantityLength(resultValue, this.unit);
        }
        // UC7: Addition with target unit
        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {

            if (other == null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }

            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            if (!Double.isFinite(this.value) || !Double.isFinite(other.value)) {
                throw new IllegalArgumentException("Invalid value");
            }

            // Convert both to feet
            double thisInFeet = this.unit.toFeet(this.value);
            double otherInFeet = other.unit.toFeet(other.value);

            // Add
            double sumInFeet = thisInFeet + otherInFeet;

            // Convert to TARGET UNIT
            double resultValue = sumInFeet / targetUnit.toFeet(1.0);

            return new QuantityLength(resultValue, targetUnit);
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

        // UC4
        System.out.println(
            new QuantityLength(1.0, LengthUnit.YARD)
            .equals(new QuantityLength(3.0, LengthUnit.FEET))
        );

        System.out.println(
            new QuantityLength(1.0, LengthUnit.CENTIMETER)
            .equals(new QuantityLength(0.393701, LengthUnit.INCH))
        );

        // UC5 Output
        System.out.println("1 foot to inch: " +
                QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCH));

        System.out.println("3 yard to feet: " +
                QuantityLength.convert(3.0, LengthUnit.YARD, LengthUnit.FEET));

        System.out.println("36 inch to yard: " +
                QuantityLength.convert(36.0, LengthUnit.INCH, LengthUnit.YARD));

        System.out.println("1 cm to inch: " +
                QuantityLength.convert(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH));
        
        // UC6 Output

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("1 ft + 12 inch = " + a.add(b).value + " " + a.unit);

        QuantityLength c = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength d = new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println("1 yard + 3 feet = " + c.add(d).value + " " + c.unit);

        QuantityLength e = new QuantityLength(2.54, LengthUnit.CENTIMETER);
        QuantityLength f = new QuantityLength(1.0, LengthUnit.INCH);

        System.out.println("2.54 cm + 1 inch = " + e.add(f).value + " " + e.unit);

        // UC7 Output

        QuantityLength x = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength y = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("Result in FEET: " + x.add(y, LengthUnit.FEET).value + " " + LengthUnit.FEET);

        System.out.println("Result in INCH: " + x.add(y, LengthUnit.INCH).value + " " + LengthUnit.INCH);

        System.out.println("Result in YARD: " + x.add(y, LengthUnit.YARD).value + " " + LengthUnit.YARD);

        QuantityLength p = new QuantityLength(2.54, LengthUnit.CENTIMETER);
        QuantityLength q = new QuantityLength(1.0, LengthUnit.INCH);

        System.out.println("CM result: " + p.add(q, LengthUnit.CENTIMETER).value + " " + LengthUnit.CENTIMETER);

    }
}