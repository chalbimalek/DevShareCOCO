import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { MarketplaceComponent } from './MarketPlacee/marketplace/marketplace.component';
import { ProductDetailsComponent } from './MarketPlacee/product-details/product-details.component';
import { RegisterProductComponent } from './MarketPlacee/register-product/register-product.component';
import { ListProduitComponent } from './BackOffice/alltemplate-back/list-produit/list-produit.component';
import { ShowProductDetailsComponent } from './MarketPlacee/show-product-details/show-product-details.component';
import { AddProductBackComponent } from './MarketPlacee/add-product-back/add-product-back.component';
import { ProductResolveBackService } from './image/product-resolve-back.service';
import { DetaitlsbackComponent } from './MarketPlacee/detaitlsback/detaitlsback.component';
import { VaryingmodalcontentComponent } from './MarketPlacee/varyingmodalcontent/varyingmodalcontent.component';
import { AlltemplateBackComponent } from './BackOffice/alltemplate-back/alltemplate-back.component';
import { AlltemplatefrontComponent } from './FrontOffice/alltemplatefront/alltemplatefront.component';
import { RegisterCarpoolingComponent } from './Carpooling/register-carpooling/register-carpooling.component';
import { ListCarpoolingComponent } from './Carpooling/list-carpooling/list-carpooling.component';
import { DetailsCarpoolingComponent } from './Carpooling/details-carpooling/details-carpooling.component';
import { CarpoolingResolveService } from './imageCarpooling/carpooling-resolve.service';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { ProfilComponent } from './profil/profil.component';
import { AuthGuardService } from './Service/auth-guard.service';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' }, // Rediriger vers la page de connexion par défaut

  {path:'',component:AlltemplatefrontComponent, children:[
  {path:'home', canActivate: [AuthGuardService], component: HomeComponent },

  {path:'Carpooling', canActivate: [AuthGuardService], component: RegisterCarpoolingComponent},
  {path:'listCarppoling', canActivate: [AuthGuardService], component: ListCarpoolingComponent},
  {path: 'detailCarp',  canActivate: [AuthGuardService],component: DetailsCarpoolingComponent ,resolve: { product: CarpoolingResolveService }},
  {
    path: 'login',
    component: LoginComponent,
  },
  { path: 'signup', component: SignupComponent },
  {
    path: 'profile',canActivate: [AuthGuardService],
    
    component: ProfilComponent,
   
  },
  {path:'marketplace',  canActivate: [AuthGuardService],component: MarketplaceComponent},
  {path:'registerproduct', component: RegisterProductComponent},
  {path: 'detail', component: ProductDetailsComponent ,resolve: { product: ProductResolveBackService }},
  {path: 'detailback', component: DetaitlsbackComponent ,resolve: { product: ProductResolveBackService }},

]},
  




{path:'back' ,component:AlltemplateBackComponent, canActivate: [AuthGuardService], children:[
  {path:'showback',component:ShowProductDetailsComponent},
  {path:'list',component:ListProduitComponent},
  {path :'addproduitBack', component:AddProductBackComponent,

  resolve:{
    product:ProductResolveBackService
  }},
  

{path: 'detailback', component: DetaitlsbackComponent ,resolve: { product: ProductResolveBackService }},



]

},{path:'varying' ,component:VaryingmodalcontentComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
