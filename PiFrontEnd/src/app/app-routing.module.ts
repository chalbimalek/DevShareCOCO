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
import { CartComponent } from './cart/cart.component';
import { BuyProductComponent } from './buy-product/buy-product.component';
import { BuyProductResolverService } from './buy-product-resolver.service';
import { MyOrdersComponent } from './my-orders/my-orders.component';
import { OrderDetaisComponent } from './order-detais/order-detais.component';
import { PaymentComponent } from './payment/payment.component';
import { ListCollocationComponent } from './Collocation/list-collocation/list-collocation.component';
import { CollocationResolveService } from './Collocation/ImageCollocation/collocation-resolve.service';
import { AddCollocationComponent } from './Collocation/add-collocation/add-collocation.component';
import { DetailsCollocationComponent } from './Collocation/details-collocation/details-collocation.component';
import { PostComponent } from './Forum-Event/post/post/post.component';
import { AddPostsComponent } from './Forum-Event/post/post/add-posts/add-posts.component';
import { EditPostsComponent } from './Forum-Event/post/post/edit-posts/edit-posts.component';
import { EventsComponent } from './Forum-Event/event/events/events.component';
import { AddEventComponent } from './Forum-Event/event/events/add-event/add-event.component';
import { EditEventsComponent } from './Forum-Event/event/events/edit-events/edit-events.component';
import { SearchPostsComponent } from './Forum-Event/search-posts/search-posts.component';
import { DetailEventComponent } from './Forum-Event/event/events/detail-event/detail-event.component';
import { PostdetailComponent } from './Forum-Event/post/post/postdetail/postdetail.component';
import { AddPostFileComponent } from './Forum-Event/add-post-file/add-post-file.component';

import { RatingComponent } from './Forum-Event/rating/rating.component';
const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' }, // Rediriger vers la page de connexion par défaut

  {path:'',component:AlltemplatefrontComponent, children:[
    { path: 'posts', component: PostComponent},
    {path: 'addpost', component: AddPostsComponent},
    { path: 'editPost/:id', component: EditPostsComponent },
    {path: 'events', component: EventsComponent},
    {path: 'addEvent', component: AddEventComponent},
    { path: 'editEvent/:id', component: EditEventsComponent },
    { path: 'detailEvent/:id', component: DetailEventComponent },
    { path:'detailPost/:id',component:PostdetailComponent},
    { path: 'addPostFile/:id', component: AddPostFileComponent },
    { path: 'post/SearchPosts/:word', component: SearchPostsComponent },
    {path:'rating',component:RatingComponent},
    
    
  
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
  {path:'listCollocation',  canActivate: [AuthGuardService],component: ListCollocationComponent},
  {path:'detailsColl',  canActivate: [AuthGuardService],component: DetailsCollocationComponent,resolve: { product: CollocationResolveService }},
  {path:'addCollocation',  canActivate: [AuthGuardService],component: AddCollocationComponent},

  {path:'marketplace',  canActivate: [AuthGuardService],component: MarketplaceComponent},
  {path:'cart',component: CartComponent},
{path:'buyProduct',component:BuyProductComponent,  resolve: {
  productDetails: BuyProductResolverService} },
  { path: 'myOrders', component: MyOrdersComponent ,  canActivate:[AuthGuardService], data:{roles:['User']} },
{path:'paiment',component:PaymentComponent ,  canActivate:[AuthGuardService]},
  {path:'registerproduct', component: RegisterProductComponent},
  {path: 'detail', component: ProductDetailsComponent ,resolve: { product: ProductResolveBackService }},
  {path: 'detailback', component: DetaitlsbackComponent ,resolve: { product: ProductResolveBackService }},

]},
  




{path:'back' ,component:AlltemplateBackComponent, canActivate: [AuthGuardService], children:[
  { path: 'posts', component: PostComponent},
  {path: 'addpost', component: AddPostsComponent},
  { path: 'editPost/:id', component: EditPostsComponent },
  {path: 'events', component: EventsComponent},
  {path: 'addEvent', component: AddEventComponent},
  { path: 'editEvent/:id', component: EditEventsComponent },
  { path: 'detailEvent/:id', component: DetailEventComponent },
  { path:'detailPost/:id',component:PostdetailComponent},
  { path: 'addPostFile/:id', component: AddPostFileComponent },
  { path: 'post/SearchPosts/:word', component: SearchPostsComponent },
  {path:'rating',component:RatingComponent},



  {path:'showback',component:ShowProductDetailsComponent},
  {path:'list',component:ListProduitComponent},
  {path :'addproduitBack', component:AddProductBackComponent,

  resolve:{
    product:ProductResolveBackService
  }},
  { path: 'orderInformation' , component: OrderDetaisComponent ,  canActivate:[AuthGuardService]},


{path: 'detailback', component: DetaitlsbackComponent ,resolve: { product: ProductResolveBackService }},



]

},{path:'varying' ,component:VaryingmodalcontentComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
