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



@NgModule({
  declarations: [
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
    MatSnackBarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
