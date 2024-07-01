package vn.bvntp.app.helper

import android.view.View


class ClickHandler {

    companion object {
        fun AnimateButtonOnClick(view: View, next: () -> Unit){
            view.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
//                .setDuration(10)
                .withEndAction {
                    view.animate()
                        .scaleX(1f)
                        .scaleY(1f)
//                        .setDuration(10)
                        .withEndAction{
                            next()
                        }
                        .start()
                }
                .start()


        }
    }
}