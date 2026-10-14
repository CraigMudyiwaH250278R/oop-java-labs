package lab11.library;

import java.util.ArrayList;
import java.util.List;

/**
 * Minimal RFC 4180-style CSV: fields containing a comma or quote are wrapped in quotes with
 * embedded quotes doubled. Needed because titles such as "Design Patterns, Elements" contain commas
 * and a naive split(",") would corrupt them.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
final class Csv {
    private Csv() { }

    static String line(String... fields) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            if (i > 0) sb.append(',');
            sb.append(escape(fields[i]));
        }
        return sb.toString();
    }

    private static String escape(String field) {
        if (field.indexOf(',') >= 0 || field.indexOf('"') >= 0) {
            return '"' + field.replace("\"", "\"\"") + '"';
        }
        return field;
    }

    static List<String> parse(String line) {
        List<String> out = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (inQuotes) {
                if (c == '"' && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    cur.append('"');
                    i++;
                } else if (c == '"') {
                    inQuotes = false;
                } else {
                    cur.append(c);
                }
            } else if (c == '"') {
                inQuotes = true;
            } else if (c == ',') {
                out.add(cur.toString());
                cur.setLength(0);
            } else {
                cur.append(c);
            }
        }
        out.add(cur.toString());
        return out;
    }
}
