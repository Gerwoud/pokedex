package com.pokedex.pokedex.ui.gridview.griditems;

import com.pokedex.pokedex.dto.PokedexDTO;
import com.pokedex.pokedex.dto.StatDTO;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.component.view.screen.Screen;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.util.StringUtils.capitalize;

public class PokedexGridStatBox extends PokedexGridBox {

    private static final Map<String, AttributedStyle> STAT_COLORS = new HashMap<>();
    static {
        STAT_COLORS.put("Hp", AttributedStyle.DEFAULT.background(144, 238, 144)); // Light Green
        STAT_COLORS.put("Attack", AttributedStyle.DEFAULT.background(255, 215, 0)); // Gold
        STAT_COLORS.put("Defense", AttributedStyle.DEFAULT.background(255, 165, 0)); // Orange
        STAT_COLORS.put("Special-attack", AttributedStyle.DEFAULT.background(135, 206, 235)); // Sky Blue
        STAT_COLORS.put("Special-defense", AttributedStyle.DEFAULT.background(138, 43, 226)); // Dark Purple
        STAT_COLORS.put("Speed", AttributedStyle.DEFAULT.background(218, 112, 214)); // Light Pink
    }

    private static final Map<String, String> STAT_NAMES = new HashMap<>();
    static {
        STAT_NAMES.put("hp", "HP:      ");
        STAT_NAMES.put("attack", "Attack:  ");
        STAT_NAMES.put("defense", "Defense: ");
        STAT_NAMES.put("special-attack", "Sp. Atk: ");
        STAT_NAMES.put("special-defense", "Sp. Def: ");
        STAT_NAMES.put("speed", "Speed:   ");
    }

    public PokedexGridStatBox(PokedexDTO dto) {
        super(dto);
    }

    @Override
    protected void drawInternal(Screen screen) {
        super.drawInternal(screen);

        Screen.Writer writer = screen.writerBuilder().build();

        writer.text("Base stats:", getInnerRect().x()+2, getInnerRect().y());
        int y = 1;
        for (StatDTO stat: dto.stats()) {
            AttributedStyle color = STAT_COLORS.getOrDefault(capitalize(stat.name()), AttributedStyle.DEFAULT.foreground(255));
            AttributedString styled = new AttributedString(" " + STAT_NAMES.get(stat.name()) + stat.base() + " ", color.foreground(255,255,255));
            writer.text("    " + styled.toAnsi(), getInnerRect().x()+2, getInnerRect().y()+y);
            y++;
        }
    }
}
