package com.pokedex.pokedex.ui.pokedexlistview;

import com.pokedex.pokedex.dto.PokedexDTO;
import org.springframework.shell.component.view.control.ListView;
import org.springframework.shell.style.ThemeResolver;

import java.util.List;

public class PokedexListView extends ListView<PokedexDTO> {


    public PokedexListView(List<PokedexDTO> pokemon, ThemeResolver themeResolver) {
        super(ListView.ItemStyle.NOCHECK);
        this.setCellFactory(
                (list, item) -> new PokedexListViewCell(item, themeResolver)
        );
        this.setItems(pokemon);
        setThemeResolver(themeResolver);
        this.setShowBorder(true);
        this.setTitle("Pokedex");
        this.setBorderPadding(2,0,4,0);
    }

    @Override
    protected String getThemeName() {
        return "pokedex";
    }
}
