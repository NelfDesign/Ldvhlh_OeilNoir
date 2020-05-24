package fr.fabriceDesign.ldvhlh_loeilnoir.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by fabricedesign at 19/05/2020
 * fr.fabriceDesign.ldvhlh_loeilnoir.model
 */
@Entity(tableName = "personnage")
data class Personnage(
    @PrimaryKey(autoGenerate = true)
    var id : Long,
    var name: String,
    var sexe: String,
    var type: String,
    var courage : Int,
    var force : Int,
    var intel : Int,
    var charisme : Int,
    var adresse : Int,
    var vie : Int,
    var astrale : Int = 0,
    var aventure : Int = 0,
    var fortune : Int)