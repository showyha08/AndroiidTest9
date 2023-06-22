# AndroiidTest9

## フラグメントのライフサイクル
### onCreateView()メソッド

ActivityのCreated３番目で呼ばれる。
最低記述しなければならない処理は以下の通り。

- レイアウトXMLファイルから画面部品を生成
- 生成した画面部品に手を加える必要がある場合はその処理を使う
- 生成した画面部品を戻値として返す

#### レイアウトXMLファイルから画面部品を生成
onCreateView()メソッドの第１引数inflaterのinflate()メソッドを使う。
画面そのもののinflateはLayoutInflater。
LayoutInflaterのinflate()は引数が3つ必要。

#### 生成した画面部品に手を加える必要がある場合はその処理を使う
そのままフラグメントの画面として利用する場合は不要。
fragmentクラスにはfindViewById()メソッドが無いので注意。
かわりにviewのfindViewById()を利用する。

#### 生成した画面部品を戻値として返す
viewをreturnするだけ