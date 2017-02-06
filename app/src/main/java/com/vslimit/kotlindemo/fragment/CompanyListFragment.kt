package com.vslimit.kotlindemo.fragment

import android.os.Bundle
import android.view.View
import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.db.Company
import com.vslimit.kotlindemo.db.CompanyTable
import com.vslimit.kotlindemo.extensions.*
import kotlinx.android.synthetic.main.fragment_companys.*
import org.jetbrains.anko.async
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update
import org.jetbrains.anko.onClick
import org.jetbrains.anko.support.v4.toast
import java.util.*


/**
 * Created by vslimit on 16/12/31.
 */
class CompanyListFragment : BaseFragment() {
    override val layoutResourceId: Int = R.layout.fragment_companys

    companion object {
        fun getInstance(): CompanyListFragment {
            return CompanyListFragment()
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        showBtn.onClick {
            context.database.use {
                val company = select(CompanyTable.TABLE_NAME).byId(1).parseOpt { Company(HashMap(it)) }
                async() {
                    nameTv.text = company?.name
                }
            }
        }

        updateBtn.onClick {
            context.database.use {
                val company = select(CompanyTable.TABLE_NAME).byId(1).parseOpt { Company(HashMap(it)) }
                company?.name = "update_name"
                update(CompanyTable.TABLE_NAME, *company!!.map.toVarargArray()).where("_id = {id}", "id" to 1).exec()
            }
        }

        insertBtn.onClick {
            context.database.use {
                val company = Company()
                company.name = "demo_name"
                company.address = "demo_address"
                insert(CompanyTable.TABLE_NAME, *company.map.toVarargArray())
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    fun init() {
        context.database.use {
            val list = select(com.vslimit.kotlindemo.db.CompanyTable.TABLE_NAME)
                    .parseList { com.vslimit.kotlindemo.db.Company(java.util.HashMap(it)) }
            async() {
                nameTv.text = list.size.toString()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}