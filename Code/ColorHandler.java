import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Random;

public class ColorHandler {

    private static HashMap<String,Color> knownColorByName;

    static {
        knownColorByName = new HashMap();
        knownColorByName.put("aliceblue", new Color(0xF0, 0xF8, 0xFF));
        knownColorByName.put("antiquewhite", new Color(0xFA, 0xEB, 0xD7));
        knownColorByName.put("aqua", new Color(0x00, 0xFF, 0xFF));
        knownColorByName.put("aquamarine", new Color(0x7F, 0xFF, 0xD4));
        knownColorByName.put("azure", new Color(0xF0, 0xFF, 0xFF));
        knownColorByName.put("beige", new Color(0xF5, 0xF5, 0xDC));
        knownColorByName.put("bisque", new Color(0xFF, 0xE4, 0xC4));
        knownColorByName.put("black", new Color(0x00, 0x00, 0x00));
        knownColorByName.put("blanchedalmond", new Color(0xFF, 0xEB, 0xCD));
        knownColorByName.put("blue", new Color(0x00, 0x00, 0xFF));
        knownColorByName.put("blueviolet", new Color(0x8A, 0x2B, 0xE2));
        knownColorByName.put("brown", new Color(0xA5, 0x2A, 0x2A));
        knownColorByName.put("burlywood", new Color(0xDE, 0xB8, 0x87));
        knownColorByName.put("cadetblue", new Color(0x5F, 0x9E, 0xA0));
        knownColorByName.put("chartreuse", new Color(0x7F, 0xFF, 0x00));
        knownColorByName.put("chocolate", new Color(0xD2, 0x69, 0x1E));
        knownColorByName.put("coral", new Color(0xFF, 0x7F, 0x50));
        knownColorByName.put("cornflowerblue", new Color(0x64, 0x95, 0xED));
        knownColorByName.put("cornsilk", new Color(0xFF, 0xF8, 0xDC));
        knownColorByName.put("crimson", new Color(0xDC, 0x14, 0x3C));
        knownColorByName.put("cyan", new Color(0x00, 0xFF, 0xFF));
        knownColorByName.put("darkblue", new Color(0x00, 0x00, 0x8B));
        knownColorByName.put("darkcyan", new Color(0x00, 0x8B, 0x8B));
        knownColorByName.put("darkgoldenrod", new Color(0xB8, 0x86, 0x0B));
        knownColorByName.put("darkgray", new Color(0xA9, 0xA9, 0xA9));
        knownColorByName.put("darkgreen", new Color(0x00, 0x64, 0x00));
        knownColorByName.put("darkkhaki", new Color(0xBD, 0xB7, 0x6B));
        knownColorByName.put("darkmagenta", new Color(0x8B, 0x00, 0x8B));
        knownColorByName.put("darkolivegreen", new Color(0x55, 0x6B, 0x2F));
        knownColorByName.put("darkorange", new Color(0xFF, 0x8C, 0x00));
        knownColorByName.put("darkorchid", new Color(0x99, 0x32, 0xCC));
        knownColorByName.put("darkred", new Color(0x8B, 0x00, 0x00));
        knownColorByName.put("darksalmon", new Color(0xE9, 0x96, 0x7A));
        knownColorByName.put("darkseagreen", new Color(0x8F, 0xBC, 0x8F));
        knownColorByName.put("darkslateblue", new Color(0x48, 0x3D, 0x8B));
        knownColorByName.put("darkslategray", new Color(0x2F, 0x4F, 0x4F));
        knownColorByName.put("darkturquoise", new Color(0x00, 0xCE, 0xD1));
        knownColorByName.put("darkviolet", new Color(0x94, 0x00, 0xD3));
        knownColorByName.put("deeppink", new Color(0xFF, 0x14, 0x93));
        knownColorByName.put("deepskyblue", new Color(0x00, 0xBF, 0xFF));
        knownColorByName.put("dimgray", new Color(0x69, 0x69, 0x69));
        knownColorByName.put("dodgerblue", new Color(0x1E, 0x90, 0xFF));
        knownColorByName.put("firebrick", new Color(0xB2, 0x22, 0x22));
        knownColorByName.put("floralwhite", new Color(0xFF, 0xFA, 0xF0));
        knownColorByName.put("forestgreen", new Color(0x22, 0x8B, 0x22));
        knownColorByName.put("fuchsia", new Color(0xFF, 0x00, 0xFF));
        knownColorByName.put("gainsboro", new Color(0xDC, 0xDC, 0xDC));
        knownColorByName.put("ghostwhite", new Color(0xF8, 0xF8, 0xFF));
        knownColorByName.put("gold", new Color(0xFF, 0xD7, 0x00));
        knownColorByName.put("goldenrod", new Color(0xDA, 0xA5, 0x20));
        knownColorByName.put("gray", new Color(0x80, 0x80, 0x80));
        knownColorByName.put("green", new Color(0x00, 0x80, 0x00));
        knownColorByName.put("greenyellow", new Color(0xAD, 0xFF, 0x2F));
        knownColorByName.put("honeydew", new Color(0xF0, 0xFF, 0xF0));
        knownColorByName.put("hotpink", new Color(0xFF, 0x69, 0xB4));
        knownColorByName.put("indianred", new Color(0xCD, 0x5C, 0x5C));
        knownColorByName.put("indigo", new Color(0x4B, 0x00, 0x82));
        knownColorByName.put("ivory", new Color(0xFF, 0xFF, 0xF0));
        knownColorByName.put("khaki", new Color(0xF0, 0xE6, 0x8C));
        knownColorByName.put("lavender", new Color(0xE6, 0xE6, 0xFA));
        knownColorByName.put("lavenderblush", new Color(0xFF, 0xF0, 0xF5));
        knownColorByName.put("lawngreen", new Color(0x7C, 0xFC, 0x00));
        knownColorByName.put("lemonchiffon", new Color(0xFF, 0xFA, 0xCD));
        knownColorByName.put("lightblue", new Color(0xAD, 0xD8, 0xE6));
        knownColorByName.put("LightCyan", new Color(0xE0, 0xFF, 0xFF));
        knownColorByName.put("LightGoldenRodYellow", new Color(0xFA, 0xFA, 0xD2));
        knownColorByName.put("LightGray", new Color(0xD3, 0xD3, 0xD3));
        knownColorByName.put("LightGreen", new Color(0x90, 0xEE, 0x90));
        knownColorByName.put("LightPink", new Color(0xFF, 0xB6, 0xC1));
        knownColorByName.put("LightSalmon", new Color(0xFF, 0xA0, 0x7A));
        knownColorByName.put("LightSeaGreen", new Color(0x20, 0xB2, 0xAA));
        knownColorByName.put("LightSkyBlue", new Color(0x87, 0xCE, 0xFA));
        knownColorByName.put("LightSlateGray", new Color(0x77, 0x88, 0x99));
        knownColorByName.put("LightSteelBlue", new Color(0xB0, 0xC4, 0xDE));
        knownColorByName.put("LightYellow", new Color(0xFF, 0xFF, 0xE0));
        knownColorByName.put("Lime", new Color(0x00, 0xFF, 0x00));
        knownColorByName.put("LimeGreen", new Color(0x32, 0xCD, 0x32));
        knownColorByName.put("Linen", new Color(0xFA, 0xF0, 0xE6));
        knownColorByName.put("Magenta", new Color(0xFF, 0x00, 0xFF));
        knownColorByName.put("Maroon", new Color(0x80, 0x00, 0x00));
        knownColorByName.put("MediumAquaMarine", new Color(0x66, 0xCD, 0xAA));
        knownColorByName.put("MediumBlue", new Color(0x00, 0x00, 0xCD));
        knownColorByName.put("MediumOrchid", new Color(0xBA, 0x55, 0xD3));
        knownColorByName.put("MediumPurple", new Color(0x93, 0x70, 0xDB));
        knownColorByName.put("MediumSeaGreen", new Color(0x3C, 0xB3, 0x71));
        knownColorByName.put("MediumSlateBlue", new Color(0x7B, 0x68, 0xEE));
        knownColorByName.put("MediumSpringGreen", new Color(0x00, 0xFA, 0x9A));
        knownColorByName.put("MediumTurquoise", new Color(0x48, 0xD1, 0xCC));
        knownColorByName.put("MediumVioletRed", new Color(0xC7, 0x15, 0x85));
        knownColorByName.put("MidnightBlue", new Color(0x19, 0x19, 0x70));
        knownColorByName.put("MintCream", new Color(0xF5, 0xFF, 0xFA));
        knownColorByName.put("MistyRose", new Color(0xFF, 0xE4, 0xE1));
        knownColorByName.put("Moccasin", new Color(0xFF, 0xE4, 0xB5));
        knownColorByName.put("NavajoWhite", new Color(0xFF, 0xDE, 0xAD));
        knownColorByName.put("Navy", new Color(0x00, 0x00, 0x80));
        knownColorByName.put("OldLace", new Color(0xFD, 0xF5, 0xE6));
        knownColorByName.put("Olive", new Color(0x80, 0x80, 0x00));
        knownColorByName.put("OliveDrab", new Color(0x6B, 0x8E, 0x23));
        knownColorByName.put("Orange", new Color(0xFF, 0xA5, 0x00));
        knownColorByName.put("OrangeRed", new Color(0xFF, 0x45, 0x00));
        knownColorByName.put("Orchid", new Color(0xDA, 0x70, 0xD6));
        knownColorByName.put("PaleGoldenRod", new Color(0xEE, 0xE8, 0xAA));
        knownColorByName.put("PaleGreen", new Color(0x98, 0xFB, 0x98));
        knownColorByName.put("PaleTurquoise", new Color(0xAF, 0xEE, 0xEE));
        knownColorByName.put("PaleVioletRed", new Color(0xDB, 0x70, 0x93));
        knownColorByName.put("PapayaWhip", new Color(0xFF, 0xEF, 0xD5));
        knownColorByName.put("PeachPuff", new Color(0xFF, 0xDA, 0xB9));
        knownColorByName.put("Peru", new Color(0xCD, 0x85, 0x3F));
        knownColorByName.put("Pink", new Color(0xFF, 0xC0, 0xCB));
        knownColorByName.put("Plum", new Color(0xDD, 0xA0, 0xDD));
        knownColorByName.put("PowderBlue", new Color(0xB0, 0xE0, 0xE6));
        knownColorByName.put("Purple", new Color(0x80, 0x00, 0x80));
        knownColorByName.put("Red", new Color(0xFF, 0x00, 0x00));
        knownColorByName.put("RosyBrown", new Color(0xBC, 0x8F, 0x8F));
        knownColorByName.put("RoyalBlue", new Color(0x41, 0x69, 0xE1));
        knownColorByName.put("SaddleBrown", new Color(0x8B, 0x45, 0x13));
        knownColorByName.put("Salmon", new Color(0xFA, 0x80, 0x72));
        knownColorByName.put("SandyBrown", new Color(0xF4, 0xA4, 0x60));
        knownColorByName.put("SeaGreen", new Color(0x2E, 0x8B, 0x57));
        knownColorByName.put("SeaShell", new Color(0xFF, 0xF5, 0xEE));
        knownColorByName.put("Sienna", new Color(0xA0, 0x52, 0x2D));
        knownColorByName.put("Silver", new Color(0xC0, 0xC0, 0xC0));
        knownColorByName.put("SkyBlue", new Color(0x87, 0xCE, 0xEB));
        knownColorByName.put("SlateBlue", new Color(0x6A, 0x5A, 0xCD));
        knownColorByName.put("SlateGray", new Color(0x70, 0x80, 0x90));
        knownColorByName.put("Snow", new Color(0xFF, 0xFA, 0xFA));
        knownColorByName.put("SpringGreen", new Color(0x00, 0xFF, 0x7F));
        knownColorByName.put("SteelBlue", new Color(0x46, 0x82, 0xB4));
        knownColorByName.put("Tan", new Color(0xD2, 0xB4, 0x8C));
        knownColorByName.put("Teal", new Color(0x00, 0x80, 0x80));
        knownColorByName.put("Thistle", new Color(0xD8, 0xBF, 0xD8));
        knownColorByName.put("Tomato", new Color(0xFF, 0x63, 0x47));
        knownColorByName.put("Turquoise", new Color(0x40, 0xE0, 0xD0));
        knownColorByName.put("Violet", new Color(0xEE, 0x82, 0xEE));
        knownColorByName.put("Wheat", new Color(0xF5, 0xDE, 0xB3));
        knownColorByName.put("White", new Color(0xFF, 0xFF, 0xFF));
        knownColorByName.put("WhiteSmoke", new Color(0xF5, 0xF5, 0xF5));
        knownColorByName.put("Yellow", new Color(0xFF, 0xFF, 0x00));
        knownColorByName.put("YellowGreen", new Color(0x9A, 0xCD, 0x32));
        changeLower();
    }
    public static void changeLower(){
        HashMap<String, Color> temp = new HashMap();
        for(var set: knownColorByName.entrySet()){
            temp.put(set.getKey().toLowerCase(),set.getValue());
        }
       knownColorByName = temp;
    }
    private static Color parseColor(final String value) {
        if (value == null) {
            return null;
        }

        final Object o = knownColorByName.get(value.toLowerCase());
        if (o != null) {
            return (Color) o;
        }

        try {
            return Color.decode(value.trim());
        } catch (NumberFormatException nfe) {
            return null;
        }
    }

    public static Color convertColor(final String colorText) {
        Random random = new Random();
        int r = random. nextInt(256);
        int g = random. nextInt(256);
        int b = random. nextInt(256);
        Color randomColor = new Color(r,g,b);

        if (colorText == null || colorText.isEmpty()) {
            return randomColor;
        }

        final Color retval = parseColor(colorText);
        if (retval == null) {
            return randomColor;
        }
        return retval;
    }
}

