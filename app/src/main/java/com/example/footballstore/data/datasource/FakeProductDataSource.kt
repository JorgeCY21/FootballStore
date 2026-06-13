package com.example.footballstore.data.datasource

import com.example.footballstore.domain.model.Product
import javax.inject.Inject

class FakeProductDataSource @Inject constructor() {

    private data class ProductSeed(
        val name: String,
        val price: Double,
        val imageUrl: String,
        val categoryId: Int
    )

    private val products = productSeeds.mapIndexed { index, seed ->
        Product(
            id = index + 1,
            name = seed.name,
            price = seed.price,
            stock = 5 + (index % 18),
            description = buildDescription(seed.name, seed.categoryId),
            imageUrl = seed.imageUrl,
            categoryId = seed.categoryId
        )
    }.toMutableList()

    fun getProducts(): List<Product> = products.toList()

    fun getProductById(id: Int): Product? = products.firstOrNull { it.id == id }

    fun addProduct(product: Product) {
        products.add(product)
    }

    fun updateProduct(product: Product) {
        val index = products.indexOfFirst { it.id == product.id }
        if (index >= 0) {
            products[index] = product
        }
    }

    fun deleteProduct(id: Int) {
        products.removeAll { it.id == id }
    }

    private fun buildDescription(name: String, categoryId: Int): String {
        return when (categoryId) {
            3 -> "$name, tomado de la oferta actual de adidas Peru para futbol. Ideal para entrenamientos o partidos segun el modelo."
            4 -> "$name, guante oficial del catalogo actual de arqueros en adidas Peru, con enfoque en agarre, proteccion y control."
            5 -> "$name, accesorio real de entrenamiento y proteccion para futbol, pensado para sesiones intensas y juego competitivo."
            6 -> "$name, accesorio de transporte del catalogo de futbol de adidas Peru, util para llevar chimpunes, ropa y equipamiento."
            else -> "$name, prenda real del catalogo actual de futbol de adidas Peru, comoda para entrenar, competir o completar tu uniforme."
        }
    }

    private companion object {
        val productSeeds = listOf(
            ProductSeed("Shorts Visitantes Seleccion Peruana 26 Niños", 129.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/b2d6648644a8449c863b5efe8dae6656_9366/Shorts_Visitantes_Seleccion_Peruana_26_Ninos_Negro_JZ8900_000_plp_model.jpg", 7),
            ProductSeed("Shorts Visitantes Seleccion Peruana 26 Version Jugador", 229.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/e1b0d523b8e949c799072a2ce3952d26_9366/Shorts_Visitantes_Seleccion_Peruana_26_Version_Jugador_Negro_KB7503_000_plp_model.jpg", 7),
            ProductSeed("Shorts Visitantes Seleccion Peruana 26", 169.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/387bf7ace6de4767851a4bd0f42f92f7_9366/Shorts_Visitantes_Seleccion_Peruana_26_Negro_JL8659_000_plp_model.jpg", 7),
            ProductSeed("Shorts Locales Alemania 26", 169.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/c773046de4e04b2a9e7e5d7962dd524d_9366/Shorts_Locales_Alemania_26_Negro_JN2073_000_plp_model.jpg", 7),
            ProductSeed("Shorts Originals Peru", 159.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/c490d94e30ba4a4eb21633e71d1d5f3f_9366/Shorts_Originals_Peru_Negro_KA8596_HM1.jpg", 7),
            ProductSeed("Shorts Alemania Originals", 159.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/906f61df4e1f462eaae568220a812cf4_9366/Shorts_Alemania_Originals_Azul_JZ9343_HM1.jpg", 7),
            ProductSeed("Shorts Espana Originals", 159.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/a214229e77544df89c8fdb835ab9cd1d_9366/Shorts_Espana_Originals_Granate_JZ2257_HM1.jpg", 7),
            ProductSeed("Shorts Originals Italia", 159.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/4348fdecfdcb418d850bd01e67b95e45_9366/Shorts_Originals_Italia_Azul_JY9625_HM1.jpg", 7),
            ProductSeed("Shorts Entrada26 Negro", 59.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/79b4824c29de4e609a9434591767a1ca_9366/Shorts_Entrada26_Negro_JZ2521_000_plp_model.jpg", 7),
            ProductSeed("Shorts Locales Liverpool FC 26/27", 169.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/c9e5ba3f04fd4793bf69d7471d3ba9dd_9366/Shorts_Locales_Liverpool_FC_26-27_Granate_KA6854_000_plp_model.jpg", 7),
            ProductSeed("Shorts Entrada26 Blanco", 59.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/97cc697fc2ee413fbd43fae819b41d46_9366/Shorts_Entrada26_Blanco_JZ2516_000_plp_model.jpg", 7),
            ProductSeed("Shorts Tiro26 League Negro", 99.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/1cc3968b5eb04e9eb365870e4c4cdebd_9366/Shorts_Tiro26_League_Negro_KA8773_000_plp_model.jpg", 7),
            ProductSeed("Shorts Locales Seleccion Peruana 26 Niños", 129.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/1312bd4d86934be68c0d51954dc885e8_9366/Shorts_Locales_Seleccion_Peruana_26_Ninos_Blanco_JZ8897_000_plp_model.jpg", 7),
            ProductSeed("Shorts Cortos Entrada26 Mujer", 59.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/0119c0e233de4382b12f4bfb7f6a2cf8_9366/Shorts_Cortos_Entrada26_Negro_JZ6540_000_plp_model.jpg", 7),
            ProductSeed("Shorts de Entrenamiento Tiro 23 Club", 99.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/d417dab9b7794016ac27af1f00eb204e_9366/shorts_de_Entrenamiento_Tiro_23_Club_Negro_HS9533_01_laydown.jpg", 7),
            ProductSeed("Shorts Squadra 25 Negro", 69.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/7521980b0e1b43e395bafe098e6da9ef_9366/Shorts_Squadra_25_Negro_JN5469_000_plp_model.jpg", 7),
            ProductSeed("Shorts Squadra 25 Blanco", 69.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/77c56aac6a064e15b720f7780366a2c5_9366/Shorts_Squadra_25_Blanco_JH3412_000_plp_model.jpg", 7),
            ProductSeed("Shorts Locales Seleccion Peruana 26", 169.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/5b52a55737754a4e9aa954ddfd6c96e7_9366/Shorts_Locales_Seleccion_Peruana_26_Blanco_JL8657_000_plp_model.jpg", 7),
            ProductSeed("Shorts de Entrenamiento Tiro 23 Club Azul", 99.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/f72bbb760c8644b68273af2b0115ecc7_9366/shorts_de_Entrenamiento_Tiro_23_Club_Azul_HZ0176_01_laydown.jpg", 7),
            ProductSeed("Shorts de Entrenamiento Tiro 23 Club Negro Alterno", 99.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/419d9e258ccf471db951afc200f420e2_9366/shorts_de_Entrenamiento_Tiro_23_Club_Negro_IL9542_01_laydown.jpg", 7),
            ProductSeed("Shorts Locales Seleccion Colombia 26", 169.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/1ec7ab88f86d4175a5cb5e7fbb89fdf6_9366/Shorts_Locales_Seleccion_Colombia_26_Azul_JL8662_000_plp_model.jpg", 7),
            ProductSeed("Shorts Squadra 25 Rojo", 69.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/06227773c76a4fb2b004831f4d903b2f_9366/Shorts_Squadra_25_Rojo_JN5466_000_plp_model.jpg", 7),
            ProductSeed("Shorts DNA Peru", 99.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/da04b656cccf4a5dab02a9ba2a6157b0_9366/Shorts_DNA_Peru_Negro_KA8602_000_plp_model.jpg", 7),
            ProductSeed("Shorts Locales Espana 26", 169.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/c7ad765cd9fe48b7a9a4194b3cdab2fa_9366/Shorts_Locales_Espana_26_Azul_JN4396_000_plp_model.jpg", 7),
            ProductSeed("Shorts Tiro26 League Blanco", 99.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/b33c86428bee42d3ab266efedc178b41_9366/Shorts_Tiro26_League_Blanco_KA8775_000_plp_model.jpg", 7),
            ProductSeed("Conjunto de Minipelotas Historical Copa Mundial de la FIFA 26", 779.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/c3df4e440e2f43f0952402ca6c01cbf0_9366/Conjunto_de_Minipelotas_Historical_Copa_Mundial_de_la_FIFA_26tm_Blanco_JN2093_HM1.jpg", 3),
            ProductSeed("Pelota CONEXT26 PRO", 549.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/3019e62823c046ad9a54037c05875447_9366/Pelota_CONEXT26_PRO_Multicolor_KE5866_00_plp_standard.jpg", 3),
            ProductSeed("Pelota Messi Club", 79.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/a97eb8507aae4874aa3bd432574af346_9366/PELOTA_MESSI_CLUB_Blanco_KA5267_00_plp_standard.jpg", 3),
            ProductSeed("Pelota Club EPP", 59.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/7f550da377f041a4aef78883ff3582bf_9366/PELOTA_CLUB_EPP_Multicolor_JW4011_00_plp_standard.jpg", 3),
            ProductSeed("Pelota Trionda Training Copa Mundial de la FIFA 26", 99.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/05a86a8a110d4b7bab767fba4f9f0062_9366/Pelota_Trionda_Training_Copa_Mundial_de_la_FIFA_26tm_Blanco_JD8032_00_plp_standard.jpg", 3),
            ProductSeed("Pelota League Copa Mundial de la FIFA 26", 159.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/fa5659d887cb4767aeb6009a62602197_9366/Pelota_League_Copa_Mundial_de_la_FIFA_26tm_Blanco_JD8045_41_detail.jpg", 3),
            ProductSeed("Pelota Trionda Club Copa Mundial de la FIFA 26 Blanco", 69.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/c806717dbb3d479eb5c7a39d3fbea39d_9366/Pelota_Trionda_Club_Copa_Mundial_de_la_FIFA_26tm_Blanco_JD8028_00_plp_standard.jpg", 3),
            ProductSeed("Pelota Trionda Pro Copa Mundial de la FIFA 26", 549.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/4229e87f23044869a5218fbc64c4fd71_9366/Pelota_Trionda_Pro_Copa_Mundial_de_la_FIFA_26tm_Blanco_JD8021_HM1.jpg", 3),
            ProductSeed("Pelota Trionda League Copa Mundial de la FIFA 26", 129.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/3eb831325cf94ca3868b4368e99f7b8c_9366/Pelota_Trionda_League_Copa_Mundial_de_la_FIFA_26tm_Blanco_JD8030_00_plp_standard.jpg", 3),
            ProductSeed("Pelota Trionda Competition Copa Mundial de la FIFA 26", 279.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/83986c87b57746909ab2192158b085ec_9366/Pelota_Trionda_Competition_Copa_Mundial_de_la_FIFA_26tm_Blanco_JD8031_00_plp_standard.jpg", 3),
            ProductSeed("Pelota Trionda Mini Copa Mundial de la FIFA 26", 49.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/f923fcbee4f34af48b4fd66b20add7b4_9366/Pelota_Trionda_Mini_Copa_Mundial_de_la_FIFA_26tm_Blanco_JD8034_00_plp_standard.jpg", 3),
            ProductSeed("Pelota de futbol UEFA Champions League Final League Box", 159.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/2c18db8b82b74482a6d1a107130b77c1_9366/Pelota_de_futbol_UEFA_CHAMPIONS_LEAGUE_FINAL_LEAGUE_BOX_Multicolor_JX9101_00_plp_standard.jpg", 3),
            ProductSeed("Pelota Tiro Club Blanco", 69.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/094cd5f6bb624530803c053b1d9b478e_9366/Pelota_Tiro_Club_Blanco_JW1530_00_plp_standard.jpg", 3),
            ProductSeed("Pelota Local Club Liverpool FC", 69.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/b56cda601fba46278d74ad7d96ac2fa2_9366/Pelota_Local_Club_Liverpool_FC_Granate_KE5132_00_plp_standard.jpg", 3),
            ProductSeed("Pelota Trionda Club Copa Mundial de la FIFA 26 Multicolor", 69.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/4b4a2c999b274834bf63a4f8eefb668e_9366/Pelota_Trionda_Club_Copa_Mundial_de_la_FIFA_26tm_Multicolor_JD8053_00_plp_standard.jpg", 3),
            ProductSeed("Pelota Tiro Club Verde", 69.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/3eab7af28fa24fcc99f9485a63d4d5f9_9366/Pelota_Tiro_Club_Verde_JW1532_00_plp_standard.jpg", 3),
            ProductSeed("Pelota Club Local Real Madrid", 69.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/1867668e242b47a28df14e997447d905_9366/Pelota_Club_Local_Real_Madrid_Blanco_KE5125_00_plp_standard.jpg", 3),
            ProductSeed("Pelota Trionda Club Copa Mundial de la FIFA 26 Verde", 69.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/082714f1ff2841c897d0f7be96d5c98e_9366/Pelota_Trionda_Club_Copa_Mundial_de_la_FIFA_26tm_Verde_JD8052_00_plp_standard.jpg", 3),
            ProductSeed("Pelota Messi League", 129.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/9408c458a17541b9897b5ddd61b16fc9_9366/PELOTA_MESSI_LEAGUE_Multicolor_KA5266_00_plp_standard.jpg", 3),
            ProductSeed("Pelota Trionda Home Club Argentina", 69.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/e0f36874252b4896bae126acc7d5267d_9366/Pelota_Trionda_Home_Club_Argentina_Azul_JY0304_00_plp_standard.jpg", 3),
            ProductSeed("Pelota Universadi", 49.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/96414a576a15414bbc7e684d33c0556d_9366/Pelota_Universadi_Blanco_KB9775_00_plp_standard.jpg", 3),
            ProductSeed("Pelota Trionda Club Copa Mundial de la FIFA 26 Blanco JD8054", 69.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/d3ab1f9a6d874a17b5aaa0d836b1d92b_9366/Pelota_Trionda_Club_Copa_Mundial_de_la_FIFA_26tm_Blanco_JD8054_00_plp_standard.jpg", 3),
            ProductSeed("Guantes de Entrenamiento de Arquero para Niños Predator", 99.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/312e960c58ab41acaf8b03a157c8b92a_9366/GUANTES_DE_ENTRENAMIENTO_DE_ARQUERO_PARA_NINOS_PREDATOR_Azul_KA7793_000_plp_model.jpg", 4),
            ProductSeed("Guantes de Arquero Predator Pro Promo Hybrid", 579.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/1b30a7d743f444b9a27f8394c6143606_9366/Guantes_de_Arquero_Predator_Pro_Promo_Hybrid_Rojo_JH3818_000_plp_model.jpg", 4),
            ProductSeed("Guantes de Entrenamiento para Arquero Predator", 129.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/065892f221ea490d9c157e41e9eabfa6_9366/GUANTES_DE_ENTRENAMIENTO_PARA_ARQUERO_PREDATOR_Blanco_JY6311_000_plp_model.jpg", 4),
            ProductSeed("Guantes de Entrenamiento para Arquero Predator para Niños", 99.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/bce04a5940f54596928546ec4c012bfa_9366/GUANTES_DE_ENTRENAMIENTO_PARA_ARQUERO_PREDATOR_PARA_NINOS_Rojo_KA7792_000_plp_model.jpg", 4),
            ProductSeed("Guantes de Arquero Copa Club", 99.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/5af2e5307ea542beb8cf7b9c091d0dcf_9366/GUANTES_DE_ARQUERO_COPA_CLUB_Blanco_KA7804_000_plp_model.jpg", 4),
            ProductSeed("Guantes de Arquero Copa Club para Jovenes Blanco", 79.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/ba00e1b86be64f3e800e9155edca12af_9366/GUANTES_DE_ARQUERO_COPA_CLUB_PARA_JOVENES_Blanco_KA7808_01_00_standard.jpg", 4),
            ProductSeed("Guantes de arquero Predator Training para Niños", 99.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/80294c42fd97438b9dd87e6d755cfbe7_9366/Guantes_de_arquero_Predator_Training_para_Ninos_Naranja_JN5361_00_plp_standard.jpg", 4),
            ProductSeed("Guantes de Arquero Copa Club para Jovenes Negro", 79.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/39b5369f85df4f2db49f3e65853fcc67_9366/GUANTES_DE_ARQUERO_COPA_CLUB_PARA_JOVENES_Negro_KA7809_01_00_standard.jpg", 4),
            ProductSeed("Guantes de arquero Copa Club para ninos", 99.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/8bc7dbfa09214d59b4edf709ac2695f5_9366/Guantes_de_arquero_Copa_Club_para_ninos_Negro_JN5341_000_plp_model.jpg", 4),
            ProductSeed("Guantes de Entrenamiento de Arquero Predator Azul", 129.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/703dc73ec666416fba63243e189a734f_9366/GUANTES_DE_ENTRENAMIENTO_DE_ARQUERO_PREDATOR_Azul_KA7786_000_plp_model.jpg", 4),
            ProductSeed("Guantes de Arquero Predator Pro Azul", 499.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/fc318ffe8bfb48fb9b90926ef3b075f9_9366/GUANTES_DE_ARQUERO_PREDATOR_PRO_Azul_JY6296_000_plp_model.jpg", 4),
            ProductSeed("Guante de arquero Predator absolado Match Fingersave jovenes", 169.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/33808def28a54bc882f5628176df43fd_9366/Guante_de_arquero_Predator_absolado_Match_Fingersave_jovenes_Azul_KA7791_000_plp_model.jpg", 4),
            ProductSeed("Guantes de Arquero Predator Pro Blanco", 499.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/120750a7577f4f4eb95ecc5163e6cf41_9366/GUANTES_DE_ARQUERO_PREDATOR_PRO_Blanco_JY6295_000_plp_model.jpg", 4),
            ProductSeed("Guantes de Arquero Predator Match Fingersave para Jovenes", 169.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/8cb40dc742d84cbbaa09c114afe89c6c_9366/GUANTES_DE_ARQUERO_PREDATOR_MATCH_FINGERSAVE_PARA_JOVENES_Blanco_KA7790_000_plp_model.jpg", 4),
            ProductSeed("Guantes Tiro League", 79.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/9ad17a292dcb422e9fd7af100099a3e4_9366/Guantes_Tiro_League_Negro_HS9760_00_plp_standard.jpg", 4),
            ProductSeed("Guantes de Entrenamiento para Arquero Predator Negro", 103.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/78f6f79596424947b9dfb2aaed1275a5_9366/GUANTES_DE_ENTRENAMIENTO_PARA_ARQUERO_PREDATOR_Negro_KA7787_000_plp_model.jpg", 4),
            ProductSeed("Copa GL CLB", 79.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/1096cbd6ab7840198711ad03d7f67e4c_9366/COPA_GL_CLB_Negro_JY0625_000_plp_model.jpg", 4),
            ProductSeed("Guantes de arquero Copa Club Negro", 63.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/a3093371753c4a0b81746d8c78bf8768_9366/Guantes_de_arquero_Copa_Club_Negro_JY0626_000_plp_model.jpg", 4),
            ProductSeed("Medias Adi 26 Blanco", 59.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/b2e57a087cd44bdbaf5ecf10a5861fc7_9366/Medias_Adi_26_Blanco_KB7162_00_plp_standard.jpg", 7),
            ProductSeed("Medias Santos 25 Blanco", 39.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/5434fa927c2b4e2c952cb49fa0cbd18d_9366/Medias_Santos_25_Blanco_JJ3609_00_plp_standard.jpg", 7),
            ProductSeed("Medias Santos 25 Negro", 39.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/a83b30816fe84e698a61be0357323b21_9366/Medias_Santos_25_Negro_JJ3613_00_plp_standard.jpg", 7),
            ProductSeed("Medias de Rendimiento a Media Pantorrilla con Estampado de Agarre adidas Futbol Negro", 59.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/d914045801fd48aa9d4be12d385f492d_9366/Medias_de_Rendimiento_a_Media_Pantorrilla_con_Estampado_de_Agarre_adidas_Futbol_Negro_KE7290_00_plp_standard.jpg", 7),
            ProductSeed("Medias Adi 26 Negro", 59.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/4c018ff123da4596b1136d076d4901f3_9366/Medias_Adi_26_Negro_KB7163_00_plp_standard.jpg", 7),
            ProductSeed("Medias de Futbol adi 24 AEROREADY Azul", 59.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/c16f86fd1a794a718a8f281697d4cbb8_9366/Medias_de_Futbol_adi_24_AEROREADY_Azul_IM8925_00_plp_standard.jpg", 7),
            ProductSeed("Medias Locales Seleccion Peruana 26", 69.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/4a1d45ec3eea47b891d6fd7a0a09b920_9366/Medias_Locales_Seleccion_Peruana_26_Blanco_JY5096_00_plp_standard.jpg", 7),
            ProductSeed("Medias Referee 22", 49.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/b1dbc280a96143d4961e1e2656492f68_9366/Medias_Referee_22_Negro_KE0289_00_plp_standard.jpg", 7),
            ProductSeed("Medias de Futbol adi 24 AEROREADY Rojo", 59.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/5d6c51e628ec4bb0a00dbf47b02c7b58_9366/Medias_de_Futbol_adi_24_AEROREADY_Rojo_IM8922_00_plp_standard.jpg", 7),
            ProductSeed("Medias Milano 23 Negro", 39.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/d1b66252bd8b4e75b4bcaf0201013d11_9366/Medias_Milano_23_Negro_HT6538_00_plp_standard.jpg", 7),
            ProductSeed("Medias Adi25 Amarillo", 59.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/2d4ed9d993334703b7821814b1767967_9366/Medias_Adi25_Amarillo_JM3663_00_plp_standard.jpg", 7),
            ProductSeed("Medias Milano 23 Blanco", 39.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/c7299ee8690e4b519333af0201085985_9366/Medias_Milano_23_Blanco_IB7813_00_plp_standard.jpg", 7),
            ProductSeed("Medias Adi25 Verde", 59.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/d38d8396d1ef4462b69d3f48da8d9bd0_9366/Medias_Adi25_Verde_JM3665_00_plp_standard.jpg", 7),
            ProductSeed("Medias Milano 23 Azul", 39.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/1351fdd2b9444a63aba6af020108ddd1_9366/Medias_Milano_23_Azul_IB7818_00_plp_standard.jpg", 7),
            ProductSeed("Medias Milano 23 Rojo", 39.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/ef67f00f45b542b1bc50af020108b365_9366/Medias_Milano_23_Rojo_IB7817_00_plp_standard.jpg", 7),
            ProductSeed("Medias adi 23 Negro", 41.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/1042851ef30f4f38b17cae620132981b_9366/Medias_adi_23_Negro_HT5027_00_plp_standard.jpg", 7),
            ProductSeed("Medias de Futbol adi 24 AEROREADY Blanco", 41.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/830f85c7a9b94b2e846f9a05beecea06_9366/Medias_de_Futbol_adi_24_AEROREADY_Blanco_IM8926_00_plp_standard.jpg", 7),
            ProductSeed("Medias Performance adidas Futbol Largo Clasico Negro", 55.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/63f17d2025fe4f37a22bae6200d143b7_9366/Medias_Performance_adidas_Futbol_Largo_Clasico_Negro_HN8838_00_plp_standard.jpg", 7),
            ProductSeed("Mochila Liverpool FC para Niños", 89.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/2bc6be9799f84d189cefa3ff7a5e4138_9366/Mochila_Liverpool_FC_para_Ninos_Negro_KQ5149_00_plp_standard.jpg", 6),
            ProductSeed("Bolso para Chimpunes Tiro", 69.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/835d4d34ec1e470fb83c381e3e5dceff_9366/BOLSO_PARA_CHIMPUNES_TIRO_Negro_JY7993_00_plp_standard.jpg", 6),
            ProductSeed("Mochila Real Madrid Niños", 89.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/20b3f1bceb1c446aa0933b4364b7d975_9366/Mochila_Real_Madrid_Ninos_Azul_KR7558_00_plp_standard.jpg", 6),
            ProductSeed("Bolso Deportivo Tiro Azul", 59.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/3acd121daa7a4898aa42f8b7885a352f_9366/BOLSO_DEPORTIVO_TIRO_Azul_KD4245_00_plp_standard.jpg", 6),
            ProductSeed("Mochila Tiro Negra", 129.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/7be07aab3a354590b5f55ce961544a0b_9366/MOCHILA_TIRO_Negro_JY7971_00_plp_standard.jpg", 6),
            ProductSeed("Maleta Deportiva Tiro Pequena con Compartimiento Inferior", 149.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/a670219e88654e67a8f655569171a1c4_9366/MALETA_DEPORTIVA_TIRO_PEQUENA_CON_COMPARTIMIENTO_INFERIOR_Negro_JY7939_00_plp_standard.jpg", 6),
            ProductSeed("Maleta Deportiva Tiro Mediana", 149.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/185c81d5c03244ab846709151a3eadba_9366/Maleta_Deportiva_Tiro_Mediana_Negro_KB0786_00_plp_standard.jpg", 6),
            ProductSeed("Bolso Deportivo Tiro Negro", 59.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/ff01a4a1c1014eb2a13f20068d243292_9366/BOLSO_DEPORTIVO_TIRO_Negro_JY7992_00_plp_standard.jpg", 6),
            ProductSeed("Mochila Tiro Azul", 129.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/8312cdff415548a4babacc9a8cf20e08_9366/MOCHILA_TIRO_Azul_KD4223_00_plp_standard.jpg", 6),
            ProductSeed("Maleta Deportiva Tiro Grande", 169.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/89663d094fd946fcb788f01c2b992b62_9366/MALETA_DEPORTIVA_TIRO_GRANDE_Negro_JY7937_00_plp_standard.jpg", 6),
            ProductSeed("Canilleras Tiro Club Negro", 59.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/14a6b0abffa84d96a66a73b1c16a4877_9366/Canilleras_Tiro_Club_Negro_IS5399_00_plp_standard.jpg", 5),
            ProductSeed("Medias con Canilleras Negro", 69.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/3ee2ba55b55d41f6bed8a516015ea3e4_9366/Medias_con_Canilleras_Negro_AH7764_00_plp_standard.jpg", 5),
            ProductSeed("Espinilleras Tiro Club", 49.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/9ebeec0246fd4dfdb6f512f241cb8211_9366/Espinilleras_Tiro_Club_Verde_JD6021_00_plp_standard.jpg", 5),
            ProductSeed("Canilleras Tiro Club Blanco", 59.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/dc06efbda2ba4868a0fa0471fe8ae4ee_9366/Canilleras_Tiro_Club_Blanco_IP3995_00_plp_standard.jpg", 5),
            ProductSeed("Canilleras Tiro Club Negro HN5601", 49.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/a45141d721b4454d9772af3a00bed368_9366/Canilleras_Tiro_Club_Negro_HN5601_00_plp_standard.jpg", 5),
            ProductSeed("Canilleras de Entrenamiento Tiro", 79.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/a04ddf43339a435387fd9cce6f49de9a_9366/Canilleras_de_Entrenamiento_Tiro_Negro_IP3998_00_plp_standard.jpg", 5),
            ProductSeed("Medias con Canilleras Blanco", 69.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/00333bff1f3b4e81afffa6c501118a9f_9366/Medias_con_Canilleras_Blanco_AZ9873_00_plp_standard.jpg", 5),
            ProductSeed("Camiseta Visitante Seleccion Peruana 26 Version Jugador", 359.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/2145082d2b7b4b98b11f3b58e2d96ab3_9366/Camiseta_Visitante_Seleccion_Peruana_26_Version_Jugador_Negro_KB7502_HM4.jpg", 1),
            ProductSeed("Camiseta Visitante Seleccion Peruana 26", 259.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/80e5b2631079420b90ee605feaf8c0bf_9366/Camiseta_Visitante_Seleccion_Peruana_26_Negro_JL8660_000_plp_model.jpg", 1),
            ProductSeed("Camiseta Visitante Seleccion Argentina 26 Version Jugador", 429.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/bd7e8465ca6340f6ba344deea1e15cd1_9366/Camiseta_Visitante_Seleccion_Argentina_26_Version_Jugador_Negro_JM5901_HM53.jpg", 1),
            ProductSeed("Camiseta Visitante Seleccion Argentina 26", 329.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/8379c4f319d34f948399a95e6f4b28ac_9366/Camiseta_Visitante_Seleccion_Argentina_26_Negro_JM8395_000_plp_model.jpg", 1),
            ProductSeed("Camiseta Visitante Seleccion Colombia 26", 329.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/70f4bc91f935442e8cac80543f1bb140_9366/Camiseta_Visitante_Seleccion_Colombia_26_Azul_JL6974_000_plp_model.jpg", 1),
            ProductSeed("Camiseta Visitante Alemania 26", 329.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/a3eb6fcc22434146abe924050e0a91ad_9366/Camiseta_Visitante_Alemania_26_Azul_JN2074_000_plp_model.jpg", 1),
            ProductSeed("Camiseta Visitante Espana 26", 329.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/d3d30120532e4e4dbe9b5a0d51fe1df2_9366/Camiseta_Visitante_Espana_26_Blanco_JN4397_000_plp_model.jpg", 1),
            ProductSeed("Camiseta Visitante Belgica 26", 329.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/5264fc56820644e293fa616e49b3b319_9366/Camiseta_Visitante_Belgica_26_Azul_JM8386_000_plp_model.jpg", 1),
            ProductSeed("Camiseta Local Seleccion Peruana 26", 259.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/21283e1da2fc4df58f59c38a16038a49_9366/Camiseta_Local_Seleccion_Peruana_26_Blanco_JL8651_000_plp_model.jpg", 1),
            ProductSeed("Camiseta Local Seleccion Argentina 26", 329.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/841749a208934f2ab4a0cfa1a8ae237d_9366/Camiseta_Local_Seleccion_Argentina_26_Blanco_JM8396_000_plp_model.jpg", 1),
            ProductSeed("Camiseta Local Espana 26", 329.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/866664f412bb4443bca790fed9c9f0a3_9366/Camiseta_Local_Espana_26_Rojo_JN4390_000_plp_model.jpg", 1),
            ProductSeed("Chimpunes F50 Hyperfast Elite sin Pasadores para Terreno Firme", 1199.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/5c54a7f085f44c03a5d86ede472dde0e_9366/Chimpunes_F50_Hyperfast_Elite_sin_Pasadores_para_Terreno_Firme_Rosado_KJ6080_HM1.jpg", 2),
            ProductSeed("Chimpunes Predator Elite Lengueta Plegable Terreno Firme", 1099.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/8743a21c02ae42d9a160c855d3d757e7_9366/Chimpunes_Predator_Elite_Lengueta_Plegable_Terreno_Firme_Rosado_JP6237_HM1.jpg", 2),
            ProductSeed("Chimpunes Copa Pure IV Elite Terreno Firme", 1099.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/79da62bcfac64c599973485905c5ea73_9366/Chimpunes_Copa_Pure_IV_Elite_Terreno_Firme_Rosado_KI0586_22_model.jpg", 2),
            ProductSeed("Chimpunes F50 Hyperfast League sin Pasadores Terreno Firme", 379.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/4dcf7761842a4a8d9c9d26690b88e0e4_9366/Chimpunes_F50_Hyperfast_League_sin_Pasadores_Terreno_Firme_Rosado_IH7089_HM1.jpg", 2),
            ProductSeed("Chimpunes F50 Hyperfast League Terreno Firme", 359.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/b90b4d6d0a35425782f1408dfc43ad0f_9366/Chimpunes_F50_Hyperfast_League_Terreno_Firme_Rosado_IH9346_HM1.jpg", 2),
            ProductSeed("Chimpunes F50 Hyperfast Club Terreno Firme Multiterreno", 259.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/ae721b713d4e49769d9afbfc1ad823c4_9366/Chimpunes_F50_Hyperfast_Club_Terreno_Firme-Multiterreno_Rosado_IH9375_22_model.jpg", 2),
            ProductSeed("Chimpunes F50 Hyperfast Club Pasto Artificial", 259.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/d6a5a5f464844662829facd662f41d2e_9366/Chimpunes_F50_Hyperfast_Club_Pasto_Artificial_Rosado_IH4604_22_model.jpg", 2),
            ProductSeed("Chimpunes F50 Hyperfast League Pasto Artificial", 359.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/207434c61a614e91b0beca3a2974e598_9366/Chimpunes_F50_Hyperfast_League_Pasto_Artificial_Rosado_IH4582_HM1.jpg", 2),
            ProductSeed("Chimpunes F50 Hyperfast League sin Cordones Pasto Sintetico", 379.0, "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/7c7a118a15d24a7f944131654db6b1f4_9366/Chimpunes_F50_Hyperfast_League_sin_Cordones_Pasto_Sintetico_Rosado_KJ6711_22_model.jpg", 2),
            ProductSeed("Chimpunes de Futbol Copa Pure II League Terreno Firme", 279.0, "https://assets.adidas.com/images/w_600%2Cf_auto%2Cq_auto/1a36fcc47e6549978ee69d669cfeb01e_9366/Chimpunes_de_Futbol_Copa_Pure_II_League_Terreno_Firme_Granate_IE7494_22_model.jpg", 2),
            ProductSeed("Chimpunes de Futbol Copa Gloro Pasto Sintetico", 449.0, "https://assets.adidas.com/images/w_600%2Cf_auto%2Cq_auto/7a5164fa980c40a4a5dfefbd03d790a5_9366/Chimpunes_de_Futbol_Copa_Gloro_Pasto_Sintetico_Blanco_IE7541_22_model.jpg", 2)
        )
    }
}
