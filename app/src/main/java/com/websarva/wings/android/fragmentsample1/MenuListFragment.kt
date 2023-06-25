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

class MenuListFragment : Fragment() {

    private var _isLayoutXLarge = true

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

        //リスナの登録
        lvMenu.onItemClickListener = ListItemClickListener()

        // インフレートされた画面を戻り地として返す
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        // 親クラスのメソッド呼び出し
        super.onActivityCreated(savedInstanceState)

        //自分が所属するアクティビティからmenuThanksFrameを取得
        val menuThanksFrame = activity?.findViewById<View>(R.id.menuThanksFrame)
        //menuThanksFrameがnull,つまり存在しないなら...
        if (menuThanksFrame == null) {
            //画面判定フラグを通常画面とする
            _isLayoutXLarge = false
        }

    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            //タップされた行のデータを取得
            val item = parent.getItemAtPosition(position) as MutableMap<String, String>
            //定食名と金額取得
            val menuName = item["name"]
            val menuPrice = item["price"]

            // 引き継ぎデータをまとめてBundle
            val bundle = Bundle()
            //Bundleオブジェクトに引き継ぎデータを書くの
            bundle.putString("menuName", menuName)
            bundle.putString("menuPrice", menuPrice)

            //大画面の場合
            if (_isLayoutXLarge) {
                //フラグメントトランザクションの開始
                val transaction = fragmentManager?.beginTransaction()
                //注文完了フラグメントを生成
                val menuThanksFragment = MenuThanksFragment()
                //引き継ぎデータを注文完了フラグメントに格納
                menuThanksFragment.arguments = bundle
                //生成した注文完了フラグメントをmenuThanksFrameレイアウト部品に追加
                transaction?.replace(R.id.menuThanksFrame, menuThanksFragment)
                //フラグメントトランザクションコミット
                transaction?.commit()
            } else { //通常画面の場合
                //インテントオブジェクトを生成
                val intent2MenuThanks = Intent(activity, MenuThanksActivity::class.java)
                //第二画面に送るデータを格納
                intent2MenuThanks.putExtras(bundle)
                //第二画面の起動
                startActivity(intent2MenuThanks)
            }

        }
    }

}