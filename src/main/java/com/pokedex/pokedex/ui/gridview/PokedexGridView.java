package com.pokedex.pokedex.ui.gridview;

import com.pokedex.pokedex.dto.PokedexDTO;
import com.pokedex.pokedex.ui.gridview.griditems.*;
import com.pokedex.pokedex.ui.gridview.griditems.column.PokedexTypeAbilityColumn;
import org.springframework.shell.component.view.control.GridView;

public class PokedexGridView extends GridView {

    public PokedexGridView(PokedexDTO dto) {
        super();
        this.setRowSize(-5,-3,-1,-10,-1,-6,-1,-6);
        this.setColumnSize(1);

        PokedexGridTitleBox title = new PokedexGridTitleBox(dto);
        this.addItem(title, 0, 1, 1, 1, 0, 0);

        PokedexTypeAbilityColumn ability2 = new PokedexTypeAbilityColumn(dto);
        this.addItem(ability2, 1, 1, 1, 1, 0, 0);

        PokedexGridDivider divider = new PokedexGridDivider();

        this.addItem(divider, 2, 1, 1, 1, 0, 0);

        PokedexGridDescriptionBox description = new PokedexGridDescriptionBox(dto);
        this.addItem(description, 3, 1, 1, 1, 0, 0);

        PokedexGridDivider divider2 = new PokedexGridDivider();
        this.addItem(divider2, 4, 1, 1, 1, 0, 0);

        PokedexGridStatBox  stat = new PokedexGridStatBox(dto);
        this.addItem(stat, 5, 1, 1, 1, 0, 0);

        PokedexGridDivider divider3 = new PokedexGridDivider();
        this.addItem(divider3, 6, 1, 1, 1, 0, 0);


        PokedexGridNavigationBox  navigation = new PokedexGridNavigationBox(dto);
        this.addItem(navigation, 7, 1, 1, 1, 0, 0);
    }

}
