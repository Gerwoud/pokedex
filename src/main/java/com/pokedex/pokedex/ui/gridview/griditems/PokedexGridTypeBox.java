package com.pokedex.pokedex.ui.gridview.griditems;

import com.pokedex.pokedex.dto.PokedexDTO;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.component.view.screen.Screen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.StringUtils.capitalize;

public class PokedexGridTypeBox extends PokedexGridBox {

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

    public PokedexGridTypeBox(PokedexDTO dto) {
        super(dto);
    }

    @Override
    protected void drawInternal(Screen screen) {
        super.drawInternal(screen);
        Screen.Writer writer = screen.writerBuilder().build();

        int y = getInnerRect().y();
        int x = getInnerRect().x()+2;

        List<String> types = dto.types();
        writer.text("Types:", x, y++);
        for (int i = 0; i < types.size(); i++) {
            AttributedStyle color = TYPE_COLORS.getOrDefault(capitalize(types.get(i)), AttributedStyle.DEFAULT.foreground(255));
            AttributedString styled = new AttributedString(" "+types.get(i)+" ", color.foreground(255,255,255));
            writer.text("    Type " + (i+1) + ": " + styled.toAnsi(), x, y);
            y++;
        }
    }
}
