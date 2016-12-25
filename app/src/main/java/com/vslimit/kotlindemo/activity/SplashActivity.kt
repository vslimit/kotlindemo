package com.vslimit.kotlindemo.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import com.vslimit.kotlindemo.R
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivity
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.*
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity(), AnkoLogger {

    private val ANIM_TIME = 2000

    private val SCALE_END = 1.15f

    //完整生命周期开始时调用
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info("onCreate")
        info("onCreate:初始化一个Activity并填充UI")
        setContentView(R.layout.activity_splash)
        info("onCreate:${titleTv.text.toString()}")
//        startActivity<MainActivity>()
        Observable.timer(1000, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe { startAnim() }

    }

    //在onCreate方法完成后调用，用于恢复UI状态
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        info("onRestoreInstanceState")
        info("onRestoreInstanceState:从savedInstanceState恢复UI状态")
        info("onRestoreInstanceState:这个Bundle也被传递给了onCreate")
        info("onRestoreInstanceState:自Activity上次可见之后，只有当系统终止该Activity时，才会被调用")
        info("onRestoreInstanceState:在随后的Activity进程中的可见生存期之前调用")
    }


    //在可见生存期的开始时调用
    override fun onStart() {
        super.onStart()
        info("onStart")
        info("onStart:既然Activity可见，就应用任何要求的UI Change")
    }

    override fun onRestart() {
        super.onRestart()
        info("onRestart")
        info("onRestart:加装载改变，知道Activity在此进程中可见")
    }

    //在Activity状态生存期开始时调用
    override fun onResume() {
        super.onResume()
        info("onResume")
        info("onResume:恢复Activity需要，但是当它处于不活动状态时被挂起的暂停的UI更新、线程或进程")
        info("onResume:在Activity状态生命周期结束的时候调用，用来保存UI状态的改变")
    }

    //把UI状态改变保存到savedInstanceState
    //end of the active lifecycle
    override fun onSaveInstanceState(savedInstanceState: Bundle?) {
        super.onSaveInstanceState(savedInstanceState)
        titleTv.text = "change text"
        info("onSaveInstanceState")
        info("onSaveInstanceState:Save UI state changes to the savedInstanceState")
        info("onSaveInstanceState:如果进程被运行时终止并被重启，那么这个Bundle将被传递给onCreate和onRestoreInstanceState")
        info("onSaveInstanceState:killed and restarted by the run time.")
    }


    private fun startMainActivity() {
    }

    private fun startAnim() {
        val animatorX = ObjectAnimator.ofFloat(imgIv, "scaleX", 1f, SCALE_END)
        val animatorY = ObjectAnimator.ofFloat(imgIv, "scaleY", 1f, SCALE_END)

        val translationY = ObjectAnimator.ofFloat(titleTv, "translationY", 150f, 300f)
        val alpha = ObjectAnimator.ofFloat(titleTv, "alpha", 0f, 1f)
        val set = AnimatorSet()
        set.setDuration(ANIM_TIME.toLong()).play(translationY).with(alpha).before(animatorX).before(animatorY)
        set.start()
        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                this@SplashActivity.finish()
            }
        })
    }


    //在Activity状态生存期结束时调用
    override fun onPause() {
        super.onPause()
        info("onPause")
        info("onPause:挂起不需要更新的UI更新、线程或CPU密集进程")
        info("onPause:当Activity不是前台的活动状态的Activity,")
        info("onPause:the active foreground Activity")
    }

    //在可见生存期的结束时调用
    override fun onStop() {
        super.onStop()
        info("onStop")
        info("onStop:挂起不需要的UI更新、线程或处理")
        info("onStop:当Activity不可见时，保存所有的编辑或者状态改变，因为在调用这个方法后，进程可能会被终止")
    }

    //完整生命周期结束时调用
    override fun onDestroy() {
        super.onDestroy()
        info("onDestroy")
        info("onDestroy:清理所有的资源，包括结束线程、关闭数据库连接等")
    }

}
