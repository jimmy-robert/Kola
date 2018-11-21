package io.kola.sample

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.kola.core.SupportFragment
import io.kola.core.elevate
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.withArguments
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var navigator: StackNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {

            button("Navigate") {
                onClick {
                    navigator.navigate(F().withArguments("color" to randomColor()))
                }
            }.lparams()

            frameLayout {
                id = R.id.myContent
                backgroundColor = Color.WHITE
            }.lparams(width = matchParent, height = 0, weight = 1f) {
                margin = dip(32)
            }

        }

        navigator = StackNavigator(supportFragmentManager, R.id.myContent)

        if (navigator.isEmpty) navigator.navigate(F().withArguments("color" to randomColor()))
    }

    override fun onBackPressed() {
        if (!navigator.onBackPressed()) {
            super.onBackPressed()
        }
    }

    private fun randomColor(): Int {
        val random = Random()
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }

    class F : SupportFragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return UI {

                frameLayout {

                    backgroundColor = arguments?.getInt("color", 0) ?: 0

                    textView("Fragment").lparams {
                        gravity = Gravity.CENTER
                    }

                }

            }.view
        }
    }

    class StackNavigator(private val fragmentManager: FragmentManager, private val containerId: Int) {

        val isEmpty get() = count == 0

        private var count = fragmentManager.fragments.size

        fun navigate(fragment: SupportFragment) {
            if (count > 0) {
                val currentPrimaryView = fragmentManager.fragments[count - 1].view!!
                add(fragment)
                val width = currentPrimaryView.parentView?.width ?: 0
                if (width > 0) {
                    val newPrimaryView = fragment.view!!
                    newPrimaryView.translationX = width.toFloat()
                    newPrimaryView.elevate(newPrimaryView.dip(count * 8))
                    currentPrimaryView.animate().translationX(-width / 4f)
                    newPrimaryView.animate().translationX(0f)
                }
            } else {
                add(fragment)
            }
            count++
        }

        fun onBackPressed(): Boolean {
            if (count <= 1) return false

            val fragments = fragmentManager.fragments

            val removingFragment = fragments[count - 1]
            val newPrimaryFragment = fragments[count - 2]

            val removingView = removingFragment.view!!
            val newPrimaryView = newPrimaryFragment.view!!

            val width = removingView.parentView?.width ?: 0
            if (width > 0) {
                newPrimaryView.translationX = -width / 4f
                removingView.elevate(newPrimaryView.dip(count * 8))
                removingView.animate().translationX(width.toFloat()).withEndAction {
                    remove(removingFragment)
                }
                newPrimaryView.animate().translationX(0f)
            } else {
                remove(removingFragment)
            }

            count--
            return true
        }

        private fun add(fragment: SupportFragment) {
            fragmentManager.beginTransaction()
                    .add(containerId, fragment)
                    .commit()
            fragmentManager.executePendingTransactions()
        }

        private fun remove(fragment: SupportFragment) {
            fragmentManager.beginTransaction()
                    .remove(fragment)
                    .commit()
            fragmentManager.executePendingTransactions()
        }

        private val View.parentView get() = parent as? ViewGroup

    }
}
