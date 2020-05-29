package fr.fabriceDesign.ldvhlh_loeilnoir.model

import com.google.gson.annotations.SerializedName

/**
 * Created by fabricedesign at 26/05/2020
 * fr.fabriceDesign.ldvhlh_loeilnoir.model
 */
data class Page(@SerializedName("number") var number:Int,
                @SerializedName("text") var text : String,
                @SerializedName("choix") var choix : ArrayList<Choix>,
                @SerializedName("ennemis") var ennemi : ArrayList<Ennemi>?)

data class Choix (@SerializedName("text") val text : String?,
                  @SerializedName("number") val number : Int)

data class Ennemi(@SerializedName("name") var name: String,
                  @SerializedName("courage") var courage : Int,
                  @SerializedName("force") var force : Int,
                  @SerializedName("intel") var intel : Int,
                  @SerializedName("charisme") var charisme : Int,
                  @SerializedName("adresse") var adresse : Int,
                  @SerializedName("vie") var vie : Int,
                  @SerializedName("aventure") var aventure : Int = 0)