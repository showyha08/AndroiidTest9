package com.websarva.wings.android.fragmentsample1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuListFragment : Fragment() {

    //    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(true)
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // フラグメントで表示する画面をXMLファイルからインフレートする
        val view = inflater.inflate(R.layout.fragment_menu_list, container, false)
        // 画面部品ListViewを取得
        val lvMenu = view.findViewById<ListView>(R.id.lvMenu)
        // SimpleAdapterで使用するMutableListオブジェクトを用意
        val menuList: MutableList<MutableMap<String, String>> = mutableListOf()
        // 「唐揚げ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        var menu = mutableMapOf("name" to "唐揚げ定食", "price" to "850円")
        menuList.add(menu)
        // 「ハンバーグ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "ハンバーグ定食", "price" to "850円")
        menuList.add(menu)
        // 「生姜焼き」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "生姜焼き", "price" to "850円")
        menuList.add(menu)
        // SimpleAdapter 第4引き数from用データの用意
        val from = arrayOf("name", "price")
        // SimpleAdapter 第5引数 to 用データの用意
        val to = intArrayOf(android.R.id.text1, android.R.id.text2)
        // SimpleAdapterを生成
        val adapter = SimpleAdapter(
            activity,
            menuList,
            android.R.layout.simple_list_item_2,
            from,
            to
        )
        // アダプタの登録
        lvMenu.adapter = adapter

        // インフレートされた画面を戻り地として返す
        return view
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            //タップされた行のデータを取得
            val item = parent.getItemAtPosition(position) as MutableMap<String, String>
            //定食名と金額取得
            val menuName = item["name"]
            val menuPrice = item["price"]
            val intent2MenuThanks = Intent(activity, MenuThanksActivity::class.java).apply {
                putExtra("menuName", menuName)
                putExtra("menuPrice", menuPrice)
            }

            //第２画面の起動
            startActivity(intent2MenuThanks)
        }
    }

}