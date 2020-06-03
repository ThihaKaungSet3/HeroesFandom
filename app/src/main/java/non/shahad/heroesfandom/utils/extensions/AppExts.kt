package non.shahad.heroesfandom.utils.extensions

import timber.log.Timber

fun timber(tag: String,msg: String){
    Timber.tag(tag).d(msg)
}