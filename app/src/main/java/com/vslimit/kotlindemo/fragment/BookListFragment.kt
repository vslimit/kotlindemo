package com.vslimit.kotlindemo.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.extensions.*
import com.vslimit.kotlindemo.realmobj.Book
import com.vslimit.kotlindemo.util.realm.RealmUtil
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_companys.*
import org.jetbrains.anko.async
import org.jetbrains.anko.onClick
import java.util.*
import kotlin.properties.Delegates


/**
 * Created by vslimit on 16/12/31.
 */
class BookListFragment : BaseFragment() {
    override val layoutResourceId: Int = R.layout.fragment_companys

    var realm: Realm by Delegates.notNull()

    companion object {
        fun getInstance(): BookListFragment {
            return BookListFragment()
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        showBtn.onClick {
            val book = realm.show(Book::class.java, mapOf("author" to "vslimit"))
            Log.d("BOOK:::", book.name)
            val name = book.name
            async() { nameTv.text = name }
        }

        updateBtn.onClick {
            val book = realm.show(Book::class.java, mapOf("author" to "vslimit"))
            book.name = "update name"
            realm.createOrUpdate(book)
        }

        insertBtn.onClick {
            val book = Book()
            book.id = UUID.randomUUID().toString()
            book.name = "Kotlin Realm Android"
            book.author = "vslimit"
            realm.create(book)
        }
    }

    override fun onResume() {
        super.onResume()
        realm.close()
    }

    fun init() {
        realm = RealmUtil.instant()
        val results = realm.findAll(Book::class.java)
        val sizeTxt = "BOOK SIZE:${results.size}"
        async() {
            nameTv.text = sizeTxt
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}