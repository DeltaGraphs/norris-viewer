package deltagraphs.norrisviewer.presenter;

public class Convert {

    public static class Hsl {
        public double h, s, l;

        public Hsl(double h, double s, double l) {
            this.h = h;
            this.s = s;
            this.l = l;
        }
    }

    public static Hsl rgbToHsl(double r, double g, double b) {
        r /= 255d;
        g /= 255d;
        b /= 255d;

        double max = Math.max(Math.max(r, g), b), min = Math.min(Math.min(r, g), b);
        double h, s, l = (max + min) / 2;

        if (max == min) {
            h = s = 0; // achromatic
        } else {
            double d = max - min;
            s = l > 0.5 ? d / (2 - max - min) : d / (max + min);

            if (max == r) h = (g - b) / d + (g < b ? 6 : 0);
            else if (max == g) h = (b - r) / d + 2;
            else h = (r - g) / d + 4; // if (max == b)

            h /= 6;
        }

        return new Hsl(h, s, l);
    }

}
