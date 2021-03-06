package forest.rice.field.k.linenotify.api.meigen;

import java.util.Calendar;

public class IchiroManager implements IMeigenManager {

	public static void main(String[] args) {
		System.out.println(new IchiroManager().getMessage(Calendar.getInstance().get(Calendar.DAY_OF_YEAR)));
		System.out.println(new IchiroManager().getMessage(179));
		System.out.println(new IchiroManager().getMessage(180));
		System.out.println(new IchiroManager().getMessage(181));

	}

	String[] list = { "人の意見や評価ほど曖昧なものはない。", "日々やっていることを同じようにやることが大切。心から持って行くのは難しいですが、体をいつもと同じように動かせば、そのうち心がついてくる。",
			"一時的に活躍するのは才能と少しの運があれば難しくない。ただ、長い時間コンスタントに結果を残していくには才能以外の何かが必要になる。", "先輩たちを超えることが後輩の使命。",
			"オフの間までストイックにやるのはダメ。", "毎日の中にいかに考えない時間をつくるかが重要なことではないかと。頭を白というより透明にする感覚です。",
			"我慢はこれがないと進めない重要な要素です。嬉しい気持ちも、悔しい気持ちも、外には出さず我慢して内に秘めながら進んで欲しい。", "数字が超えたからといって、２年前の僕をいまの僕が超えているかどうかわからない。",
			"上手くいっているだけではつまらない。", "大切なのは、いま自分がやっていることが好きであるかどうか。それさえあれば自分を磨こうとするし、常に前に進むことができる。",
			"自分の中で何かひらめいたり、バッティングの技術が高まったりするのは、いつも決まって凡打のときです。",
			"結果が良かったからといって、自分にとっていい内容だとは限らないし、結果が悪く出ても、すべてが良くないとも思わない。", "フォアボールを狙いにいくようなバッターは、その時点で負けています。",
			"大切なのは、チームが負けたからといってモチベーションを失ってはいけないということなんです。",
			"自分らしくあろうとしたときに大事なのは、嘘をつかないことでしょう。相手にこう思って欲しいがためだけに、安易に言葉を発しないということは大事だと思っています。<script async src="// pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>"
			, "どんな結果に対しても、僕はそれを受け入れる。失敗したときの自分の立場が怖いからといって、変な理由づけはしません。だから僕の発している言葉に嘘はないはずです。",
			"小さいことを積み重ねることが、とんでもないところへ行くただひとつの道。",
			"去年、行き着いたひとつの答えは、プレッシャーを克服する方法なんて、結局はないんだということです。以前はプレッシャーがない普通の状態に近い自分をどうやって取り戻すことができるのか、そういう薬みたいなものを探していました。でも、そんなものはないんだというのが現段階での結論です。そう思えたことは大きいですよ。あるかもしれないと思っているのと、ないんだと割り切っているのとでは、プレッシャーに対する向き合い方はまったく違ってきますから。",
			"あのとき（ワールド・ベースボール・クラシックのとき）僕を支えていたのは、自分のやってきたことへのプライドと、これからやろうとしていることへの自信でした。",
			"僕は常に人のちょっと先を行かなければいけないと考えています。何かをする側が後をついていくようではまずいですから。",
			"７年先のことなんて考えたら、ろくなことはないですよ。その時々に集中していくという方法をとります。僕はとどまっていたいと考えることはないでしょうから、野球には無数、無限の可能性があると信じていたいんです。選手の年齢は、精神的脂肪に出るものです。脳みその硬さですよね。ここに一番、年齢があらわれちゃいますから、それだけは避けたいと思っています。もちろん僕は常識的ではないので、そんなものとは無縁でしょうけど。",
			"やれることはすべてやってきましたし、手を抜いたことは一度もありません。常にやれることをやろうとした自分がいた。それに対して準備ができた自分がいたことを誇りに思います。",
			"僕は今年で日米合わせて１６年プレーしてきました。１６年もプレーして、いまようやくスタート地点に立ったという気がしているんです。これまで、いろんな数字を残してきて、これから先のモチベーションを保てるのかといろんな人に聞かれるんですけど、僕はそこについてはまったく心配していません。なぜならこれまで僕は野球選手として、何かをやったという達成感が残っていないからです。",
			"僕は自分がメジャーでやりたくて行っただけですから、結果的にどうなろうとも自分のためにやっただけなんです。あとから「日本野球界のためになった」とか、先駆者だとか、そうやっていい意味に捉えられても戸惑います。僕が先駆者だなんて、ちゃんちゃらおかしいいですよ。",
			"いまでもブレない自分というものが完全に出来上がっているわけではないですよ。ただ、その時々に感じているものの中から、おかしいなって感じたものを削除するという行為を繰り返してきただけなんです。その意識は徐々に強くなってきたとは思いますけど。",
			"雨の日に買い物したら、買ったものにビニールをかけてくれたんですよ。いやぁ、久しぶりに思い出しましたね、日本はそうだったって（笑）。急いでいるから早くっていう鬱陶しさもあったりするんですけど、それでもああいうことをド丁寧に、時間をかけてやるんですから、すごいですよ。仕事に対する意識の高さとか控えめな態度とか、そういうところで感じるものなんですよね。",
			"野球の人気ということを考えるのならば、そこで選手たちがどういう立ち振る舞いをするかというのは大事だと思います。子供たちに対しては、勝つだけじゃなくて、カッコいいなぁと思ってもらえなければダメなんです。真摯に野球のことを考えている選手たちが集まって、それでさらに勝てれば、子供たちが野球に向いてくれるきっかけになる可能性は十分にあると思います。",
			"強いチームというのは、個人があってチームがあると思うんです。個々が持っている力を発揮して、役割をはたして、それが結果としてチームとしての力となる。でも、弱いチームは、個々が持っている力を発揮されない。だから勝てない。「チームのために」という言葉でごまかして個人の力を発揮できないことへの言い訳を探す、そうしたらもっと勝てなくなる。悪循環ですよね。",
			"苦しいけど、バッティングに終わりはない。もうこれで終わりというのがわかっていたら、救われるんです。もし、２割９分３厘でも、まあいいんじゃねえの、と感じている自分がいたら、ぶっ殺してやりたい（笑）。そんな打率しか残せていないことに怒りを感じている自分がいることは、悪くないんです。３割切って悪くないなと思いだしたら、僕、野球辞めます。４割を打つか、３割を切って満足したら、僕、確実に野球辞めますから。ｌ",
			"僕にとっては、高校を出てすぐの９２年にプレーしていたオリックスの２軍、あのチームの雰囲気は最高でしたから、すごく楽しかった。でも、楽しいのと面白いのとは、ちょっと違うと思います。いまだって、草野球の中に入って野球をやれば楽しいし、きっと笑いっぱなしですよ。でも、おもしろさというのはそういう次元では味わうことができない。",
			"選手として同情されるのは最大の屈辱なんです。もし僕が記録を抜けなければ、なんだよ、できなかったのかよと非難されると思いますけど、同時に、仕方ないよくやったという声も出てきたと思うんです。それは僕にとっては一番悔しい。非難された方が、よっぽどマシだと思います。",
			"自分が人生かけてやってきた、一番時間を費やしてきた、一番自信を持って勝負してきたことで、精神的に一段上に上がれるチャンスができたわけですから、それは辛いこともありますけど、だからこそ面白いし、ドキドキできるんです。",
			"ひとつだけ言えるとしたら、メシのタネに野球をやっている選手では、絶対にここまで来られないと思います。野球が生活の手段になってしまったら、もっと前に進みたいという気持ちは消えてしまいますから。こちらでも、野球が手段になってしまっている選手はムチャクチャ多い。",
			"まだまだ自分の可能性は感じていますし、この数字を超えられるだけの自分を作り上げる余地は感じています。だって、まだミスをしていますから。それを減らせば、もっとヒットが打てるということでしょう。",
			"今年のテーマは許すことです。いままでの僕は、何に対しても完ぺきを求めていました。でも、相手のいいところを探そうとか、見方や視点をちょっと変えると、それもありかな、と思えることがある。もちろん、それは自分に対する甘えにつながる可能性はありますけど、僕の「甘い」は他の人よりよっぽど厳しいと思いますから、それくらいはオッケーでしょう。脱、完ぺき主義ですね。",
			"いろんな人の話を聞くと、肉体的な節目がやってくるのが３０歳か３５歳だという人が多い。自分だけは違う、という発想は危険なんです。慎重になることで、何かを防げることはあるでしょうし。ただ、この先に僕が考えなければいけないのは、そういう周りの頑なな目に流されてはいけないということ。この世界、３０歳はこう、３５歳はこう、４０歳はこうなるというものが出来上がってしまっているでしょう。でも、僕はそうではないと思っています。",
			"僕は常に戦っています。苦しいのは当たり前だし、それもメジャーで野球をやる楽しみのひとつなのかもしれません。一番苦しいと感じるのは、できるのにできないということ。相手にやられて、とてもそんなことはできないと思えるのなら、まだいいんです。それは自分の力のなさですから、もうしょうがない。でも、できるはずのことができないからこそ、歯がゆいし、悔しいんです。",
			"これまでも常にプレッシャーを自分にかけてきましたし、そういう状況はたくさんありました。それをくぐり抜けてきた、乗り越えてきたという自信がありますから、どんな状況が目の前に現れても動揺したりすることはないし、普通の精神状態で目の前の状況をこなすことができる自信はあります。",
			"経験があるということは、イメージができるということじゃないですか。試合が始まるまでの気持ちの持っていき方が違ってきますし、だいたいこうだとイメージできれば、あらゆる場面でどういうふうに対処していくかということがわかりますから。",
			"野球場においては、プライドを持ってその場に立つということは変わりません。これまでと何も変わらずに、ただユニフォームが変わっているだけ。信念を持ったことは、向こうのスタイルがこうだからといって揺らぐことはできないです。",
			"英語？前は少し勉強しましたけど、いまはまったくやってない。メジャー行きたいなぁ、でも無理だろうなぁって感覚のときは勉強もやる気になるんですけど、いざ行けるとなったら、英語の勉強なんかするよりも素振りしなきゃとか（笑）、そっちの方に意識が行ってしまうんです。",
			"野球界に限らず、どの世界でもそうだと思いますけど、自分のできることをとことんやっていきたいという意識があるか、ないか、そういうことだと思うんですよ。首位打者を獲ったとか獲らないとかということじゃなくて、２割５分の選手であっても、自分のできることを、完ぺきには無理でも意識の中でできた人間であれば、適当にやった３割５分の選手よりもプライドを持って相手に立ち向かえると思うんです。どっちが人間として優秀かといわれると、決して適当にやった３割５分を残したほうじゃない。",
			"メジャー行きをとくに意識したとなると、９６年の日米野球ですね。ヤツらのスウィングを見ていると、いまの一打席をものすごく大事にしてバットを振っている感覚があるわけですよ、何の迷いもなく。なんかこう、自分がやりたいスタイルって本当はああいう感じだったのに、いつのまにか殻に入ってしまったというか、なんか自分が小さく見えてね。自分でもそういう感覚を失ってたわけじゃないんだけど、それほど強くなくなっていたんでしょうね。あぁ、いいなぁ、って思いましたよ。",
			"最大の武器？それは、何かにトライしていこうとしている自分がいるということです。自分では、向こうでいまある状態を出してみたい、という感覚なんです。いまの僕の現状ではもっと上は見えてこないですから。",
			"別にそうは思わないですね。それだけのことをやってきたわけですから。<br/>【覚書き｜自分のことを天才だと思うかという問いに対しての発言】",
			"人の意見や評価は曖昧なものだから。<br/>【覚書き｜オリックスでの二軍時代、そしてメジャーに行ってからもコーチに「一般的な打ち方にするように」と言われても振り子打法をやめなかったことについて語った言葉】",
			"ジーター（デレク・ジーター、ＭＬＢを代表するスーパースター）が言ってたんだけど、「今日で練習試合は終わりだ」と。このチームはあくまでここがファーストステージで、この瞬間は明日からは過去のものになる。<br/>【覚書き｜２０１２年、ヤンキースが２年連続ア・リーグ東地区優勝を決めた祝勝会でのコメント】",
			"いまでもブレない自分というのが完全にできあがっているわけではありません。ただ、その時々に感じたものを削除するという行為を繰り返してきただけなんです。",
			"２０００本目を打ったときも、次の打席のことのほうが大事だととらえていました。<br/>【覚え書き｜メジャー通算２０００本安打を達成したときを振り返っての言葉】",
			"食べたくなったら食べるですね。いまの軸は食パンです。アリゾナで食べた食パンがメチャクチャ美味しくて、それをシアトルまで送ってもらってます。<br/>【覚え書き｜食生活について聞かれたときの発言】",
			"何か無理に抑えることをしたくない。身体が欲しがることを僕は表現するだけですから。", "家の中の空間がどれくらい気持ちよいかというのは、グラウンドに反映されるんです。", "僕、一貫性ないの嫌いなんです。",
			"最初からあるべき姿に到達するのは不可能で、まずはムダな時間を経験して、そこから削ぎ落としていくことによってようやく自分の行きたいところに近づけるのではないかと。合理的に考えすぎてムダの生じないような進み方をしようとすると、結局近づくことすらできない。当然、深みも出ない。",
			"僕にとって準備は、一試合終わったときの言い訳の材料をなくす作業です。たとえばシューズやグローブを磨いていないと、エラーをしたときや怪我をしたときにシューズやグローブのせいにするかもしれない。そうした言い訳を自分にさせないように、納得いくまで準備をするのです。僕には普通のことだけど、それを異常だと言う人もいる。正解はわかりません。",
			"野球選手はキャンプが始まると動きが決まったものになるので、シーズンオフにいかにアホになれるかが大事。オフにまで突き詰めてやると、必ずそのツケがシーズン中に回ってきます。",
			"大事にしているのが、ゲーム後から寝るまでの２、３時間です。この時間のうちにその日のことを振り返って結論を出さないと、確実に引きずってしまいます。本来は、その日起きたことは、できるだけクラブハウスで整理する。それが理想です。",
			"達成感や満足感を、僕は味わえば味わうほど、前に進めると思う。小さなことでも満足することは、すごく大事なこと。それを味わうと次へのやる気が生まれてくると、僕は経験上信じている。",
			"負けているときに落ち込んだり、勝っているときに喜ぶチームは怖くない。負けているのに元気なチーム、勝っているのに喜ばないチームこそが怖い。" };

	@Override
	public String getMessage(int num) {
		return list[num % list.length];
	}
}
