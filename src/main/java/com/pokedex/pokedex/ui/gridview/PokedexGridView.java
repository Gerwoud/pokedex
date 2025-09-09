package com.pokedex.pokedex.ui.gridview;

import com.pokedex.pokedex.dto.PokedexDTO;
import com.pokedex.pokedex.ui.gridview.griditems.*;
import org.springframework.shell.component.view.control.GridView;

public class PokedexGridView extends GridView {

    public PokedexGridView(PokedexDTO dto) {
        super();
        this.setRowSize(5,3,1,10,1,6);
        this.setColumnSize(1);

        PokedexGridTitleBox title = new PokedexGridTitleBox(dto);
        this.addItem(title, 0, 1, 1, 1, 0, 0);

        PokedexGridTypeBox type = new PokedexGridTypeBox(dto);
        this.addItem(type, 1, 1, 1, 1, 0, 0);

        PokedexGridDivider divider = new PokedexGridDivider();

        this.addItem(divider, 2, 1, 1, 1, 0, 0);

        PokedexGridDescriptionBox description = new PokedexGridDescriptionBox(dto);
        this.addItem(description, 3, 1, 1, 1, 0, 0);

        PokedexGridDivider divider2 = new PokedexGridDivider();
        this.addItem(divider2, 4, 1, 1, 1, 0, 0);

        PokedexGridNavigationBox  navigation = new PokedexGridNavigationBox(dto);
        this.addItem(navigation, 5, 1, 1, 1, 0, 0);
    }

}
