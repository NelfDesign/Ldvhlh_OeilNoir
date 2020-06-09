package fr.fabriceDesign.ldvhlh_loeilnoir.model

import com.google.gson.annotations.SerializedName

/**
 * Created by fabricedesign at 26/05/2020
 * fr.fabriceDesign.ldvhlh_loeilnoir.model
 */
data class Page(@SerializedName("number") var number:Int,
                @SerializedName("text") var text : String,
                @SerializedName("choix") var choix : ArrayList<Choix>,
                @SerializedName("ennemis") var ennemi : ArrayList<Ennemi>?,
                @SerializedName("jet") var jet : ArrayList<Jet>?,
                @SerializedName("bonus") var bonus : ArrayList<Bonus>?,
                @SerializedName("condition") var condition : ArrayList<ConditionVie>?
)

data class ConditionVie (@SerializedName("type") var type: String,
                         @SerializedName("minimum") var minimum : String,
                         @SerializedName("number") var number: Int
)

data class Bonus (@SerializedName("type") var type: String,
                  @SerializedName("modif") var modif: String,
                  @SerializedName("number") var number: Int
)

data class Jet (@SerializedName("type") var type  : String,
                @SerializedName("plus") var plus : Int?,
                @SerializedName("moins") var moins : Int?,
                @SerializedName("multiplier") var multi : Int?,
                @SerializedName("de") var de : Int,
                @SerializedName("number") var number : Int?,
                @SerializedName("capa") var capa : String?
)

data class Choix (@SerializedName("text") val text : String?,
                  @SerializedName("number") var number : Int?
)

data class Ennemi(@SerializedName("name") var name: String,
                  @SerializedName("combat") var combat: String,
                  @SerializedName("age") var age: Int?,
                  @SerializedName("courage") var courage : Int,
                  @SerializedName("energie") var energie : Int,
                  @SerializedName("protection") var protection : Int,
                  @SerializedName("attaque") var attaque : Int,
                  @SerializedName("parade") var parade : Int,
                  @SerializedName("impact") var impact : ArrayList<Impact>,
                  @SerializedName("aventure") var aventure : Int = 0
)

class Impact (@SerializedName("de") var de: Int,
              @SerializedName("plus") var plus : Int
)
