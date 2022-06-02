package br.com.kelvingcr.easymarket.model

data class ItemModel (

    var nome: String,
    var valor: Double

) {
    override fun toString(): String {
        return "Item: $nome, Valor: R$$valor"
    }
}