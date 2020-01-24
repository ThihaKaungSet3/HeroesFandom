package non.shahad.heroesfandom.ui.home.models

import non.shahad.heroesfandom.domain.model.Comic

data class ParentComic(val name : String,
                       val comics : List<Comic>)