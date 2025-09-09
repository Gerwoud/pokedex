package com.pokedex.pokedex.dto;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.component.view.control.BoxView;
import org.springframework.shell.component.view.screen.Screen;
import org.springframework.shell.geom.HorizontalAlign;
import org.springframework.shell.geom.Rectangle;
import org.springframework.shell.geom.VerticalAlign;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.StringUtils.capitalize;

public record PokedexDTO(
        int id,
        String name,
        String flavortext,
        String prevPokemon,
        String nextPokemon,
        List<String> types
) {

    private static final int WIDTH = 36;
    private static final Map<String, AttributedStyle> TYPE_COLORS = new HashMap<>();
    static {
        TYPE_COLORS.put("Normal", AttributedStyle.DEFAULT.background(200, 200, 200)); // Light gray (neutral)
        TYPE_COLORS.put("Fire", AttributedStyle.DEFAULT.background(255, 69, 0)); // Orange-red (fiery)
        TYPE_COLORS.put("Water", AttributedStyle.DEFAULT.background(0, 191, 255)); // Deep sky blue (watery)
        TYPE_COLORS.put("Grass", AttributedStyle.DEFAULT.background(0, 128, 0)); // Green (lush)
        TYPE_COLORS.put("Electric", AttributedStyle.DEFAULT.background(255, 255, 0)); // Yellow (electric)
        TYPE_COLORS.put("Ice", AttributedStyle.DEFAULT.background(173, 216, 230)); // Light blue (icy)
        TYPE_COLORS.put("Fighting", AttributedStyle.DEFAULT.background(139, 69, 19)); // Brown (earthy, strong)
        TYPE_COLORS.put("Poison", AttributedStyle.DEFAULT.background(128, 0, 128)); // Purple (toxic)
        TYPE_COLORS.put("Ground", AttributedStyle.DEFAULT.background(205, 133, 63)); // Tan (earthy)
        TYPE_COLORS.put("Flying", AttributedStyle.DEFAULT.background(135, 206, 235)); // Sky blue (airy)
        TYPE_COLORS.put("Psychic", AttributedStyle.DEFAULT.background(255, 0, 255)); // Magenta (mystical)
        TYPE_COLORS.put("Bug", AttributedStyle.DEFAULT.background(50, 205, 50)); // Lime green (bug-like)
        TYPE_COLORS.put("Rock", AttributedStyle.DEFAULT.background(139, 137, 137)); // Gray (rocky)
        TYPE_COLORS.put("Ghost", AttributedStyle.DEFAULT.background(106, 90, 205)); // Slate blue (ghostly)
        TYPE_COLORS.put("Dragon", AttributedStyle.DEFAULT.background(75, 0, 130)); // Indigo (mystical, powerful)
        TYPE_COLORS.put("Dark", AttributedStyle.DEFAULT.background(64, 64, 64)); // Dark gray (shadowy)
        TYPE_COLORS.put("Steel", AttributedStyle.DEFAULT.background(192, 192, 192)); // Silver (metallic)
        TYPE_COLORS.put("Fairy", AttributedStyle.DEFAULT.background(255, 182, 193)); // Light pink (magical)
    }

    public void draw(Screen.Writer writer, Rectangle ret) {
        int y = ret.y();

        y = drawHeaderPart(writer, ret.x(), y);
        y = drawTypesPart(writer, ret.x(), y);
        y = drawFlavorTextPart(writer, ret.x(), y);
        y = drawNavigationPart(writer, ret.x(), y);
        y = drawFooterPart(writer, ret.x(), y);
    }

    private int drawHeaderPart(Screen.Writer writer, int x, int y) {
        writer.text("=".repeat(WIDTH), x, y++);
        writer.text("Pokédex Entry #" + id, x, y++);
        writer.text("Name: " + name, x, y++);
        writer.text("-".repeat(WIDTH), x, y++);
        return y;
    }

    private int drawFlavorTextPart(Screen.Writer writer, int x, int y) {
        for (String line: flavortext.split("[\n\u000c]")) {
            writer.text(line, x, y++);
        }
        writer.text("-".repeat(WIDTH), x, y++);
        return y;
    }

    private int drawTypesPart(Screen.Writer writer, int x, int y) {
        if (types == null || types.isEmpty()) {
            return y;
        }

        String tmp = "";
        for (int i = 0; i < types.size(); i++) {
            AttributedStyle color = TYPE_COLORS.getOrDefault(capitalize(types.get(i)), AttributedStyle.DEFAULT.foreground(255));
            AttributedString styled = new AttributedString(types.get(i), color.foreground(255,255,255));
            writer.text("Type " + (i+1) + ": " + styled.toAnsi(), x, y);
            tmp +=  "Type " + (i+1) + ": " + styled.toAnsi();
            y++;
        }
        writer.text("-".repeat(WIDTH), x, y++);
        return y;
    }

    private int drawNavigationPart(Screen.Writer writer, int x, int y) {
        boolean hasPrev = prevPokemon != null && !prevPokemon.isEmpty();
        boolean hasNext = nextPokemon != null && !nextPokemon.isEmpty();

        StringBuilder arrows = new StringBuilder();
        if (hasPrev) arrows.append("<-");
        while (arrows.length() < WIDTH - (hasNext ? 2 : 0)) {
            arrows.append(" ");
        }
        if (hasNext) arrows.append("->");

        writer.text(arrows.toString(), x, y++);

        StringBuilder names = new StringBuilder();
        if (hasPrev) names.append(prevPokemon);
        while (names.length() < WIDTH - (hasNext ? nextPokemon.length() : 0)) {
            names.append(" ");
        }
        if (hasNext) names.append(nextPokemon);

        writer.text(names.toString(), x, y++);
        return y;
    }

    private int drawFooterPart(Screen.Writer writer, int x, int y) {
        writer.text("=".repeat(WIDTH), x, y++);
        return y;
    }

}
