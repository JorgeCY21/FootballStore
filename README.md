# Football Store

Aplicacion Android construida con Jetpack Compose para gestionar un catalogo de productos de futbol. El proyecto esta orientado a resolver un escenario academico donde se solicita CRUD de productos y categorias, busqueda, navegacion entre pantallas y una arquitectura basada en `ViewModelFactory`.

## Objetivo del proyecto

Resolver el siguiente enunciado:

- CRUD de productos y categorias
- Cada producto debe tener nombre, precio, descripcion e imagen
- Busqueda de productos por nombre o categoria
- Navegacion entre pantallas
- Implementacion con `ViewModelFactory`
- Cumplimiento de buenas practicas de Compose y arquitectura

## Tecnologias usadas

- Kotlin
- Jetpack Compose
- Material 3
- Navigation Compose
- ViewModel
- StateFlow
- Coil para carga de imagenes
- Gradle Kotlin DSL

## Como el proyecto resuelve el enunciado

### 1. CRUD de productos y categorias

El proyecto incluye logica y pantallas para crear, editar, listar y eliminar entidades:

- Productos:
  [ProductListScreen.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/screens/product/ProductListScreen.kt),
  [ProductCreateScreen.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/screens/product/ProductCreateScreen.kt),
  [ProductEditScreen.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/screens/product/ProductEditScreen.kt),
  [ProductDetailScreen.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/screens/product/ProductDetailScreen.kt)

- Categorias:
  [CategoryShopScreen.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/screens/category/CategoryShopScreen.kt),
  [CategoryListScreen.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/screens/category/CategoryListScreen.kt),
  [CategoryCreateScreen.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/screens/category/CategoryCreateScreen.kt),
  [CategoryEditScreen.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/screens/category/CategoryEditScreen.kt)

La logica de negocio del CRUD esta concentrada en:

- [ProductViewModel.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/viewmodel/ProductViewModel.kt)
- [CategoryViewModel.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/viewmodel/CategoryViewModel.kt)

### 2. Datos requeridos por producto

El modelo de producto contempla:

- Nombre
- Precio
- Descripcion
- Imagen
- Categoria

Referencia:
- [Product.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/data/model/Product.kt)

### 3. Busqueda por nombre o categoria

La busqueda se implementa desde el catalogo principal:

- Barra de busqueda por nombre:
  [FootballSearchBar.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/components/FootballSearchBar.kt)
- Filtro por categoria:
  [CategoryFilterChips.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/components/CategoryFilterChips.kt)
- Filtrado en ViewModel y repositorio:
  [ProductViewModel.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/viewmodel/ProductViewModel.kt),
  [ProductRepositoryImpl.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/data/repository/ProductRepositoryImpl.kt)

### 4. Navegacion entre pantallas

La navegacion esta centralizada en:

- [AppNavGraph.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/navigation/AppNavGraph.kt)
- [Routes.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/navigation/Routes.kt)

Actualmente el flujo principal conectado incluye:

- Inicio / catalogo
- Categorias de compra
- Detalle de producto
- Carrito

Ademas, el proyecto ya define pantallas y rutas para operaciones CRUD de administracion (`create`, `edit`, `list`) de productos y categorias, listas para integrarse o ampliarse dentro del flujo principal segun el alcance de la entrega.

### 5. Implementacion con ViewModelFactory

La creacion de `ViewModel` se resuelve mediante una fabrica propia:

- [AppViewModelFactory.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/viewmodel/AppViewModelFactory.kt)

Esto permite inyectar dependencias de repositorio sin acoplar la UI a implementaciones concretas.

## Cumplimiento de requisitos tecnicos

### Composable reutilizables

La UI esta dividida en componentes reutilizables:

- [ProductCard.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/components/ProductCard.kt)
- [CategoryCard.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/components/CategoryCard.kt)
- [ProductForm.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/components/ProductForm.kt)
- [CategoryForm.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/components/CategoryForm.kt)
- [FootballSearchBar.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/components/FootballSearchBar.kt)
- [CategoryFilterChips.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/components/CategoryFilterChips.kt)

### Estado y recomposicion

Se usa `StateFlow` expuesto desde los `ViewModel`, observado con `collectAsStateWithLifecycle()` en Compose. Esto permite recomposicion automatica cuando cambia el estado de la pantalla.

Referencias:

- [ProductViewModel.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/viewmodel/ProductViewModel.kt)
- [CategoryViewModel.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/viewmodel/CategoryViewModel.kt)

### Navegacion Compose

La app utiliza `NavHost`, `composable` y rutas tipificadas por convencion.

Referencia:

- [AppNavGraph.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/navigation/AppNavGraph.kt)

### Optimizacion de listas

Las listas se renderizan con `LazyColumn` e `items(..., key = { it.id })`, reduciendo recomposiciones innecesarias y mejorando estabilidad visual.

Referencias:

- [ProductListScreen.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/screens/product/ProductListScreen.kt)
- [CategoryShopScreen.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/screens/category/CategoryShopScreen.kt)
- [CategoryListScreen.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/screens/category/CategoryListScreen.kt)
- [CartScreen.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/screens/cart/CartScreen.kt)

### SOLID

El proyecto busca respetar SOLID a escala academica:

- `S`: responsabilidades separadas entre UI, ViewModel, repositorio y datasource
- `O`: la capa de repositorio puede extenderse sin romper la UI
- `L`: las implementaciones concretas respetan sus contratos
- `I`: interfaces pequenas por dominio (`ProductRepository`, `CategoryRepository`)
- `D`: los ViewModel dependen de abstracciones, no de detalles concretos

Referencias:

- [ProductRepository.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/data/repository/ProductRepository.kt)
- [CategoryRepository.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/data/repository/CategoryRepository.kt)

### MVVM

La estructura sigue el patron MVVM:

- `Model`: entidades y repositorios
- `View`: pantallas y componentes Compose
- `ViewModel`: manejo de estado, validacion y logica de interaccion

### Repository Pattern

La app abstrae el acceso a datos mediante interfaces e implementaciones de repositorio:

- [ProductRepository.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/data/repository/ProductRepository.kt)
- [ProductRepositoryImpl.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/data/repository/ProductRepositoryImpl.kt)
- [CategoryRepository.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/data/repository/CategoryRepository.kt)
- [CategoryRepositoryImpl.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/data/repository/CategoryRepositoryImpl.kt)

### UI State Pattern

Cada dominio de pantalla trabaja con estados explicitos:

- `Loading`
- `Success`
- `Empty`
- `Error`

Referencias:

- [ProductUiState.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/state/ProductUiState.kt)
- [CategoryUiState.kt](C:/Users/Usuario/AndroidStudioProjects/FootballStore/app/src/main/java/com/example/footballstore/ui/state/CategoryUiState.kt)

## Estructura general

```text
app/src/main/java/com/example/footballstore/
|- data/
|  |- datasource/
|  |- model/
|  |- repository/
|- ui/
|  |- components/
|  |- navigation/
|  |- screens/
|  |- state/
|  |- theme/
|- viewmodel/
```

## Ejecucion

1. Abrir el proyecto en Android Studio.
2. Sincronizar dependencias Gradle.
3. Ejecutar en emulador o dispositivo fisico con Android.

Compilacion por terminal:

```bash
./gradlew assembleDebug
```

En Windows:

```powershell
./gradlew.bat assembleDebug
```

## Observaciones tecnicas

- Los datos actuales provienen de `FakeDataSource`, utiles para pruebas locales y demostracion del flujo.
- La arquitectura ya esta preparada para reemplazar esa fuente por persistencia real, por ejemplo Room o una API.
- La navegacion principal actual prioriza el flujo de catalogo. Las pantallas CRUD administrativas existen en el proyecto y pueden conectarse al menu principal o a un flujo de administracion si la entrega final lo requiere.
