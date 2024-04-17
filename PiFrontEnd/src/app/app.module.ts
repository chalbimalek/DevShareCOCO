import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { MarketplaceComponent } from './MarketPlacee/marketplace/marketplace.component';
import { RegisterProductComponent } from './MarketPlacee/register-product/register-product.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatStepperModule } from '@angular/material/stepper';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule } from '@angular/material/form-field';
import { HttpClientModule } from '@angular/common/http';
import { FilterPipe } from './image/filter.pipe';
import {MatGridListModule} from '@angular/material/grid-list';
import { DragDirective } from './drag.directive';
import { ListProduitComponent } from './BackOffice/alltemplate-back/list-produit/list-produit.component';
import { ShowProductDetailsComponent } from './MarketPlacee/show-product-details/show-product-details.component';
import {MatTableModule} from '@angular/material/table';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatDialogModule} from '@angular/material/dialog';
import { ProdutShowDialogComponent } from './MarketPlacee/produt-show-dialog/produt-show-dialog.component';
import { AddProductBackComponent } from './MarketPlacee/add-product-back/add-product-back.component';
import { CommonModule } from '@angular/common';
import { DetaitlsbackComponent } from './MarketPlacee/detaitlsback/detaitlsback.component';
import { MatCardModule } from '@angular/material/card';
import { VaryingmodalcontentComponent } from './MarketPlacee/varyingmodalcontent/varyingmodalcontent.component';
import { AlltemplateBackComponent } from './BackOffice/alltemplate-back/alltemplate-back.component';
import { FooterBackComponent } from './BackOffice/footer-back/footer-back.component';
import { NavbarBackComponent } from './BackOffice/navbar-back/navbar-back.component';
import { SidebarBackComponent } from './BackOffice/sidebar-back/sidebar-back.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { NavbarFrontComponent } from './FrontOffice/navbar-front/navbar-front.component';
import { AlltemplatefrontComponent } from './FrontOffice/alltemplatefront/alltemplatefront.component';
import { FooterfrontComponent } from './FrontOffice/footerfront/footerfront.component'; // Importez NgxPaginationModule depuis ngx-pagination
import * as QRCode from 'qrcode';
import { RegisterCarpoolingComponent } from './Carpooling/register-carpooling/register-carpooling.component';
import { ListCarpoolingComponent } from './Carpooling/list-carpooling/list-carpooling.component';
import { DetailsCarpoolingComponent } from './Carpooling/details-carpooling/details-carpooling.component';
import { DateFormatPipe } from './date-format.pipe'; // Importez la bibliothèque qrcode
import { MatDatepickerModule } from '@angular/material/datepicker';
import { SignupComponent } from './signup/signup.component';
import { GuardComponent } from './guard/guard.component';
import { LoginComponent } from './login/login.component';
import { ProfilComponent } from './profil/profil.component';
import { GoogleRecaptchaComponent } from './google-recaptcha/google-recaptcha.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { CartComponent } from './cart/cart.component';
import { BuyProductComponent } from './buy-product/buy-product.component';
import { MyOrdersComponent } from './my-orders/my-orders.component';
import { OrderDetaisComponent } from './order-detais/order-detais.component';
import { PaymentComponent } from './payment/payment.component';
import { ConfirmationDialogComponent } from './confirmation-dialog/confirmation-dialog.component';
import { SnackbarComponent } from './snackbar/snackbar.component';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { AddCollocationComponent } from './Collocation/add-collocation/add-collocation.component';
import { ListCollocationComponent } from './Collocation/list-collocation/list-collocation.component';
import { DetailsCollocationComponent } from './Collocation/details-collocation/details-collocation.component';
import { AddEventComponent } from './Forum-Event/event/events/add-event/add-event.component';
import { DetailEventComponent } from './Forum-Event/event/events/detail-event/detail-event.component';
import { EditEventsComponent } from './Forum-Event/event/events/edit-events/edit-events.component';
import { EventsComponent } from './Forum-Event/event/events/events.component';
import { CustomDatePipe } from './Forum-Event/event/CustomDatePipe';
import { AddPostFileComponent } from './Forum-Event/add-post-file/add-post-file.component';
import { EditPostsComponent } from './Forum-Event/post/post/edit-posts/edit-posts.component';
import { PostdetailComponent } from './Forum-Event/post/post/postdetail/postdetail.component';
import { PostComponent } from './Forum-Event/post/post/post.component';
import { SearchPostsComponent } from './Forum-Event/search-posts/search-posts.component';
import { AddPostsComponent } from './Forum-Event/post/post/add-posts/add-posts.component';
import { RatingComponent } from './Forum-Event/rating/rating.component';
import { RatingService } from './Service/rating.service';



@NgModule({
  declarations: [
    AddPostsComponent,
    SearchPostsComponent,
    PostComponent,
    PostdetailComponent,
    EditPostsComponent,
    AddPostFileComponent,
    CustomDatePipe,
    EventsComponent,
    EditEventsComponent,
    DetailEventComponent,
    AddEventComponent ,
    AppComponent,
    HomeComponent,
    MarketplaceComponent,
    RegisterProductComponent,

    FilterPipe,
    DragDirective,
    ListProduitComponent,
    ShowProductDetailsComponent,
    ProdutShowDialogComponent,
    AddProductBackComponent,
    DetaitlsbackComponent,
    VaryingmodalcontentComponent,
    AlltemplateBackComponent,
    FooterBackComponent,
    NavbarBackComponent,
    SidebarBackComponent,
    NavbarFrontComponent,
    AlltemplatefrontComponent,
    FooterfrontComponent,
    RegisterCarpoolingComponent,
    ListCarpoolingComponent,
    DetailsCarpoolingComponent,
    DateFormatPipe,
    SignupComponent,
    GuardComponent,
    LoginComponent,
    ProfilComponent,
    GoogleRecaptchaComponent,
    CartComponent,
    BuyProductComponent,
    MyOrdersComponent,
    OrderDetaisComponent,
    PaymentComponent,
    SnackbarComponent,
    AddCollocationComponent,
    ListCollocationComponent,
    DetailsCollocationComponent,
    RatingComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    MatStepperModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    HttpClientModule,
    FormsModule,
    MatGridListModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    CommonModule,
    MatCardModule,
    NgxPaginationModule,
    MatDatepickerModule,
    MatSnackBarModule,
    MatProgressSpinnerModule,
    CommonModule,
    ReactiveFormsModule
  ],
  providers: [RatingService],
  bootstrap: [AppComponent]
})
export class AppModule { }
