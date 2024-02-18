import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { MarketplaceComponent } from './MarketPlacee/marketplace/marketplace.component';
import { ProductDetailsComponent } from './MarketPlacee/product-details/product-details.component';
import { RegisterProductComponent } from './MarketPlacee/register-product/register-product.component';

const routes: Routes = [
  {path:'', component: HomeComponent},
  {path:'marketplace', component: MarketplaceComponent},
  {path:'registerproduct', component: RegisterProductComponent},
  {path: 'detail/:id', component: ProductDetailsComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
