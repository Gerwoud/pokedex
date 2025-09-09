package com.pokedex.pokedex.ui.gridview.griditems.column;

import com.pokedex.pokedex.dto.PokedexDTO;
import com.pokedex.pokedex.ui.gridview.griditems.PokeGridAbilityBox;
import com.pokedex.pokedex.ui.gridview.griditems.PokedexGridTypeBox;
import org.springframework.shell.component.view.control.GridView;

public class PokedexTypeAbilityColumn extends GridView {

    PokedexDTO dto;

    public PokedexTypeAbilityColumn(PokedexDTO dto) {
        super();
        this.setColumnSize(-1,-1);
        this.setRowSize(-1);

        PokeGridAbilityBox pokeGridAbilityBox = new PokeGridAbilityBox(dto);
        PokedexGridTypeBox pokedexGridTypeBox = new PokedexGridTypeBox(dto);

        this.addItem(pokedexGridTypeBox, 0, 1, 1, 1, 0, 0);
        this.addItem(pokeGridAbilityBox, 0, 0, 1, 1, 0, 0);

    }

}
