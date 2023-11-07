package hinatazaka46.smash.Domain;

public enum CatDescription {

    AMAENBO("甘えん坊の猫は、感情豊かで愛情深い存在です。日々の生活の中で、彼らは飼い主の膝の上に乗ったり、一緒にくつろいだりすることを愛しています。彼らのこの甘えん坊な性格は、飼い主との強い絆や信頼を示すものであり、しばしば家族の中心的存在として注目されます。"),
    NORABOSS("ノラボスの猫は独立心が旺盛で、しっかりとした自我を持っています。彼らは自分のテリトリーを守ることを非常に重視し、他の動物や猫には警戒心を持ちながら接します。そんな彼らでも、信頼できる人間や環境には愛情を示すことができる特別な存在です。"),
    SEKKACHI("せっかちな猫は、エネルギーに満ち溢れていて、待っていることが苦手な性格を持っています。何事も迅速に行いたいと感じる彼らは、新しいおもちゃや環境に対して非常に興奮しやすいです。飼い主との遊び時間は、彼らにとって最高の贈り物となるでしょう。"),
    ADVENTURER("冒険家の猫は、好奇心旺盛で新しいことを探求するのが大好きです。家の中の隠れた場所や外の未知の世界に興味を持ち、常に新しい発見を求めています。彼らとの生活は、飼い主にとっても新しい発見や驚きが満載で、一緒に冒険の旅を楽しむことができます。"),
    OTTORI("おっとりとした猫は、温和で穏やかな性格を持っています。彼らは、周りの環境や人々との関係に対して非常にフレキシブルで、新しい環境や猫ともすぐに打ち解けることができます。その優しさと安らぎを提供する存在は、家族の中で非常に大切な役割を果たします。"),
    BIBIRI("ビビりの猫は、敏感で臆病な性格を持っており、新しい環境や突然の音に敏感に反応します。しかし、安全な場所や飼い主の側にいることで、彼らの不安を和らげることができます。そんな彼らの繊細な性格は、深い絆を感じることができる魅力的な点でもあります。"),
    BEAUTY("美形の猫は、その外見の魅力で多くの人々を魅了します。その美しさは、優雅さや気品を持ち合わせており、彼らの存在自体が特別なものと感じられます。しかし、その美しさだけでなく、心の中にも優しさや愛情を持っていることが多いです。"),
    PLAYFUL("遊び好きの猫は、常にエネルギッシュで活発です。彼らは、新しいおもちゃやゲームに対して興奮しやすく、飼い主との遊びを通じて日々の楽しみを見つけます。その遊び心満載な性格は、家の中を明るくし、飼い主との関係を深める要因となります。"),
    CHATTY("おしゃべりな猫は、声をよく出すことで飼い主とのコミュニケーションを取ります。彼らの鳴き声や行動は、彼らの気持ちや要求を示すものであり、彼らとの日常は会話が絶えない楽しいものとなります。"),
    LONE_WOLF("単独行動の猫は、他の猫や動物、時には人間よりも独自の時間を大切にする性格を持っています。これは彼らが社交的でないわけではありません。彼らは深く考える傾向があり、静かな環境でのリラックスした時間を求めています。また、彼らはしばしば環境や状況を観察するのが得意で、それによって何が起こるかを予測する能力を持っています。飼い主との関係も特別で、深い絆を築くことができる一方で、彼らのプライバシーを尊重することが必要です。"),
    MAMA("ママ猫は、母性本能が強く、他の動物や猫、そして時には人間に対しても保護的な態度を取ることが多いです。彼女たちは他の猫や動物を気遣い、必要な時にはケアや愛情を示します。家の中では、彼女たちは家族の中心的な存在となり、他の猫や動物の面倒を見るリーダーシップを発揮することが多いです。また、飼い主との関係も深く、家族の一員としての役割を果たすことが多いです。"),
    GUARD("自宅警備員の猫は、その名の通り、家やテリトリーを守ることを非常に重視しています。彼らは家の中や外での異変に敏感に反応し、家族を守るための行動を取ることが多いです。外敵や危険を察知する能力が高く、飼い主が気付かないような小さな変化にも敏感に反応します。そのため、彼らとの生活は非常に安心感があり、家族としての役割を果たしています。"),
    STUDY("インテリ猫は、好奇心旺盛で知的な性格を持っています。新しいおもちゃやゲーム、そして学びの機会に興味を示すことが多く、日常の中で新しいことを学ぶのが大好きです。彼らは問題解決能力が高く、難しいゲームや課題を楽しむことができます。また、飼い主とのコミュニケーションも豊かで、彼らの知的な側面を活かした遊びや学びの時間は、飼い主にとっても非常に楽しいものとなります。");

    private final String description;

    CatDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static void main(String[] args) {
        for (CatPersonality personality : CatPersonality.values()) {
            System.out.println(personality.name() + ": " + personality.getDescription());
        }
    }
}