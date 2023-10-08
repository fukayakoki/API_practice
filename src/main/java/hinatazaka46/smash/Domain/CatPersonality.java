package hinatazaka46.smash.Domain;

public enum CatPersonality {

    AMAENBO("甘えん坊猫"),
    NORABOSS("ノラボス猫"),
    SEKKACHI("せっかち猫"),
    ADVENTURER("冒険家猫"),
    OTTORI("おっとり猫"),
    BIBIRI("ビビり猫"),
    BEAUTY("美形猫"),
    PLAYFUL("遊び好き猫"),
    CHATTY("おしゃべり猫"),
    LONE_WOLF("単独行動猫"),
    MAMA("ママ猫"),
    GUARD("自宅警備員猫"),
    STUDY("インテリ猫");

    private final String description;

    CatPersonality(String description) {
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

