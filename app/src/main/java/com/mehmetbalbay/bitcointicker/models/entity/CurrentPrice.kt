package com.mehmetbalbay.bitcointicker.models.entity


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentPrice(
    @SerializedName("aed")
    val aed: Double,
    @SerializedName("ars")
    val ars: Double,
    @SerializedName("aud")
    val aud: Double,
    @SerializedName("bch")
    val bch: Double,
    @SerializedName("bdt")
    val bdt: Double,
    @SerializedName("bhd")
    val bhd: Double,
    @SerializedName("bmd")
    val bmd: Double,
    @SerializedName("bnb")
    val bnb: Double,
    @SerializedName("brl")
    val brl: Double,
    @SerializedName("btc")
    val btc: Double,
    @SerializedName("cad")
    val cad: Double,
    @SerializedName("chf")
    val chf: Double,
    @SerializedName("clp")
    val clp: Double,
    @SerializedName("cny")
    val cny: Double,
    @SerializedName("czk")
    val czk: Double,
    @SerializedName("dkk")
    val dkk: Double,
    @SerializedName("dot")
    val dot: Double,
    @SerializedName("eos")
    val eos: Double,
    @SerializedName("eth")
    val eth: Double,
    @SerializedName("eur")
    val eur: Double,
    @SerializedName("gbp")
    val gbp: Double,
    @SerializedName("hkd")
    val hkd: Double,
    @SerializedName("huf")
    val huf: Double,
    @SerializedName("idr")
    val idr: Double,
    @SerializedName("ils")
    val ils: Double,
    @SerializedName("inr")
    val inr: Double,
    @SerializedName("jpy")
    val jpy: Double,
    @SerializedName("krw")
    val krw: Double,
    @SerializedName("kwd")
    val kwd: Double,
    @SerializedName("lkr")
    val lkr: Double,
    @SerializedName("ltc")
    val ltc: Double,
    @SerializedName("mmk")
    val mmk: Double,
    @SerializedName("mxn")
    val mxn: Double,
    @SerializedName("myr")
    val myr: Double,
    @SerializedName("nok")
    val nok: Double,
    @SerializedName("nzd")
    val nzd: Double,
    @SerializedName("php")
    val php: Double,
    @SerializedName("pkr")
    val pkr: Double,
    @SerializedName("pln")
    val pln: Double,
    @SerializedName("rub")
    val rub: Double,
    @SerializedName("sar")
    val sar: Double,
    @SerializedName("sek")
    val sek: Double,
    @SerializedName("sgd")
    val sgd: Double,
    @SerializedName("thb")
    val thb: Double,
    @SerializedName("try")
    val tryX: Double,
    @SerializedName("twd")
    val twd: Double,
    @SerializedName("uah")
    val uah: Double,
    @SerializedName("usd")
    val usd: Double,
    @SerializedName("vef")
    val vef: Long,
    @SerializedName("vnd")
    val vnd: Double,
    @SerializedName("xag")
    val xag: Double,
    @SerializedName("xau")
    val xau: Double,
    @SerializedName("xdr")
    val xdr: Double,
    @SerializedName("xlm")
    val xlm: Double,
    @SerializedName("xrp")
    val xrp: Double,
    @SerializedName("yfi")
    val yfi: Double,
    @SerializedName("zar")
    val zar: Double,
    @SerializedName("link")
    val link: Double
) : Parcelable