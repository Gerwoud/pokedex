package com.pokedex.pokedex.ui.gridview.griditems;

import com.pokedex.pokedex.dto.PokedexDTO;
import org.springframework.shell.component.view.control.BoxView;

public abstract class PokedexGridBox extends BoxView {

    PokedexDTO dto;

    public PokedexGridBox(PokedexDTO dto) {
        this.dto = dto;
    }
}
