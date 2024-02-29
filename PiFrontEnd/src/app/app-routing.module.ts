import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { MarketplaceComponent } from './MarketPlacee/marketplace/marketplace.component';
import { ProductDetailsComponent } from './MarketPlacee/product-details/product-details.component';
import { RegisterProductComponent } from './MarketPlacee/register-product/register-product.component';
import { AddCarpoolingComponent } from './carpooling/add-carpooling/add-carpooling.component';
import { CarpoolingListComponent } from './carpooling/carpooling-list/carpooling-list.component';
import { ReactiveComponent } from './carpooling/reactive/reactive.component'
const routes: Routes = [
  {path:'', component: HomeComponent},
  {path:'marketplace', component: MarketplaceComponent},
  {path:'registerproduct', component: RegisterProductComponent},
  {path: 'detail/:id', component: ProductDetailsComponent},
  {path:'addcarpooling', component: AddCarpoolingComponent},
  {path:'carpoolingList', component: CarpoolingListComponent},
  {path: 'reactive', component: ReactiveComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
